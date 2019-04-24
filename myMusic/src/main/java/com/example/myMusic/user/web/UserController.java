package com.example.myMusic.user.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myMusic.common.dto.UserloginDTO;
import com.example.myMusic.common.dto.UserregisteredDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.user.entities.User;
import com.example.myMusic.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService=null;
	
	 @GetMapping(value="/test")
	 public String hello() {
		 return "hello";
	 }
	
	 @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	 public ExtAjaxResponse userlogin(@RequestBody UserloginDTO userlogindto) {
		 
		 ExtAjaxResponse extAjaxResponse= userService.userlogin(userlogindto);
		
		 return extAjaxResponse;
	 }
	 
	 @PostMapping(value="/register",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public ExtAjaxResponse userregistered(@RequestBody UserregisteredDTO userregisteredDTO) {
		
		 ExtAjaxResponse extAjaxResponse= userService.userregistered(userregisteredDTO);
		
		 return extAjaxResponse;
	 }
	 
}
