package com.example.myMusic.common.dto.user;

public class AddsongDTO {
	private boolean islogin=false;
     private Long userid;
     private Long songid;//the true id
     private Long songlistid;
     private Long discussid;
     
	public Long getDiscussid() {
		return discussid;
	}
	public void setDiscussid(Long discussid) {
		this.discussid = discussid;
	}
	public boolean isIslogin() {
		return islogin;
	}
	public void setIslogin(boolean islogin) {
		this.islogin = islogin;
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
	public Long getSonglistid() {
		return songlistid;
	}
	public void setSonglistid(Long songlistid) {
		this.songlistid = songlistid;
	}
     
}
