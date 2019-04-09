package com.example.myMusic.music.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.myMusic.common.util.BaseEntity;

@Entity
@Table(name="music")
public class Music extends BaseEntity{
        
	private String name;//歌名
	private String path;//存放地址
	private String singer;//歌手
	private String album;//专辑
	private String language;//语言
	private String genre;//流派
	private String region;//地区
	private String theme;//主题
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	
	
	
	
	
	
	
	
	
	
}
