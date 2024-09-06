package golang.chat.domain.entity;

import golang.chat.domain.dto.request.ChatMessageRequest;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 채팅 메시지 Entity
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Document(collection = "chatMessage")
public class ChatMessage {

    private String chatRoomUUID; // 채팅방 UUID
    private String username; // 전송한 사용자의 ID
    private String message; // 메시지 내용
    private LocalDateTime sendAt; // 메시지 전송시간

    public static ChatMessage createMessage(ChatMessageRequest request) {
        return ChatMessage.builder()
                .chatRoomUUID(request.getChatroomUUID())
                .username(request.getUsername())
                .message(request.getMessage())
                .sendAt(LocalDateTime.now())
                .build();
    }
}
