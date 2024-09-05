package golang.chat.domain.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import golang.chat.domain.dto.request.ChatMessageRequest;
import lombok.RequiredArgsConstructor;

/**
 * 레디스 구독 담당 객체
 *
 * @author : parkjihyeok
 * @since : 2024/09/05
 */
@Service
@RequiredArgsConstructor
public class RedisPublisher {

	private final ChannelTopic channelTopic;
	private final RedisTemplate<String, Object> redisTemplate;

	/**
	 * 지정된 Redis 채널로 메시지를 발행
	 */
	public void publish(ChatMessageRequest request) {
		redisTemplate.convertAndSend(channelTopic.getTopic(), request);
	}
}
