package gradproject.carefli.connection.controller;

import gradproject.carefli.connection.dto.ConnectionListDto;
import gradproject.carefli.connection.dto.ConnectionRequestDto;
import gradproject.carefli.connection.dto.ConnectionResponseDto;
import gradproject.carefli.connection.dto.ConnectionUpdateRequestDto;
import gradproject.carefli.connection.service.ConnectionService;
import gradproject.carefli.oauth.customAnnotation.AuthUser;
import gradproject.carefli.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connections")
@RequiredArgsConstructor
public class ConnectionController {
    private final ConnectionService connectionService;

    //인맥 등록
    @PostMapping
    public ConnectionResponseDto createConnection(@AuthUser User user, @RequestBody ConnectionRequestDto requestDto) {
        return connectionService.createConnection(user, requestDto);
    }
    //인맥 수정
    @PatchMapping("/{connectionId}")
    public ConnectionResponseDto updateConnection(@AuthUser User user, @PathVariable Long connectionId, @RequestBody ConnectionUpdateRequestDto requestDto) {
        return connectionService.updateConnection(user, connectionId, requestDto);
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
