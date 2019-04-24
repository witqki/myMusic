package com.example.myMusic.user.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.myMusic.common.util.BaseEntity;
import com.example.myMusic.discuss.entities.Discuss;
import com.example.myMusic.reply.entities.Reply;
@Entity
@Table(name="user")
public class User extends BaseEntity{
	
	 private String name;
     private String password;
     private Integer sex;
     private String phone;
     private String email;
     private String headUrl;//头像地址
     private List<Discuss> discussList=new ArrayList<Discuss>();//用户的评论
     private List<Reply> replyList=new ArrayList<Reply>();//用户的回复
     
     @OneToMany(mappedBy="user")
	public List<Discuss> getDiscussList() {
		return discussList;
	}
	public void setDiscussList(List<Discuss> discussList) {
		this.discussList = discussList;
	}
	@OneToMany(mappedBy="user")
	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
}
