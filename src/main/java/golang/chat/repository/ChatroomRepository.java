package golang.chat.repository;

import golang.chat.domain.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * 채팅방 Repository
 */
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

    @Query("select cr from Chatroom cr where cr.chatroomUUID = :roomUUID")
    Optional<Chatroom> findByRoomUUID(String roomUUID);

    /**
     * username으로 참여자가 참여중인 채팅방 리스트 찾기
     */
    @Query("select cm.chatroom from ChatMember cm where cm.member.username = :username")
    List<Chatroom> findByUsername(String username);
}
