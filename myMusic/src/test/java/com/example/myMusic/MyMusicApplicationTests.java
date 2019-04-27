package com.example.myMusic;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.myMusic.common.util.BeanUtil;
import com.example.myMusic.music.dao.MusicDao;
import com.example.myMusic.music.entities.Music;
import com.example.myMusic.music.service.MusicService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyMusicApplicationTests {
	@Autowired
	private MusicService musicService=null;
	@Autowired
	private MusicDao musicDao=null;
	
	@Test
	public void contextLoads() {
//		String path="G:\\apache-tomcat-9.0.19-windows-x64\\apache-tomcat-9.0.19\\webapps\\ROOT\\music\\";
//		//BeanUtil.readAllfile(path);
//		System.out.println(BeanUtil.readAllfile(path));
//		System.out.println("---------------");
//		System.out.println(BeanUtil.filter(BeanUtil.readAllfile(path)));
		String path="E-kids - 玩玩具.mp3";
		String mp3path="G://KuGou/"+path;
		 File file=new File(mp3path);
		Music music=BeanUtil.readMp3(mp3path);
		musicDao.save(music);
		//System.out.println(BeanUtil.isNull(""));
	}
	
	
	
	
	

}

