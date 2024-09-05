package golang.chat.domain.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import golang.chat.domain.ChatroomType;
import golang.chat.domain.dto.request.ChatroomRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅방 Entity
 *
 * @author : parkjihyeok
 * @since : 2024/09/05
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // createRoom 메서드로만 메시지 생성, Entity이기 때문에 PROTECTED로 설정
@AllArgsConstructor(access = AccessLevel.PRIVATE) // createRoom 메서드로만 메시지 생성
@Builder(access = AccessLevel.PRIVATE) // createRoom 메서드로만 메시지 생성
public class Chatroom extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chat_room_id")
	private Long id; // 기본키
	@Column(name = "chat_room_name", nullable = false, updatable = false)
	private String chatroomName; // 채팅방 이름
	@Column(name = "chat_room_uuid", nullable = false, updatable = false)
	private String chatroomUUID; // 채팅방 UUID
	@Enumerated(EnumType.STRING)
	private ChatroomType chatroomType;
	@Column(name = "last_time")
	private String lastTime; // 마지막 채팅시간

	// TODO: 2024/09/5 채팅이 종료된 후 세팅할 결과값 (메서드까지 함께 만들어야함)
	// private Result result; // 채팅이 종료된 후 결과값
	/**
	 * 채팅방을 생성합니다.
	 *
	 * @param request 채팅방이름
	 * @return 생성한 채팅방 객체
	 */
	public static Chatroom createRoom(ChatroomRequest request) {
		return Chatroom.builder()
				.chatroomName(request.getChatroomName())
				.chatroomUUID(UUID.randomUUID().toString())
				.chatroomType(request.getChatroomType())
				.lastTime(LocalDateTime.now().toString())
				.build();
	}

	/**
	 * 마지막 채팅시간 변경
	 */
	public void updateLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
}
