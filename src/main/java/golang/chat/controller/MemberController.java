package golang.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import golang.chat.domain.dto.response.ApiResponse;
import golang.chat.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/login")
	public ResponseEntity<?> login() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponse.success("로그인 하였습니다.", memberService.saveRandomMember()));
	}
}
