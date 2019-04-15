package com.example.myMusic.common.dto.reply;

import java.util.ArrayList;
import java.util.List;



public class ReplyResponseDTO {
	 private boolean success;//请求是否成功
     private String msg="";
	   private int totalpage;//总页数
     private int totalnumber;//总记录数
     private int nowpage;//当前页数
     private int nownumber;//当前页面记录数
     private List<ReplyDTO> discussdtolist=new ArrayList<ReplyDTO>();
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
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(int totalnumber) {
		this.totalnumber = totalnumber;
	}
	public int getNowpage() {
		return nowpage;
	}
	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}
	public int getNownumber() {
		return nownumber;
	}
	public void setNownumber(int nownumber) {
		this.nownumber = nownumber;
	}
	public List<ReplyDTO> getDiscussdtolist() {
		return discussdtolist;
	}
	public void setDiscussdtolist(List<ReplyDTO> discussdtolist) {
		this.discussdtolist = discussdtolist;
	}
     
}
