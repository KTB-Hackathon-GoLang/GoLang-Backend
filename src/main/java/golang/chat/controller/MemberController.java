package golang.chat.controller;

import golang.chat.domain.dto.response.ApiResponse;
import golang.chat.domain.dto.response.MemberLoginResponse;
import golang.chat.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response) {
        String username = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }

        if (username == null || username.isEmpty()) {
            MemberLoginResponse newMember = memberService.saveRandomMember();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.success("로그인 하였습니다.", newMember));
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/already-logged-in") // 리다이렉트 URL
                .build();
    }
}
