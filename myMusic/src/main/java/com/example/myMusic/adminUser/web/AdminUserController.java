package com.example.myMusic.adminUser.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.myMusic.adminUser.service.AdminUserService;
import com.example.myMusic.common.dto.adminUser.PathDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.music.entities.Music;
@RestController
@RequestMapping("/adminuser")
public class AdminUserController {
	@Autowired
	private AdminUserService adminUserService=null;
	//导入歌曲
	@PostMapping(value="/importsong",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse importsong(@RequestBody PathDTO pathDTO) {
	
		return adminUserService.importsong(pathDTO.getPath());
	}
	
}
