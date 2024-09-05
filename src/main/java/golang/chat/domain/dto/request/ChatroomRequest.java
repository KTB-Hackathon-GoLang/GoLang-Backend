package golang.chat.domain.dto.request;

import java.util.List;

import golang.chat.domain.ChatroomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅방 생성 요청 객체
 *
 * @author : parkjihyeok
 * @since : 2024/09/05
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatroomRequest {

	private String username;
	private String chatroomName;
	private ChatroomType chatroomType;
}
