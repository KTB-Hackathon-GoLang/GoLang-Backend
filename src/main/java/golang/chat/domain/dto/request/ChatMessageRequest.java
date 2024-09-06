package golang.chat.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅 전송 객체
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequest {

    private String chatroomUUID; // 채팅방 UUID
    private String username; // 전송한 사용자의 ID
    private String message; // 메시지 내용
}
