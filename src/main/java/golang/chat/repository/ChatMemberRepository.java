package golang.chat.repository;

import golang.chat.domain.entity.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 채팅방 참여자 Repository
 */
public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {

    /**
     * 채팅방 UUID로 채팅방 참여자들 찾기
     */
    @Query("select cm from ChatMember cm join fetch cm.member m where cm.chatroom.chatroomUUID = :roomUUID")
    List<ChatMember> findByChatroomUUID(String roomUUID);

}
