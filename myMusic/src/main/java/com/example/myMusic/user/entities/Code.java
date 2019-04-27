package com.example.myMusic.user.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.myMusic.common.util.BaseEntity;
@Entity
@Table(name="Code")
public class Code extends BaseEntity{

	private String email;
	private String phone;
	private String code;//验证码
	private long buildtime;
	
	
	public long getBuildtime() {
		return buildtime;
	}
	public void setBuildtime(long buildtime) {
		this.buildtime = buildtime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}
