package golang.chat.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 기본 Entity
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private String createdAt;

    @Column(name = "modified_at", nullable = false)
    @LastModifiedDate
    private String modifiedAt;

    // 날짜 포멧팅
    @PrePersist // 해당 엔티티를 저장하기 전에 실행
    public void onPrePersist() {
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        this.modifiedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    // 업데이트 날짜 포멧팅
    @PreUpdate  // 해당 엔티티를 업데이트하기 전에 실행
    public void onPreUpdate() {
        this.modifiedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
