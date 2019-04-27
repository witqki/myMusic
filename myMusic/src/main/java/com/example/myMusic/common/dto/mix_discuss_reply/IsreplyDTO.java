package com.example.myMusic.common.dto.mix_discuss_reply;

public class IsreplyDTO {
	private String content;//内容
    private Long userId;
    private String username;
	 public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
