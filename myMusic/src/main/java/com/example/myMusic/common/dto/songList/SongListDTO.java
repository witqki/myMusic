package com.example.myMusic.common.dto.songList;

import java.util.List;

import com.example.myMusic.music.entities.Music;
import com.example.myMusic.user.entities.User;

public class SongListDTO {
	 private Long songListId;
	 private String name;
   //  private String picturepath;//图片地址
     private Integer songNumber;//歌曲数量
     private String userName;
     private boolean isOpen=false;//是否公开
     private String intro;//简介
     
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getSongListId() {
		return songListId;
	}
	public void setSongListId(Long songListId) {
		this.songListId = songListId;
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
	public Integer getSongNumber() {
		return songNumber;
	}
	public void setSongNumber(Integer songNumber) {
		this.songNumber = songNumber;
	}
     
 
}
