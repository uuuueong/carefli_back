package gradproject.carefli.connection.service;

import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.connection.dto.ConnectionListDto;
import gradproject.carefli.connection.dto.ConnectionRequestDto;
import gradproject.carefli.connection.dto.ConnectionResponseDto;
import gradproject.carefli.connection.dto.ConnectionUpdateRequestDto;
import gradproject.carefli.connection.repository.ConnectionRepository;
import gradproject.carefli.global.exception.CustomException;
import gradproject.carefli.global.exception.ErrorCode;
import gradproject.carefli.global.s3Image.service.S3ImageService;
import gradproject.carefli.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final S3ImageService s3ImageService;

    //인맥 등록
    public ConnectionResponseDto createConnection(User user, ConnectionRequestDto requestDto, MultipartFile multipartFile) throws IOException {
        String connectionImageUrl = null;

        if (multipartFile != null && !multipartFile.isEmpty()) {
            // 이미지를 S3에 업로드하고 URL을 가져옴
            String fileName = s3ImageService.upload(multipartFile, "images/connectionImages");
            connectionImageUrl = s3ImageService.getFileUrl(fileName);
        }

        // 이미지 URL을 포함하여 Connection 객체 생성
        Connection connection = requestDto.toEntity(user, connectionImageUrl);
        Connection savedConnection = connectionRepository.save(connection);
        return ConnectionResponseDto.from(savedConnection);
    }


    //인맥 수정
    public ConnectionResponseDto updateConnection(User user, Long connectionId, ConnectionUpdateRequestDto requestDto, MultipartFile multipartFile) throws IOException {
        Connection connection = connectionRepository.findByConnectionId(connectionId)
                .orElseThrow(() -> new CustomException(ErrorCode.CONNECTION_NOT_FOUND));

        if (multipartFile != null && !multipartFile.isEmpty()) {
            // 새 이미지 업로드
            String fileName = s3ImageService.upload(multipartFile, "images/connectionImages");
            String newFileUrl = s3ImageService.getFileUrl(fileName);

            // 기존 이미지 삭제
            if (connection.getConnectionImageUrl() != null && !connection.getConnectionImageUrl().isEmpty()) {
                String oldFileName = connection.getConnectionImageUrl().substring(connection.getConnectionImageUrl().indexOf("images/connectionImages"));
                s3ImageService.delete(oldFileName);
            }

            // 이미지 URL 업데이트
            connection.updateImageUrl(newFileUrl);
        }

        // 이미지 외의 다른 정보 업데이트
        connection.updateConnection(requestDto);
        Connection updatedConnection = connectionRepository.save(connection);
        return ConnectionResponseDto.from(updatedConnection);
    }
    //인맥 전체 조회
    public List<ConnectionListDto> getAllConnections(User user) {
        List<Connection> connections = connectionRepository.findByUserId(user.getUserId());
        return connections.stream().map(ConnectionListDto::from)
                .toList();
    }

    //인맥 상세 조회
    public ConnectionResponseDto getConnection(User user, Long connectionId) {
        Connection connection = connectionRepository.findByUserIdAndConnectionId(user.getUserId(), connectionId)
                .orElseThrow(()-> new CustomException(ErrorCode.CONNECTION_NOT_FOUND));
        return ConnectionResponseDto.from(connection);
    }

    public void deleteConnection(Long connectionId) {
    Connection connection = connectionRepository.findByConnectionId(connectionId)
            .orElseThrow(()-> new CustomException(ErrorCode.CONNECTION_NOT_FOUND));
    connectionRepository.delete(connection);
    }
}
