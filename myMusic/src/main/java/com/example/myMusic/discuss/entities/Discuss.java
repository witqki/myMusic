package com.example.myMusic.discuss.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.myMusic.common.util.BaseEntity;
import com.example.myMusic.music.entities.Music;
import com.example.myMusic.reply.entities.Reply;
import com.example.myMusic.user.entities.User;
@Entity
@Table(name="discuss")
public class Discuss extends BaseEntity{
       
       private String content;//评论的内容
	 
	   private User user;  //该评论的用户     
       private Music music;//该评论属于哪首歌
       private Discuss discuss;//如果存在，则此为回复  单方向，可直接置空
       private List<User> userlist=new ArrayList<User>();//点赞人 
	
     @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)  
	 public Discuss getDiscuss() {
		return discuss;
	}
	public void setDiscuss(Discuss discuss) {
		this.discuss = discuss;
	}
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<User> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
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
//	@OneToMany(mappedBy="discuss")
//	public List<Reply> getReplylist() {
//		return replylist;
//	}
//	public void setReplylist(List<Reply> replylist) {
//		this.replylist = replylist;
//	}

	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	   
	
}
