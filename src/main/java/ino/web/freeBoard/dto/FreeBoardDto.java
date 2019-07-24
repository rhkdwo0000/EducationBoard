package ino.web.freeBoard.dto;

import org.apache.ibatis.type.Alias;

@Alias("freeBoardDto")
public class FreeBoardDto {

	private int origin_num;
	private String name;
	private String title;
	private String content;
	private String new_day;

	public FreeBoardDto() {
	}

	public int getOrigin_num() {
		return origin_num;
	}

	public void setOrigin_num(int origin_num) {
		this.origin_num = origin_num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNew_day() {
		return new_day;
	}

	public void setNew_day(String new_day) {
		this.new_day = new_day;
	}

	
}
