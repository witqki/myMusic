package com.example.myMusic.common.dto.adminUser;

import java.util.ArrayList;
import java.util.List;

public class UserRspDTO {
    private String msg;
    private boolean success;
    private List<UserDTO> userDTO=new ArrayList<UserDTO>();
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<UserDTO> getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(List<UserDTO> userDTO) {
		this.userDTO = userDTO;
	}
    
}
