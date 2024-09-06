package golang.chat.domain.dto.response;

import java.util.List;

import golang.chat.domain.entity.Chatroom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 채팅방 응답 객체
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ChatroomResponse {

	private List<String> usernames;
	private String chatRoomUUID;
	private String chatRoomName;
	private String lastTime; // 마지막 채팅 전송 시간

	/**
	 * 하나의 채팅방에 대한 정보를 담은 객체를 생성해 반환합니다.
	 *
	 * @param usernames 채팅방 참여자 정보
	 * @param chatroom  채팅방 Entity
	 * @return 채팅방 정보를 담은 DTO
	 */
	public static ChatroomResponse fromEntity(List<String> usernames, Chatroom chatroom) {
		return ChatroomResponse.builder()
				.usernames(usernames)
				.chatRoomUUID(chatroom.getChatroomUUID())
				.chatRoomName(chatroom.getChatroomName())
				.lastTime(chatroom.getLastTime())
				.build();
	}
}
