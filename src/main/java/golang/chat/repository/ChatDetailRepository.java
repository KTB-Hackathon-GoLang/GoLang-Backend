package golang.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import golang.chat.domain.entity.ChatDetail;

/**
 * 채팅방 상황설명 Repository
 *
 * @author : parkjihyeok
 * @since : 2024/09/06
 */
public interface ChatDetailRepository extends JpaRepository<ChatDetail, Long> {
}
