package com.example.myMusic.music.web;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myMusic.music.dao.MusicDao;
import com.example.myMusic.music.entities.Music;
import com.example.myMusic.music.service.MusicService;
import com.example.myMusic.user.web.UserController;

@RestController
@RequestMapping("/music")
public class MusicController {
	private Logger logger = LoggerFactory.getLogger(MusicController.class);
	@Autowired
	private MusicService musicService=null;
	@Autowired
	private MusicDao musicDao=null;
	
	
	@GetMapping(value="/test")
	public List<Music> test( HttpServletResponse response,String searchcontent) {
		System.out.print(searchcontent);
		Music music=new Music();
		List<Music> list=(List<Music>) musicDao. searchContent(searchcontent);
		return list;
		
//		 String  urlStr ="G://KuGou/Bigbang - LOSER(Japanese Ver.).mp3";
//		 File imageFile = new File(urlStr);
//		 response.setContentType("application/octet-stream");
//		 try {
//		        FileInputStream fis = new FileInputStream(urlStr);
//		        byte[] content = new byte[fis.available()];
//		        fis.read(content);
//		        fis.close();
//		 
//		        ServletOutputStream sos = response.getOutputStream();
//		        sos.write(content);
//		 
//		        sos.flush();
//		        sos.close();
//		        return "success";
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		        return "fail";
//		    }
//		
		
	}
	
	@GetMapping(value="/search")
	public List<Music> searchmusic( HttpServletResponse response,String searchcontent) {
		
		System.out.print(searchcontent);
		Music music=new Music();
		List<Music> list=(List<Music>) musicDao. searchContent(searchcontent);
		return list;
	}
	
}
