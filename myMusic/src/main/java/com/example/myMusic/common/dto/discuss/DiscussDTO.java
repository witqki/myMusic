package com.example.myMusic.common.dto.discuss;



public class DiscussDTO {
	 private Long id;
	 private boolean islike=false;//是否点赞
	 private String name;//评论用户名
	 private String content;//评论的内容
	 private Integer likernumber=null;
	 private ReplyDTO replydto;
	 private String musicname;//查看评论时用
	 private Long discussid;
	 private Long time;//评论的时间
	 
	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getDiscussid() {
	
		return discussid;
	}

	public void setDiscussid(Long discussid) {
		this.discussid = discussid;
	}

	public String getMusicname() {
		return musicname;
	}

	public void setMusicname(String musicname) {
		this.musicname = musicname;
	}

	public boolean isIslike() {
		return islike;
	}

	public void setIslike(boolean islike) {
		this.islike = islike;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ReplyDTO getReplydto() {
		return replydto;
	}

	public void setReplydto(ReplyDTO replydto) {
		this.replydto = replydto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLikernumber() {
		return likernumber;
	}

	public void setLikernumber(Integer likernumber) {
		this.likernumber = likernumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	 
}
