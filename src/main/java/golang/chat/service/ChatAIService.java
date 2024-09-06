package golang.chat.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import golang.chat.domain.ChatType;
import golang.chat.domain.dto.request.AIRequest;
import golang.chat.domain.dto.request.ChatAIRequest;
import golang.chat.domain.dto.response.AIPurifyResponse;
import golang.chat.domain.dto.response.ApiResponse;
import golang.chat.domain.entity.ChatDetail;
import golang.chat.domain.entity.ChatMessage;
import golang.chat.repository.ChatDetailRepository;
import golang.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;

/**
 * 채팅 AI 요청을 담당하는 서비스
 */
@Service
@RequiredArgsConstructor
public class ChatAIService {

	private final AiClient aiClient;
	private final ChatDetailRepository chatDetailRepository;
	private final ChatMessageRepository chatMessageRepository;

	/** 채팅 AI 요청을 받아 AI 호출 메서드
	 *
	 * @param request 채팅 AI 요청
	 * @return 처리결과
	 */
	public ResponseEntity<ApiResponse<AIPurifyResponse>> callAI(ChatAIRequest request) {
		List<ChatDetail> chatDetails = chatDetailRepository.findByChatroomUUID(request.getChatroomUUID());

		chatMessageRepository.save(ChatMessage.createMessage(request));

		if (request.getChatType() == ChatType.CHAT_FIlTER) {
			return aiClient.purifyMessage(
					new AIRequest(request.getUsername(), chatDetails.get(0).getRelationship().toString(),
							request.getChatroomUUID(),
							request.getChatMessage(), request.getChatType()));
		}
		// TODO: 2024/09/6 요약 처리 로직 추가하기
		return null;
	}
}
