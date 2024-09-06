package golang.chat.domain.dto.request;

import golang.chat.domain.ChatroomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅방 생성 요청 객체
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatroomRequest {

    private String username;
    private String chatroomName;
    private ChatroomType chatroomType;
}
