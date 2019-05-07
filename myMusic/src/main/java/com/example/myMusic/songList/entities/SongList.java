package com.example.myMusic.songList.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.myMusic.common.util.BaseEntity;

import com.example.myMusic.music.entities.Music;
import com.example.myMusic.user.entities.User;

@Entity
@Table(name="songlist")
public class SongList extends BaseEntity{
	private Long trueid;
     private String name;
    // private String picturepath;//图片地址
     private boolean isopen=false;//是否是网易云的歌单，false不是
    // private Long userid;
    // private String intro;//简介
     private List<User> userlist;//收藏该歌单的用户;个人创建歌单的只有一个用户，非个人歌单就有多个
     private List<Music> musiclist;//非网易云歌单包含的歌曲
   
//	public String getIntro() {
//		return intro;
//	}
//	public void setIntro(String intro) {
//		this.intro = intro;
//	}

//
	public String getName() {
		return name;
	}
	public Long getTrueid() {
		return trueid;
	}
	public void setTrueid(Long trueid) {
		this.trueid = trueid;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public String getPicturepath() {
//		return picturepath;
//	}
//	public void setPicturepath(String picturepath) {
//		this.picturepath = picturepath;
//	}

	public boolean isIsopen() {
		return isopen;
	}
	public void setIsopen(boolean isopen) {
		this.isopen = isopen;
	}
//	public Long getUserid() {
//		return userid;
//	}
//	public void setUserid(Long userid) {
//		this.userid = userid;
//	}
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<User> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<User> userlist) {
		this.userlist = userlist;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Music> getMusiclist() {
		return musiclist;
	}
	public void setMusiclist(List<Music> musiclist) {
		this.musiclist = musiclist;
	}
	
     
     
}
