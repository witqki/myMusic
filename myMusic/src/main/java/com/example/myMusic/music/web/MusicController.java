package com.example.myMusic.music.web;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myMusic.common.dto.music.SearchMusicDTO;
import com.example.myMusic.common.dto.music.SearchResponseDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;
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
	
	@GetMapping
	public String test(HttpServletResponse response) {
	//这是进行下载的代码
		 String  urlStr ="G://KuGou/Bigbang - LOSER(Japanese Ver.).mp3";
		 File imageFile = new File(urlStr);
		 response.setContentType("application/octet-stream");
		 try {
		        FileInputStream fis = new FileInputStream(urlStr);
		        byte[] content = new byte[fis.available()];
		        fis.read(content);
		        fis.close();
		 
		        ServletOutputStream sos = response.getOutputStream();
		        sos.write(content);
		 
		        sos.flush();
		        sos.close();
		        return "success";
		    } catch (Exception e) {
		        e.printStackTrace();
		        return "fail";
		    }
	}
	@GetMapping(value="/search")
	public SearchResponseDTO searchmusic( HttpServletResponse response,String searchcontent) {	
		SearchResponseDTO rsp= musicService. searchContent(searchcontent);		
		return  rsp;
	}
	@GetMapping(value="/downsong/{songid}")
	public ExtAjaxResponse downsong(HttpServletResponse response,@PathVariable ("songid") Long songid) {
         return musicService.downsong(response, songid);	
	}
	@GetMapping(value="/playsong/{songid}")
	public ExtAjaxResponse playsong(@PathVariable("songid") Long songid) {
		return musicService.playsong(songid);
	}
	
}
