package com.example.myMusic.common.dto.adminUser;

public class UserDTO {
     private Long id;
     private String name;
    // private String picturepath;
     private boolean isWrite=true;//是否能发评论
    // private boolean isApply=true;//能否申请歌单分享
     private boolean isAdmin=false;//是否管理员
     
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public String getPicturepath() {
//		return picturepath;
//	}
//	public void setPicturepath(String picturepath) {
//		this.picturepath = picturepath;
//	}
	public boolean isWrite() {
		return isWrite;
	}
	public void setWrite(boolean isWrite) {
		this.isWrite = isWrite;
	}
//	public boolean isApply() {
//		return isApply;
//	}
//	public void setApply(boolean isApply) {
//		this.isApply = isApply;
//	}
     
}
