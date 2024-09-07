package golang.chat.domain.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * AI 필터링 결과
 */
@Getter
@NoArgsConstructor
public class AIResponse {

	private List<String> messages;


	@JsonCreator
	public AIResponse(@JsonProperty("messages") List<String> messages) {
		this.messages = messages;
	}

}
