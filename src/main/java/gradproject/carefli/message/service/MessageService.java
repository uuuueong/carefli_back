package gradproject.carefli.message.service;

import gradproject.carefli.connection.domain.Connection;
import gradproject.carefli.connection.repository.ConnectionRepository;
import gradproject.carefli.message.domain.Message;
import gradproject.carefli.message.dto.MessageRequestDto;
import gradproject.carefli.message.dto.MessageResponseDto;
import gradproject.carefli.message.repository.MessageRepository;
import gradproject.carefli.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ConnectionRepository connectionRepository;

    //문구 저장
    @Transactional
    public MessageResponseDto saveMessage(User user, MessageRequestDto requestDto) {
        Connection connection = null;
        if (requestDto.getConnectionId() != null) {
            connection = connectionRepository.findById(requestDto.getConnectionId())
                    .orElseThrow(()-> new IllegalStateException("Connection not found"));
        }

        Message message = Message.builder()
                .user(user)
                .connection(connection)
                .occasionType(requestDto.getOccasionType())
                .tone(requestDto.getTone())
                .length(requestDto.getLength())
                .text(requestDto.getText())
                .build();

        Message savedMessage = messageRepository.save(message);
        return convertToDto(savedMessage);
    }

    //문구 조회
    @Transactional(readOnly = true)
    public List<MessageResponseDto> getAllMessagesByUserId(User user, Long userId) {
        if (!user.getUserId().equals(userId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        List<Message> messages = messageRepository.findByUserId(userId);
        return messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //인맥별 문구 조회
    @Transactional(readOnly = true)
    public List<MessageResponseDto> getAllMessagesByConnectionId(User user, Long connectionId) {
        if (!user.getUserId().equals(connectionId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        List<Message> messages = messageRepository.findByConnectionIdOrderByUpdatedAtDesc(connectionId);
        return messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //문구 수정
    @Transactional
    public MessageResponseDto updateMessage(User user, Long messageId, String text) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(()-> new IllegalStateException("Message not found"));

        if (!message.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        message.updateContent(text);
        return convertToDto(message);
    }

    //문구 삭제
    @Transactional
    public void deleteMessage(User user, Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalStateException("Message not found"));

        if (!message.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        messageRepository.delete(message);
    }

    private MessageResponseDto convertToDto(Message message) {
        return MessageResponseDto.builder()
                .messageId(message.getMessageId())
                .userId(message.getUser().getUserId())
                .connectionId(message.getConnection() != null? message.getConnection().getConnectionId() : null)
                .occasionType(message.getOccasionType())
                .tone(message.getTone())
                .length(message.getLength())
                .content(message.getText())
                .createdAt(message.getCreatedAt())
                .updatedAt(message.getUpdatedAt())
                .build();
    }
}
