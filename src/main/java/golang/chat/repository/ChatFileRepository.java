package golang.chat.repository;

import golang.chat.domain.entity.ChatFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 채팅방 상세정보 파일 Repository
 */
public interface ChatFileRepository extends JpaRepository<ChatFile, Long> {
}
