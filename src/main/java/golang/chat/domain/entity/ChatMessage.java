package golang.chat.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import golang.chat.domain.dto.request.ChatMessageRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅 메시지 Entity
 *
 * @author : parkjihyeok
 * @since : 2024/09/05
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE) // createMessage 메서드로만 메시지 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE) // createMessage 메서드로만 메시지 생성
@Builder(access = AccessLevel.PRIVATE) // createMessage 메서드로만 메시지 생성
@Document(collection = "chatMessage")
public class ChatMessage {

	private String chatRoomUUID; // 채팅방 UUID
	private String username; // 전송한 사용자의 ID
	private String message; // 메시지 내용
	private LocalDateTime sendAt; // 메시지 전송시간

	public static ChatMessage createMessage(ChatMessageRequest dto) {
		return ChatMessage.builder()
				.chatRoomUUID(dto.getChatroomUUID())
				.username(dto.getUsername())
				.message(dto.getMessage())
				.sendAt(LocalDateTime.now())
				.build();
	}
}
