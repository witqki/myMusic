package com.example.myMusic.adminUser.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.myMusic.common.util.BaseEntity;
@Entity
@Table(name="admin_user")
public class AdminUser extends BaseEntity{
    private String name;
    private String password;
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
    

}
