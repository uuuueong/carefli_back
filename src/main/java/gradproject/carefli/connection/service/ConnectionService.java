package gradproject.carefli.connection.service;

import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.connection.dto.ConnectionListDto;
import gradproject.carefli.connection.dto.ConnectionRequestDto;
import gradproject.carefli.connection.dto.ConnectionResponseDto;
import gradproject.carefli.connection.dto.ConnectionUpdateRequestDto;
import gradproject.carefli.connection.repository.ConnectionRepository;
import gradproject.carefli.global.exception.CustomException;
import gradproject.carefli.global.exception.ErrorCode;
import gradproject.carefli.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class ConnectionService {
    private final ConnectionRepository connectionRepository;

    //인맥 등록
    public ConnectionResponseDto createConnection(User user, ConnectionRequestDto requestDto) {
        Connection connection = requestDto.toEntity(user);
        Connection savedConnection = connectionRepository.save(connection);
        return ConnectionResponseDto.from(savedConnection);
    }

    //인맥 수정
    public ConnectionResponseDto updateConnection(User user, Long connectionId, ConnectionUpdateRequestDto requestDto) {
        Connection connection = connectionRepository.findByConnectionId(connectionId)
                .orElseThrow(() -> new CustomException(ErrorCode.CONNECTION_NOT_FOUND));
        connection.updateConnection(requestDto);
        Connection updateConnection = connectionRepository.save(connection);
        return ConnectionResponseDto.from(updateConnection);
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
