package com.example.myMusic.common.dto.discuss;

public class DiscussRequestDTO {//保存评论使用
	 private Long user_id;  //该评论的用户
	 private Long music_id;//该评论属于哪首歌
	 private String content;//评论的内容
	 
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getMusic_id() {
		return music_id;
	}
	public void setMusic_id(Long music_id) {
		this.music_id = music_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	 
}
