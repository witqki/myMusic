package com.example.myMusic.discuss.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.myMusic.common.util.BaseEntity;
import com.example.myMusic.music.entities.Music;
import com.example.myMusic.reply.entities.Reply;
import com.example.myMusic.user.entities.User;
@Entity
@Table(name="discuss")
public class Discuss extends BaseEntity{
       private User user;  //该评论的用户
       private List<Reply> replylist= new ArrayList<Reply>();//该评论的回复
       private Music music;//该评论属于哪首歌
       private String content;//评论的内容
	   private Integer likernumber=null;//点赞人数
	   
	 @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Music getMusic() {
	    return music;
    }
	public void setMusic(Music music) {
		this.music = music;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@OneToMany(mappedBy="discuss")
	public List<Reply> getReplylist() {
		return replylist;
	}
	public void setReplylist(List<Reply> replylist) {
		this.replylist = replylist;
	}

	
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
