package golang.chat.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 채팅방 참여자 Entity
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ChatMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

    public static ChatMember createChatMember(Member member, Chatroom chatroom) {
        return ChatMember.builder()
                .member(member)
                .chatroom(chatroom)
                .build();
    }
}
