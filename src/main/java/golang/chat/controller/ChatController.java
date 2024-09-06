package golang.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import golang.chat.domain.dto.request.ChatAIRequest;
import golang.chat.domain.dto.request.ChatMessageRequest;
import golang.chat.domain.dto.request.ChatroomJoinRequest;
import golang.chat.domain.dto.request.ChatroomMakeDetailRequest;
import golang.chat.domain.dto.request.ChatroomRequest;
import golang.chat.domain.dto.response.AIResponse;
import golang.chat.domain.dto.response.ApiResponse;
import golang.chat.domain.dto.response.ChatMessageResponse;
import golang.chat.domain.dto.response.ChatroomInfo;
import golang.chat.domain.dto.response.ChatroomResponse;
import golang.chat.service.ChatAIService;
import golang.chat.service.ChatService;
import golang.chat.service.FileService;

/**
 * 채팅을 담당하는 컨트롤러
 */
@RestController
@RequestMapping("/api")
public class ChatController {

	private final ChatService chatService;
	private final FileService fileService;
	private final String FILE_PATH;
	private final ChatAIService chatAIService;

	public ChatController(ChatService chatService, FileService fileService, @Value("${FILE_PATH}") String FILE_PATH,
			ChatAIService chatAIService) {
		this.chatService = chatService;
		this.fileService = fileService;
		this.FILE_PATH = FILE_PATH;
		this.chatAIService = chatAIService;
	}

	/**
	 * username으로 참여했던 채팅방 리스트 조회
	 *
	 * @param username 사용자 ID
	 * @return 참여했더 채팅방 리스트
	 */
	@GetMapping("/chatrooms")
	public ResponseEntity<ApiResponse<List<ChatroomResponse>>> findChatRoomListByUsernameChat(
			@RequestParam String username) {
		List<ChatroomResponse> responses = chatService.findByUsername(username);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.success(responses));
	}

	/**
	 * 채팅방 상세 정보 조회
	 *
	 * @param roomUUID 채팅방 UUID
	 * @return 채팅방 상세 정보
	 */
	@GetMapping("/chatrooms/details")
	public ResponseEntity<ApiResponse<ChatroomResponse>> findChatRoomDetails(@RequestParam String roomUUID) {
		ChatroomResponse response = chatService.findByChatUUID(roomUUID);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.success(response));
	}

	/**
	 * 채팅방 생성
	 *
	 * @param chatRoomRequest 채팅방 생성에 필요한 DTO
	 * @return 처리 결과
	 */
	@PostMapping("/chatrooms")
	public ResponseEntity<ApiResponse<ChatroomInfo>> createRoom(@RequestBody ChatroomRequest chatRoomRequest) {
		ChatroomInfo response = chatService.createRoom(chatRoomRequest);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.success("채팅방을 생성하였습니다.", response));
	}

	/**
	 * 채팅방 참가
	 *
	 * @param request 채팅방 참가에 필요한 DTO
	 * @return 처리 결과
	 */
	@PostMapping("/chatrooms/join")
	public ResponseEntity<ApiResponse<ChatroomInfo>> joinRoom(@RequestBody ChatroomJoinRequest request) {
		ChatroomInfo response = chatService.joinRoom(request);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.success("채팅방에 참가하였습니다.", response));
	}

	/**
	 * 파일 저장
	 *
	 * @param file 파일 정보
	 * @return 저장된 파일이름
	 */
	@PostMapping("/chatrooms/details/file")
	public ResponseEntity<ApiResponse<String>> saveFile(MultipartFile file) {
		String filename = fileService.saveFile(file, FILE_PATH);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.success(filename));
	}

	/**
	 * 채팅방 상세정보 입력
	 *
	 * @param request 채팅방 생성자의 상세정보 입력
	 * @return 채팅방 정보
	 */
	@PostMapping("/chatrooms/details")
	public ResponseEntity<ApiResponse<ChatroomInfo>> saveDetails(@RequestBody ChatroomMakeDetailRequest request) {
		ChatroomInfo response = chatService.saveDetails(request);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.success(response));
	}

	/**
	 * 채팅방 상세정보 입력
	 *
	 * @param request 채팅방 참가자의 상세정보 입력
	 * @return 채팅방 정보
	 */
	@PostMapping("/chatrooms/details/join")
	public ResponseEntity<ApiResponse<?>> saveOtherDetails(@RequestBody ChatroomMakeDetailRequest request) {
		ChatroomInfo response = chatService.saveDetails(request);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.success(response));
	}

	/**
	 * 채팅방의 채팅 내역 불러오기
	 *
	 * @param chatroomUUID 채팅방 UUID
	 * @param pageable     페이지 정보
	 * @return 채팅내역
	 */
	@GetMapping("/chatrooms/messages")
	public ResponseEntity<ApiResponse<List<ChatMessageResponse>>> findMessages(@RequestParam String chatroomUUID,
			Pageable pageable) {

		List<ChatMessageResponse> message = chatService.findMessage(chatroomUUID, pageable);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.success(message));
	}

	/**
	 * 채팅 AI 기능 API
	 * @param request 채팅 AI 요청
	 * @return 처리결과
	 */
	@PostMapping("/chatrooms/ai")
	public ResponseEntity<ApiResponse<AIResponse>> callAIClient(@RequestBody ChatAIRequest request) {

		return chatAIService.callAI(request);
	}

	/**
	 * 가상채팅 AI API
	 * @param request 채팅 AI 요청
	 * @return 처리결과
	 */
	@PostMapping("/chatrooms/ai-chat")
	public ResponseEntity<ApiResponse<String>> callChatAIClient(@RequestBody ChatAIRequest request) {

		return chatAIService.callChatAI(request);
	}

	// TODO : finish api 추가
	// @PostMapping("/chatrooms/finish")

	/**
	 * 웹소켓의 메세지를 처리
	 *
	 * @param message 메시지
	 */
	@MessageMapping("/messages")
	public void sendMessage(@RequestBody ChatMessageRequest message) {
		chatService.sendChatMessage(message);
	}
}
