package golang.chat.domain.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class MemberLoginResponse {

    private String username; // 유저의 ID

    public static MemberLoginResponse fromEntity(String username) {
        return MemberLoginResponse.builder()
                .username(username)
                .build();
    }
}
