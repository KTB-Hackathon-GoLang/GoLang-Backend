package golang.chat.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import golang.chat.domain.ChatType;
import golang.chat.domain.dto.request.AIRequest;
import golang.chat.domain.dto.request.ChatAIRequest;
import golang.chat.domain.dto.response.AIResponse;
import golang.chat.domain.dto.response.ApiResponse;
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
	public ResponseEntity<ApiResponse<AIResponse>> callAI(ChatAIRequest request) {
		String relation = getRelation(request);

		chatMessageRepository.save(ChatMessage.createMessage(request));

		// 필터링인 경우
		if (request.getChatType() == ChatType.CHAT_FIlTER) {
			return aiClient.purifyMessage(new AIRequest(request.getUsername(), relation, request.getChatroomUUID(),
					request.getChatMessage(), request.getChatType()));
		}

		// 요약인 경우
		return aiClient.summarizeMessage(new AIRequest(request.getUsername(), relation, request.getChatroomUUID(),
				request.getChatMessage(), request.getChatType()));
	}

	/** 가상채팅 요청을 받아 AI 호출 메서드
	 *
	 * @param request 채팅 AI 요청
	 * @return 처리결과
	 */
	public ResponseEntity<ApiResponse<String>> callChatAI(ChatAIRequest request) {
		String relation = getRelation(request);

		chatMessageRepository.save(ChatMessage.createMessage(request));

		return aiClient.chatAI(new AIRequest(request.getUsername(), relation, request.getChatroomUUID(),
				request.getChatMessage(), request.getChatType()));
	}

	private String getRelation(ChatAIRequest request) {
		return chatDetailRepository.findByChatroomUUID(request.getChatroomUUID())
				.get(0).getRelationship().toString();
	}
}
