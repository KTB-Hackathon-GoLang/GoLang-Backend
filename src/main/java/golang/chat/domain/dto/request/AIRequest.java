package golang.chat.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import golang.chat.domain.ChatType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AI 호출용 객체
 */
@Getter
@AllArgsConstructor
public class AIRequest {

	private String username;
	private String relation;
	@JsonProperty("chatroom_uuid")
	private String chatroomUUID;
	@JsonProperty("chat_message")
	private String chatMessage;
	@JsonProperty("chat_type")
	private ChatType chatType;
}
