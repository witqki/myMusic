package com.example.myMusic.common.dto.reply;

public class ReplySaveDTO {
	   private Long user_id;
	   private Long discuss_id;
	   private String content;//回复的内容
		public Long getUser_id() {
			return user_id;
		}
		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}
		public Long getDiscuss_id() {
			return discuss_id;
		}
		public void setDiscuss_id(Long discuss_id) {
			this.discuss_id = discuss_id;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
   
	
	
}
