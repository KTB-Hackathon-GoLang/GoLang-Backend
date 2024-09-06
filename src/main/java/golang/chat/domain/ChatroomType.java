package golang.chat.domain;

import lombok.Getter;

/**
 * 채팅방 타입
 */
@Getter
public enum ChatroomType {
    CHAT_BOT("AI 채팅"),
    CHAT_PERSON("사람 채팅");
    private final String type;

    ChatroomType(String type) {
        this.type = type;
    }
}
