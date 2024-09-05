package golang.chat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import golang.chat.domain.entity.ChatMessage;

/**
 * 채팅방 Repository
 *
 * @author : parkjihyeok
 * @since : 2024/09/05
 */
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

	/**
	 * 채팅방 메시지를 시간으로 정렬하여 페이지로 가져옵니다.
	 *
	 * @param roomUUID 채팅방 UUID
	 * @param pageable 페이지 정보
	 * @return 해당 페이지
	 */
	Page<ChatMessage> findByChatRoomUUIDOrderBySendAtDesc(String roomUUID, Pageable pageable);
}
