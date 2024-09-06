package golang.chat.domain.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AI 필터링 결과
 */
@Getter
@AllArgsConstructor
public class AIResponse {

	private List<String> messages;
}
