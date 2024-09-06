package golang.chat.domain.dto.request;

import golang.chat.domain.ChatRelationship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅방 상세정보 요청
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatroomMakeDetailRequest {

    private String chatroomUUID;
    private String chatroomDetails;
    private String filename;
    private ChatRelationship relationship;
}
