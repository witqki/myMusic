package com.example.myMusic.common.dto.user;

public class LoginRspDTO {
	    private Long userid;
        private boolean success=false;//登录是否成功
        private String token;
        private boolean admin=false;//是否普通管理员
        private boolean superadmin=false;//是否超级管理员
        private String msg;
        private String picturepath;
        private String name;
        private String phone;
        private String email;
        private boolean sex;
        
		public Long getUserid() {
			return userid;
		}
		public void setUserid(Long userid) {
			this.userid = userid;
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
		public String getPicturepath() {
			return picturepath;
		}
		public void setPicturepath(String picturepath) {
			this.picturepath = picturepath;
		}
	
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public boolean isAdmin() {
			return admin;
		}
		public void setAdmin(boolean admin) {
			this.admin = admin;
		}
		public boolean isSuperadmin() {
			return superadmin;
		}
		public void setSuperadmin(boolean superadmin) {
			this.superadmin = superadmin;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
        
        
}
