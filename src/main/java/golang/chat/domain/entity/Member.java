package golang.chat.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 회원 Entity
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id; // 기본키

    @Column(nullable = false, updatable = false)
    private String username; // 회원 ID
}
