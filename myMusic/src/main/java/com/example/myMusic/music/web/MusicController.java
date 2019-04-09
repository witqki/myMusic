package com.example.myMusic.music.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myMusic.music.service.MusicService;
import com.example.myMusic.user.web.UserController;

@RestController
@RequestMapping("/music")
public class MusicController {
	private Logger logger = LoggerFactory.getLogger(MusicController.class);
	@Autowired
	private MusicService musicService=null;
	
	@GetMapping(value="/test")
	public String test() {
		
		return "hh";
		
	}
	
	
}
