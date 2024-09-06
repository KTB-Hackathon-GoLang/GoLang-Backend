package golang.chat.domain.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AI 필터링 결과
 */
@Getter
@AllArgsConstructor
public class AIPurifyResponse {

	private List<String> message1;
	private List<String> message2;
}
