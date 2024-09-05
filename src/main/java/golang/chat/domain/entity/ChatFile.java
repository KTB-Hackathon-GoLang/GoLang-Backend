package golang.chat.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅방 관련정보 파일 Entity
 *
 * @author : parkjihyeok
 * @since : 2024/09/05
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
