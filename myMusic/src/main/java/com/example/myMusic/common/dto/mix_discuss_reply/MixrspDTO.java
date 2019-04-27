package com.example.myMusic.common.dto.mix_discuss_reply;

import java.util.ArrayList;
import java.util.List;

public class MixrspDTO {
	   private Integer pageTotal=null;//总页数
	   private Integer nowpage=null;//现在所在页数
       private Integer count=null;
       private boolean success;
       private String msg;
       private List<MixDTO> mixdto=null;
       
	public Integer getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}
	public Integer getNowpage() {
		return nowpage;
	}
	public void setNowpage(Integer nowpage) {
		this.nowpage = nowpage;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
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
	public List<MixDTO> getMixdto() {
		return mixdto;
	}
	public void setMixdto(List<MixDTO> mixdto) {
		this.mixdto = mixdto;
	}
       
}
