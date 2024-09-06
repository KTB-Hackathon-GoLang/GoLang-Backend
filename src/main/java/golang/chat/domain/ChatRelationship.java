package golang.chat.domain;

import lombok.Getter;

/**
 * 채팅 관계
 */
@Getter
public enum ChatRelationship {
    COUPLE("커플"),
    FRIEND("친구"),
    FAMILY("가족"),
    ETC("기타");

    private final String relationship;

    ChatRelationship(String relationship) {
        this.relationship = relationship;
    }
}
