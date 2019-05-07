package com.example.myMusic.user.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.myMusic.common.util.BaseEntity;
import com.example.myMusic.discuss.entities.Discuss;
import com.example.myMusic.music.entities.Music;
import com.example.myMusic.reply.entities.Reply;
import com.example.myMusic.songList.entities.SongList;
@Entity
@Table(name="user")
public class User extends BaseEntity{
	
	 private String name;
     private String password;
     private boolean sex;
     private String phone;
     private String email;
    // private String pictureUrl;//头像地址
     private boolean isadmin=false;//是否普通管理员
     private boolean iswrite=true;//是否能发评论
     private boolean isapply=true;//能否申请歌单分享
     private List<Discuss> discusslist=new ArrayList<Discuss>();//用户的评论  
     private List<SongList> songlist=new ArrayList<SongList>();//歌单
     private List<Music> musiclist=new ArrayList<Music>();//自己收藏的歌曲

     
 
	public boolean isIsadmin() {
		return isadmin;
	}
	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}
	public boolean isIswrite() {
		return iswrite;
	}
	public void setIswrite(boolean iswrite) {
		this.iswrite = iswrite;
	}
	public boolean isIsapply() {
		return isapply;
	}
	public void setIsapply(boolean isapply) {
		this.isapply = isapply;
	}
	@OneToMany(mappedBy="user")
	public List<Discuss> getDiscusslist() {
		return discusslist;
	}
	public void setDiscusslist(List<Discuss> discusslist) {
		this.discusslist = discusslist;
	}

	@ManyToMany(mappedBy="userlist")
	public List<SongList> getSonglist() {
		return songlist;
	}
	public void setSonglist(List<SongList> songlist) {
		this.songlist = songlist;
	}
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Music> getMusiclist() {
		return musiclist;
	}
	public void setMusiclist(List<Music> musiclist) {
		this.musiclist = musiclist;
	}
	
   

	


	
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
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
//	public String getPictureUrl() {
//		return pictureUrl;
//	}
//	public void setPictureUrl(String pictureUrl) {
//		this.pictureUrl = pictureUrl;
//	}

	
	
}
