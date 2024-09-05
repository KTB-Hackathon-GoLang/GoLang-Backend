package golang.chat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import golang.chat.domain.entity.Member;

/**
 * 회원 Repository
 *
 * @author : parkjihyeok
 * @since : 2024/09/05
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByUsername(String username);
}
