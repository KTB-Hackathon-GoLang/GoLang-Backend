package golang.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import golang.chat.domain.redis.RedisSubscriber;
import lombok.RequiredArgsConstructor;

/**
 * 레디스 설정파일
 *
 * @author : parkjihyeok
 * @since : 2024/09/05
 */
@Configuration
@RequiredArgsConstructor
public class RedisConfig {

	private final RedisConnectionFactory redisConnectionFactory;

	/**
	 * RedisTemplate 빈 생성
	 *
	 * @param connectionFactory RedisConnectionFactory 주입된 RedisConnectionFactory 인스턴스
	 * @return RedisTemplate<String, Object> Redis에서 데이터를 처리하는 데 사용되는 템플릿
	 */
	@Bean
	public RedisTemplate<String, Object> redisChatTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory); // Redis 연결 팩토리 설정
		template.setKeySerializer(new StringRedisSerializer()); // 키를 문자열로 직렬화
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // 값을 JSON 형식으로 직렬화
		template.setHashKeySerializer(new StringRedisSerializer()); // 해시 구조의 키를 문자열로 직렬화
		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer()); // 해시 구조의 값을 JSON 형식으로 직렬화
		template.afterPropertiesSet(); // 설정된 직렬화기들을 적용하여 템플릿 초기화

		return template;
	}

	/**
	 * Pub/Sub 채널 정의
	 *
	 * @return ChannelTopic "chatroom"이라는 이름을 가진 채널
	 */
	@Bean
	public ChannelTopic channelTopic() {
		return new ChannelTopic("chatroom");
	}

	/**
	 * RedisMessageListenerContainer 빈 생성
	 *
	 * @param listenerAdapterChatMessage Redis에서 수신된 메시지를 처리하는 리스너 어댑터
	 * @param channelTopic 메시지를 구독할 채널
	 * @return RedisMessageListenerContainer 메시지 리스너 컨테이너
	 */
	@Bean
	public RedisMessageListenerContainer redisMessageListener(
			MessageListenerAdapter listenerAdapterChatMessage,
			ChannelTopic channelTopic
	) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory); // Redis 연결 팩토리 설정
		container.addMessageListener(listenerAdapterChatMessage, channelTopic); // 리스너와 채널을 컨테이너에 등록
		return container;
	}

	/**
	 * MessageListenerAdapter 빈 생성
	 *
	 * @param subscriber 실제로 메시지를 처리하는 클래스 (RedisSubscriber)
	 * @return MessageListenerAdapter 메시지 처리 어댑터
	 */
	@Bean
	public MessageListenerAdapter listenerAdapterChatMessage(RedisSubscriber subscriber) {
		// RedisSubscriber 클래스에 정의된 메서드 이름을 2번째 인자로 넘겨야 한다.
		return new MessageListenerAdapter(subscriber, "sendMessage");
	}
}
