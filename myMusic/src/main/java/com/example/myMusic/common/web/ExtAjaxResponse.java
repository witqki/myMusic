package com.example.myMusic.common.web;

public class ExtAjaxResponse {
	private boolean success= true;
	private String msg= "";
	
	public ExtAjaxResponse() {}
	
	public ExtAjaxResponse(boolean success) {
		this.success = success;
	}
	
	public ExtAjaxResponse(boolean success,String msg) {
		this.success = success;
		this.msg = msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public boolean isSuccess() {
		return success;
	}
	public String getMsg() {
		return msg;
	}	
}
