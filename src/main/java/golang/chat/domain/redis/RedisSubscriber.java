package golang.chat.domain.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import golang.chat.domain.dto.request.ChatMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

/**
 * Redis에서 구독된 메시지를 처리하고 웹 소켓 클라이언트에게 전송하는 클래스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisSubscriber {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    /**
     * Redis 채널에서 수신된 메시지를 처리
     *
     * @param chatMessageRequest Redis에서 수신된 JSON 문자열 형식의 메시지.
     */
    public void sendMessage(String chatMessageRequest) {
        try {
            ChatMessageRequest chat = objectMapper.readValue(chatMessageRequest, ChatMessageRequest.class);
            // 채팅방을 구독 중인 WebSocket 클라이언트에게 메시지 전송
            messagingTemplate.convertAndSend("/sub/chatrooms/" + chat.getChatroomUUID(), chat);
        } catch (Exception e) {
            log.error("예상치 못한 예외가 발생하였습니다. 내용 = {}", e.getMessage());
        }
    }
}
