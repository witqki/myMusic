package com.example.myMusic.common.dto.music;

import java.util.List;

public class SearchResponseDTO {
     private boolean success;
     private String msg;
     private List<SearchMusicDTO> list;
     
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
	public List<SearchMusicDTO> getList() {
		return list;
	}
	public void setList(List<SearchMusicDTO> list) {
		this.list = list;
	}
     
}
