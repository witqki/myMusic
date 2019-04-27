package com.example.myMusic.common.util;

import java.util.ArrayList;
import java.util.List;

public class FilelistDTO {
     private boolean success=true;
     private String msg;
     private List<String> filelist=new ArrayList<String>();
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
	public List<String> getFilelist() {
		return filelist;
	}
	public void setFilelist(List<String> filelist) {
		this.filelist = filelist;
	}
     
}
