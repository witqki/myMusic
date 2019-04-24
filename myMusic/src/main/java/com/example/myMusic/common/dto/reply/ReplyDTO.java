package com.example.myMusic.common.dto.reply;

public class ReplyDTO {
	 private Long id;
	 private String content;//评论的内容
	 private Integer likernumber=null;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getLikernumber() {
		return likernumber;
	}
	public void setLikernumber(Integer likernumber) {
		this.likernumber = likernumber;
	}
}
