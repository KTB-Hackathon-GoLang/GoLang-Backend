package golang.chat.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 채팅방 참가 요청
 *
 * @author : parkjihyeok
 * @since : 2024/09/06
 */
@Getter
@AllArgsConstructor
public class ChatroomJoinRequest {

	private String chatroomUUID;
	private String username;
}
