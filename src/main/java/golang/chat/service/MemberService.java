package golang.chat.service;

import golang.chat.domain.dto.response.MemberLoginResponse;
import golang.chat.domain.entity.Member;
import golang.chat.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 랜덤한 username 생성 후 멤버를 저장
     */
    public MemberLoginResponse saveRandomMember() {
        String random = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        String randomUsername = "User" + random;
        Member newMember = Member.builder()
                .username(randomUsername)
                .build();
        Member savedMember = memberRepository.save(newMember);

        return MemberLoginResponse.fromEntity(savedMember.getUsername());
    }
}
