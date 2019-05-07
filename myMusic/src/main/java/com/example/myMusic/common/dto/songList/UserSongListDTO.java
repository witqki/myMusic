package com.example.myMusic.common.dto.songList;

public class UserSongListDTO {
	private Long songListId;
      private String name;
      private Long userId;
      
	public Long getSongListId() {
		return songListId;
	}
	public void setSongListId(Long songListId) {
		this.songListId = songListId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
      
      
}
