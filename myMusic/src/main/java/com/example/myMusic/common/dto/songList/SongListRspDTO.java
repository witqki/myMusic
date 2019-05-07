package com.example.myMusic.common.dto.songList;

import java.util.List;

public class SongListRspDTO {
     private  boolean  success;
     private String msg;
     private List<SongListDTO> songList=null;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<SongListDTO> getSongList() {
		return songList;
	}
	public void setSongList(List<SongListDTO> songList) {
		this.songList = songList;
	}
     
}
