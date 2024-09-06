package golang.chat.domain;

import lombok.Getter;

/**
 * 채팅 타입
 */
@Getter
public enum ChatType {
    CHAT_FIlTER("채팅 필터"),
    CHAT_SUMMARY("채팅 정리");
    private final String type;

    ChatType(String type) {
        this.type = type;
    }
}
