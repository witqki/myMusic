package com.example.myMusic.common.dto.mix_discuss_reply;

import java.util.Date;

public class IdDTO {
     private Long id;
     private Date date=null;
     private boolean isreply=false;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isIsreply() {
		return isreply;
	}
	public void setIsreply(boolean isreply) {
		this.isreply = isreply;
	}
     
}
