package com.example.myMusic.common.dto.music;

import java.util.ArrayList;
import java.util.List;

import com.example.myMusic.common.util.BaseEntity;



public class SearchMusicDTO extends BaseEntity{
	private Long trueid;//真实网易云id
	private String name;//歌名
	private String path;//存放地址
	private String singer;//歌手
	private String album;//专辑
	private String language;//语言
	private String genre;//流派
	private String region;//地区
	private String theme;//主题
	private String Date;//发行日期
	private Integer time=null;//时长
	private String picturepath;
	private Integer amount_play=null;//播放量
	private List<Long> discussids;
	//private List<Discuss> discusslist=new ArrayList<Discuss>();//该歌的所有评论

	
	public String getName() {
		return name;
	}
	public Long getTrueid() {
		return trueid;
	}
	public void setTrueid(Long trueid) {
		this.trueid = trueid;
	}
	public Integer getAmount_play() {
		return amount_play;
	}
	public void setAmount_play(Integer amount_play) {
		this.amount_play = amount_play;
	}
	public SearchMusicDTO() {
		
	}
	public SearchMusicDTO(Long id,String name, String singer, 
			 Integer time, String picturepath,Integer amount_play) {
		this.id=id;
		this.name = name;
		this.singer = singer;
		this.time = time;
		this.picturepath = picturepath;
		this.amount_play = amount_play;
	
	}
	public SearchMusicDTO(String name, String path, String singer, String album, String language, String genre,
			String region, String theme, String date, Integer time, String picturepath, List<Long> discussids) {
		super();
		this.name = name;
		this.path = path;
		this.singer = singer;
		this.album = album;
		this.language = language;
		this.genre = genre;
		this.region = region;
		this.theme = theme;
		Date = date;
		this.time = time;
		this.picturepath = picturepath;
		this.discussids = discussids;
	}
	public String getPicturepath() {
		return picturepath;
	}
	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
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
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public List<Long> getDiscussids() {
		return discussids;
	}
	public void setDiscussids(List<Long> discussids) {
		this.discussids = discussids;
	}
	
}
