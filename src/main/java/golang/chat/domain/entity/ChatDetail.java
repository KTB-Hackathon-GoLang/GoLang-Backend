package golang.chat.domain.entity;

import golang.chat.domain.ChatRelationship;
import golang.chat.domain.dto.request.ChatroomMakeDetailRequest;
import jakarta.persistence.*;
import lombok.*;

/**
 * 채팅방 상세정보 Entity
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ChatDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

    private String detail;
    private ChatRelationship relationship;

    public static ChatDetail createChatDetail(ChatroomMakeDetailRequest request) {
        return ChatDetail.builder()
                .detail(request.getChatroomDetails())
                .relationship(request.getRelationship())
                .build();
    }
}
