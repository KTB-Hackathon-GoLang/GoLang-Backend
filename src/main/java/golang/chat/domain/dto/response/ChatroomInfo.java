package golang.chat.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 채팅방 기본 정보
 */
@Getter
@AllArgsConstructor
public class ChatroomInfo {

    private String chatroomName;
    private String chatroomUUID;
}
