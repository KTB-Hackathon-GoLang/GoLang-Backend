package golang.chat.domain.dto.request;

import golang.chat.domain.ChatType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 채팅 AI 기능 요청 객체
 */
@Getter
@AllArgsConstructor
public class ChatAIRequest {

	private String username;
	private String chatroomUUID;
	private String chatMessage;
	private ChatType chatType;
}
