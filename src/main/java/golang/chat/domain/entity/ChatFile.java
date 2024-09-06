package golang.chat.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 채팅방 관련정보 파일 Entity
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ChatFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;

    @ManyToOne
    @JoinColumn(nullable = false, name = "chatroom_id")
    private Chatroom chatroom;

    public static ChatFile createChatFile(String filename, Chatroom chatroom) {
        return ChatFile.builder()
                .filename(filename)
                .chatroom(chatroom)
                .build();
    }
}
