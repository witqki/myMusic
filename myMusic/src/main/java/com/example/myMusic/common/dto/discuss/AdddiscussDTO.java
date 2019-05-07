package com.example.myMusic.common.dto.discuss;

public class AdddiscussDTO {
	private Long discussid;
     private Long userid;
     private Long songid;
     private String content;
     private boolean isLogin=false;
     
	public Long getDiscussid() {
		return discussid;
	}
	public void setDiscussid(Long discussid) {
		this.discussid = discussid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getSongid() {
		return songid;
	}
	public void setSongid(Long songid) {
		this.songid = songid;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
     
}
