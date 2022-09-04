package site.metacoding.red.domain.boards;

import java.sql.Timestamp;

import lombok.Getter;
import site.metacoding.red.web.dto.reqest.boards.UpdateDto;

@Getter
public class Boards {
	private Integer id;
	private String title;
	private String content;
	private Integer userId;
	private Timestamp createdAt;
	
	public void 글전체수정(UpdateDto updateDto) {
		this.title = updateDto.getTitle();
		this.content = updateDto.getContent();
	}
}
