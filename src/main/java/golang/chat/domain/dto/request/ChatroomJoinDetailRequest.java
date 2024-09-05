package golang.chat.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅방 상세정보 요청
 *
 * @author : parkjihyeok
 * @since : 2024/09/05
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatroomJoinDetailRequest {

	private String chatroomUUID;
	private String chatroomDetails;
	private String myRole;
	private String yourRole;
}
