package golang.chat.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * AI 호출용 객체
 */
@Getter
// @Setter TODO: openfeign에 setter가 필요한지 테스트하고 정리하기
@AllArgsConstructor
public class AIRequest {

	@JsonProperty("user_name")
	private String userName; // 전송자 아이디
	private String relationship; // 채팅방 관계
	private String message; // 요청할 메시지
}
