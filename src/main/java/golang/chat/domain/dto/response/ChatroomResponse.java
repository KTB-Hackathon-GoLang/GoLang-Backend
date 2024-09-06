package golang.chat.domain.dto.response;

import golang.chat.domain.entity.Chatroom;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    /**
     * 채팅방 Entity List를 전달받아 채팅방의 간단한 정보만 담고있는 리스트를 반환합니다.
     *
     * @param usernamesMap 채팅방 ID와 참여자 이름 리스트를 매핑한 Map
     * @param chatRooms    채팅방 Entity List
     * @return 생성된 List
     */
    public static List<ChatroomResponse> fromEntityList(Map<Long, List<String>> usernamesMap, List<Chatroom> chatRooms) {
        return chatRooms.stream()
                .map(chatRoom -> ChatroomResponse.builder()
                        .usernames(usernamesMap.getOrDefault(chatRoom.getId(), Collections.emptyList()))
                        .chatRoomUUID(chatRoom.getChatroomUUID())
                        .chatRoomName(chatRoom.getChatroomName())
                        .lastTime(chatRoom.getLastTime())
                        .build())
                .collect(Collectors.toList());
    }
}
