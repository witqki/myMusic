package com.example.myMusic.common.dto.user;

public class UserresetDTO {
       private Integer id;
       private String sender;
     
       private String code;
       private String psw;
       private String conpsw;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getConpsw() {
		return conpsw;
	}
	public void setConpsw(String conpsw) {
		this.conpsw = conpsw;
	}
       
}
