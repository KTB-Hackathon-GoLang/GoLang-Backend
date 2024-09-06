package golang.chat.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import golang.chat.domain.dto.request.AIRequest;
import golang.chat.domain.dto.response.AIResponse;
import golang.chat.domain.dto.response.ApiResponse;

/**
 * AI 호출을 위한 FeignClient
 */
@FeignClient(name = "aiClient", url = "${AI.HOST}")
public interface AiClient {

	@PostMapping("/ai/purify")
	ResponseEntity<ApiResponse<AIResponse>> purifyMessage(@RequestBody AIRequest request);

	@PostMapping("/ai/summarize")
	ResponseEntity<ApiResponse<AIResponse>> summarizeMessage(@RequestBody AIRequest request);

	@PostMapping("/ai/chat")
	ResponseEntity<ApiResponse<String>> chatAI(@RequestBody AIRequest request);
}
