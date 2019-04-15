package com.example.myMusic.user.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.myMusic.common.util.BaseEntity;
import com.example.myMusic.discuss.entities.Discuss;
import com.example.myMusic.music.entities.Music;
import com.example.myMusic.reply.entities.Reply;
@Entity
@Table(name="user")
public class User extends BaseEntity{
	
	 private String name;
     private String password;
     private Integer sex;
     private String phone;
     private String email;
     private List<Discuss> discusslist= new ArrayList<Discuss>();//该用户的评论
     private List<Reply> replylist= new ArrayList<Reply>();//该用户的回复
    // private List<Music> musiclist;//该用户收藏的音乐
     
//     @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//     public List<Music> getMusiclist() {
//		return musiclist;
//	}
//	public void setMusiclist(List<Music> musiclist) {
//		this.musiclist = musiclist;
//	}
	
	@OneToMany(mappedBy="user")
	public List<Discuss> getDiscusslist() {
		return discusslist;
	}
	public void setDiscusslist(List<Discuss> discusslist) {
		this.discusslist = discusslist;
	}
	@OneToMany(mappedBy="user")
	public List<Reply> getReplylist() {
		return replylist;
	}
	public void setReplylist(List<Reply> replylist) {
		this.replylist = replylist;
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
}
