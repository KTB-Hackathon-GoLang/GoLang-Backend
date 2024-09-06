package golang.chat.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import golang.chat.domain.dto.request.AIRequest;
import golang.chat.domain.dto.response.ApiResponse;

/**
 * AI 호출을 위한 FeignClient
 */
@FeignClient(name = "aiClient", url = "${AI.HOST}")
public interface AiClient {

	@PostMapping("/ai/purify")
	ResponseEntity<ApiResponse<List<String>>> purifyMessage(@RequestBody AIRequest request);
}
