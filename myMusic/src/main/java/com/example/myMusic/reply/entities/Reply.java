package com.example.myMusic.reply.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.myMusic.common.util.BaseEntity;
import com.example.myMusic.discuss.entities.Discuss;
import com.example.myMusic.user.entities.User;
@Entity
@Table(name="reply")
public class Reply extends BaseEntity{
	// private User user;  //该回复的用户
	// private Discuss discuss;//这条回复属于的那个评论的
	 private String content;//回复的内容
	 private Integer likernumber=null;//点赞人数
	 
//	 @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	 @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	public Discuss getDiscuss() {
//		return discuss;
//	}
//	public void setDiscuss(Discuss discuss) {
//		this.discuss = discuss;
//	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getLikernumber() {
		return likernumber;
	}
	public void setLikernumber(Integer likernumber) {
		this.likernumber = likernumber;
	}
	 
	 
}
