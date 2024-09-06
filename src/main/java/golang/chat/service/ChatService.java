package golang.chat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import golang.chat.domain.dto.request.ChatMessageRequest;
import golang.chat.domain.dto.request.ChatroomJoinRequest;
import golang.chat.domain.dto.request.ChatroomMakeDetailRequest;
import golang.chat.domain.dto.request.ChatroomRequest;
import golang.chat.domain.dto.response.ChatMessageResponse;
import golang.chat.domain.dto.response.ChatroomInfo;
import golang.chat.domain.dto.response.ChatroomResponse;
import golang.chat.domain.entity.ChatDetail;
import golang.chat.domain.entity.ChatFile;
import golang.chat.domain.entity.ChatMember;
import golang.chat.domain.entity.ChatMessage;
import golang.chat.domain.entity.Chatroom;
import golang.chat.domain.entity.Member;
import golang.chat.domain.redis.RedisPublisher;
import golang.chat.repository.ChatDetailRepository;
import golang.chat.repository.ChatFileRepository;
import golang.chat.repository.ChatMemberRepository;
import golang.chat.repository.ChatMessageRepository;
import golang.chat.repository.ChatroomRepository;
import golang.chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

/**
 * 채팅 서비스
 */
@Service
@RequiredArgsConstructor
public class ChatService {

	private final RedisPublisher redisPublisher;
	private final MemberRepository memberRepository;
	private final ChatroomRepository chatroomRepository;
	private final ChatMemberRepository chatMemberRepository;
	private final ChatMessageRepository chatMessageRepository;
	private final ChatFileRepository chatFileRepository;
	private final ChatDetailRepository chatDetailRepository;

	/**
	 * 채팅방 UUID값으로 채팅방의 정보를 찾아 반환합니다.
	 *
	 * @param chatRoomUUID 채팅방 UUID
	 * @return 채팅방의 정보를 담은 DTO
	 */
	public ChatroomResponse findByChatUUID(String chatRoomUUID) {
		Chatroom chatRoom = chatroomRepository.findByRoomUUID(chatRoomUUID)
				.orElseThrow(() -> new IllegalArgumentException("해당 UUID에 해당하는 채팅방을 찾을 수 없습니다."));

		List<String> usernames = chatMemberRepository.findByChatroomUUID(chatRoomUUID)
				.stream()
				.map(cm -> cm.getMember().getUsername())
				.toList();

		return ChatroomResponse.fromEntity(usernames, chatRoom);
	}

	/**
	 * 사용자가 참여중인 채팅방의 정보를 찾아 List로 반환합니다.
	 *
	 * @param username 사용자 ID
	 * @return 채팅방 List
	 */
	public List<ChatroomResponse> findByUsername(String username) {
		// 사용자가 참여한 채팅방 목록을 가져옴
		List<Chatroom> chatRooms = chatroomRepository.findByUsername(username);
		return chatRooms.stream()
				.map(Chatroom::getChatroomUUID)
				.map(this::findByChatUUID)
				.toList();
	}

	/**
	 * 새로운 채팅방을 생성
	 *
	 * @param request 채팅방 생성 DTO
	 */
	@Transactional
	public ChatroomInfo createRoom(ChatroomRequest request) {
		Chatroom chatRoom = Chatroom.createRoom(request);
		chatroomRepository.save(chatRoom);

		Member member = memberRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

		chatMemberRepository.save(ChatMember.createChatMember(member, chatRoom));

		return new ChatroomInfo(chatRoom.getChatroomName(), chatRoom.getChatroomUUID());
	}

	/**
	 * 기존 채팅방에 참가
	 *
	 * @param request 채팅방 참가 DTO
	 */
	@Transactional
	public ChatroomInfo joinRoom(ChatroomJoinRequest request) {
		Chatroom chatroom = chatroomRepository.findByRoomUUID(request.getChatroomUUID())
				.orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다."));

		Member member = memberRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

		chatMemberRepository.save(ChatMember.createChatMember(member, chatroom));

		return new ChatroomInfo(chatroom.getChatroomName(), chatroom.getChatroomUUID());
	}

	/**
	 * 채팅방의 채팅 내역을 불러옵니다.
	 *
	 * @param roomUUID 채팅방 UUID
	 * @param pageable 페이지 정보
	 * @return 해당하는 채팅 내역
	 */
	public List<ChatMessageResponse> findMessage(String roomUUID, Pageable pageable) {
		return chatMessageRepository.findByChatroomUUIDOrderBySendAtDesc(roomUUID, pageable)
				.map(ChatMessageResponse::fromEntity)
				.toList();
	}

	/**
	 * 채팅방 상세정보 입력
	 *
	 * @param request 채팅방 상세정보 요청
	 * @return 채팅방 정보
	 */
	@Transactional
	public ChatroomInfo saveDetails(ChatroomMakeDetailRequest request) {
		Chatroom chatRoom = chatroomRepository.findByRoomUUID(request.getChatroomUUID())
				.orElseThrow(() -> new IllegalArgumentException("해당 UUID에 해당하는 채팅방을 찾을 수 없습니다."));

		if (request.getFilename() != null) {
			ChatFile chatFile = ChatFile.createChatFile(request.getFilename(), chatRoom);
			chatFileRepository.save(chatFile);
		}
		chatDetailRepository.save(ChatDetail.createChatDetail(request, chatRoom));

		return new ChatroomInfo(chatRoom.getChatroomName(), chatRoom.getChatroomUUID());
	}

	/**
	 * 채팅방에 채팅을 발송합니다.
	 *
	 * @param request 채팅 DTO
	 */
	@Transactional
	public void sendChatMessage(ChatMessageRequest request) {
		Chatroom chatroom = chatroomRepository.findByRoomUUID(request.getChatroomUUID())
				.orElseThrow(() -> new IllegalArgumentException("해당 UUID에 해당하는 채팅방을 찾을 수 없습니다."));

		chatroom.updateLastTime(LocalDateTime.now().toString());
		chatMessageRepository.save(ChatMessage.createMessage(request));
		redisPublisher.publish(request);
	}
}
