package golang.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import golang.chat.domain.entity.ChatFile;

/**
 * 채팅방 상세정보 파일 Repository
 *
 * @author : parkjihyeok
 * @since : 2024/09/05
 */
public interface ChatFileRepository extends JpaRepository<ChatFile, Long> {
}
