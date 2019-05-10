package com.example.myMusic.common.web;

public class ExtAjaxResponse {
	private boolean success= true;
	private String msg= "";
	private String name;
	private boolean ismylike=false;
	private Long discussid;
	
	public Long getDiscussid() {
		return discussid;
	}

	public void setDiscussid(Long discussid) {
		this.discussid = discussid;
	}

	public boolean isIsmylike() {
		return ismylike;
	}

	public void setIsmylike(boolean ismylike) {
		this.ismylike = ismylike;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
