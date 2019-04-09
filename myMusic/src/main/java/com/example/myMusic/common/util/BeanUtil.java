package com.example.myMusic.common.util;

import com.example.myMusic.common.dto.UserregisteredDTO;
import com.example.myMusic.user.entities.User;

public class BeanUtil {
        public static User UserregisteredDTOtoUser(UserregisteredDTO userregisteredDTO,User user) {
        	user.setName(userregisteredDTO.getName());
        	user.setPassword(userregisteredDTO.getPassword());
        	user.setSex(userregisteredDTO.getSex());
        	user.setPhone(userregisteredDTO.getPhone());
        	user.setEmail(userregisteredDTO.getEmail());
        	return user;
        }
}
