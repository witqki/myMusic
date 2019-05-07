package com.example.myMusic.common.dto.discuss;

import java.util.ArrayList;
import java.util.List;

public class DiscussResponseDTO {
	   private boolean success;//请求是否成功
       private String msg="";

       private List<DiscussDTO> discussdtolist=new ArrayList<DiscussDTO>();
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
//	public int getTotalpage() {
//		return totalpage;
//	}
//	public void setTotalpage(int totalpage) {
//		this.totalpage = totalpage;
//	}
//	public int getTotalnumber() {
//		return totalnumber;
//	}
//	public void setTotalnumber(int totalnumber) {
//		this.totalnumber = totalnumber;
//	}
//	public int getNowpage() {
//		return nowpage;
//	}
//	public void setNowpage(int nowpage) {
//		this.nowpage = nowpage;
//	}
//	public int getNownumber() {
//		return nownumber;
//	}
//	public void setNownumber(int nownumber) {
//		this.nownumber = nownumber;
//	}
	public List<DiscussDTO> getDiscussdtolist() {
		return discussdtolist;
	}
	public void setDiscussdtolist(List<DiscussDTO> discussdtolist) {
		this.discussdtolist = discussdtolist;
	}
	
       
}
