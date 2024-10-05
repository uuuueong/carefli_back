package gradproject.carefli.message.controller;

import gradproject.carefli.message.dto.MessageRequestDto;
import gradproject.carefli.message.dto.MessageResponseDto;
import gradproject.carefli.message.dto.MessageUpdateRequestDto;
import gradproject.carefli.message.service.MessageService;
import gradproject.carefli.oauth.customAnnotation.AuthUser;
import gradproject.carefli.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    //문구 저장
    @PostMapping
    public ResponseEntity<MessageResponseDto> saveMessage(@AuthUser User user, @RequestBody MessageRequestDto requestDto) {
        return ResponseEntity.ok(messageService.saveMessage(user, requestDto));
    }

    //문구 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<MessageResponseDto>> getMessages(@AuthUser User user, @PathVariable Long userId) {
        List<MessageResponseDto> messages = messageService.getAllMessagesByUserId(user, userId);
        return ResponseEntity.ok(messages);
    }

    //문구 수정
    @PatchMapping("/{messageId}")
    public ResponseEntity<MessageResponseDto> updateMessage(@AuthUser User user, @PathVariable Long messageId, @RequestBody MessageUpdateRequestDto requestDto) {
        MessageResponseDto updatedMessage = messageService.updateMessage(user, messageId, requestDto.getContent());
        return ResponseEntity.ok(updatedMessage);
    }

    //문구 삭제
    @DeleteMapping("/{messageId}")
    public ResponseEntity<String> deleteMessage(@AuthUser User user, @PathVariable Long messageId) {
        messageService.deleteMessage(user, messageId);
        return ResponseEntity.ok("성공적으로 삭제되었습니다.");
    }


}
