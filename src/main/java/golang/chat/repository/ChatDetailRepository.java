package golang.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import golang.chat.domain.entity.ChatDetail;

/**
 * 채팅방 상황설명 Repository
 *
 * @author : parkjihyeok
 * @since : 2024/09/06
 */
public interface ChatDetailRepository extends JpaRepository<ChatDetail, Long> {

	@Query("select cd from ChatDetail cd where cd.chatroom.chatroomUUID = :chatroomUUID")
	List<ChatDetail> findByChatroomUUID(String chatroomUUID);
}
