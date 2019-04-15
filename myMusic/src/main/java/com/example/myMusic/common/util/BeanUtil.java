package com.example.myMusic.common.util;

import com.example.myMusic.common.dto.UserregisteredDTO;
import com.example.myMusic.common.dto.discuss.DiscussDTO;
import com.example.myMusic.discuss.entities.Discuss;
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
        public static void discussTodiscussDTO(Discuss discuss,DiscussDTO discussdto) {
        	discussdto.setId(discuss.getId());
        	discussdto.setContent(discuss.getContent());
        	discussdto.setLikernumber(discuss.getLikernumber());
        }
}
