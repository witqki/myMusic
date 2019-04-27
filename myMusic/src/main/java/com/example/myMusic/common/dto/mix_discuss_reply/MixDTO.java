package com.example.myMusic.common.dto.mix_discuss_reply;

import java.util.Date;

//评论与回复的融合
public class MixDTO {
	 private Date sendtime;//发表时间
     private String content;//内容
     private Integer likernumber=null;
     private boolean isreply=false;
     private Long userId;
     private String username;  
     private IsreplyDTO isreplyDTO=null;
     
	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getLikernumber() {
		return likernumber;
	}
	public void setLikernumber(Integer likernumber) {
		this.likernumber = likernumber;
	}
	public boolean isIsreply() {
		return isreply;
	}
	public void setIsreply(boolean isreply) {
		this.isreply = isreply;
	}
	public IsreplyDTO getIsreplyDTO() {
		return isreplyDTO;
	}
	public void setIsreplyDTO(IsreplyDTO isreplyDTO) {
		this.isreplyDTO = isreplyDTO;
	}
     
}
