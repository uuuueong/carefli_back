package gradproject.carefli.connection.controller;

import gradproject.carefli.connection.dto.ConnectionListDto;
import gradproject.carefli.connection.dto.ConnectionRequestDto;
import gradproject.carefli.connection.dto.ConnectionResponseDto;
import gradproject.carefli.connection.dto.ConnectionUpdateRequestDto;
import gradproject.carefli.connection.service.ConnectionService;
import gradproject.carefli.oauth.customAnnotation.AuthUser;
import gradproject.carefli.user.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/connections")
@RequiredArgsConstructor
public class ConnectionController {
    private final ConnectionService connectionService;

    //인맥 등록
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ConnectionResponseDto createConnection(@AuthUser User user,
                                                  @RequestPart(value = "connectionCreateRequestDto") @Valid ConnectionRequestDto requestDto,
                                                  @RequestPart(value = "connectionImage", required = false) MultipartFile multipartFile) throws IOException {
        return connectionService.createConnection(user, requestDto, multipartFile);
    }

    //인맥 수정
    @PatchMapping("/{connectionId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ConnectionResponseDto updateConnection(@AuthUser User user,
                                                  @PathVariable Long connectionId,
                                                  @RequestPart(value = "connectionUpdateRequestDto") @Valid ConnectionUpdateRequestDto requestDto,
                                                  @RequestPart(value = "connectionImage", required = false) MultipartFile multipartFile) throws IOException {
        return connectionService.updateConnection(user, connectionId, requestDto, multipartFile);
    }

    //인맥 전체 조회
    @GetMapping
    public List<ConnectionListDto> getAllConnections(@AuthUser User user) {
        return connectionService.getAllConnections(user);
    }


    //인맥 상세 조회
    @GetMapping("/{connectionId}")
    public ConnectionResponseDto getConnection(@AuthUser User user, @PathVariable Long connectionId) {
        return connectionService.getConnection(user, connectionId);
    }

    // 인맥 삭제
    @DeleteMapping("/{connectionId}")
    public ResponseEntity<String> deleteConnection(@PathVariable Long connectionId) {
        connectionService.deleteConnection(connectionId);
        return ResponseEntity.ok("connectionId=" + connectionId + " 인맥이 성공적으로 삭제되었습니다.");
    }

}
