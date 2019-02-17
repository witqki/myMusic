package com.example.myMusic.user.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.myMusic.common.util.BaseEntity;
@Entity
@Table(name="user")
public class User extends BaseEntity{
	
	 private String name;
     private String password;
     private Integer sex;
     private String phone;
     private String email;
     
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
}
