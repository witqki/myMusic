package com.example.myMusic.common.dto.discuss;



public class DiscussDTO {
	 private Long id;
	 private String content;//评论的内容
	 private Integer likernumber=null;
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLikernumber() {
		return likernumber;
	}

	public void setLikernumber(Integer likernumber) {
		this.likernumber = likernumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	 
}
