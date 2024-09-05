package golang.chat.domain;

import lombok.Getter;

/**
 * 채팅 관계
 *
 * @author : parkjihyeok
 * @since : 2024/09/05
 */
@Getter
public enum ChatRelationship {
	COUPLE("커플"),
	MARRY("부부"),
	FAMILY("가족"),
	ETC("기타");

	private final String relationship;

	ChatRelationship(String relationship) {
		this.relationship = relationship;
	}
}
