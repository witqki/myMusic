package com.example.myMusic.music.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myMusic.common.dto.music.SearchMusicDTO;
import com.example.myMusic.common.dto.music.SearchResponseDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.discuss.entities.Discuss;
import com.example.myMusic.music.dao.MusicDao;
import com.example.myMusic.music.entities.Music;
import com.example.myMusic.music.service.MusicService;

@Service
@Transactional
public class MusicServiceImpl implements MusicService{
	private String url="http://39.96.172.186:8080/";
	@Autowired
	private MusicDao musicDao=null;
	
	@Override
	public boolean existsById(Long id) {
		
		return musicDao.existsById(id);
	}

	@Override
	public SearchResponseDTO searchContent(String searchcontent) {
		// TODO Auto-generated method stub
		SearchResponseDTO searchResponseDTO=new SearchResponseDTO();
		List<SearchMusicDTO> list=new ArrayList<SearchMusicDTO>();
		List<Music> musiclist=musicDao.searchContent(searchcontent);
		if(musiclist.size()!=0&&musiclist!=null) {
			//Music music=new Music();
			for(Music music:musiclist) {
				SearchMusicDTO searchMusicDTO=new SearchMusicDTO();
				searchMusicDTO=musicTomusicDRTO(music,searchMusicDTO);
				//获取该歌曲的所有评论id
				List<Long> discussids=new ArrayList<Long>();
				List<Discuss> discusslist=music.getDiscusslist();//获取歌曲的评论
				if(discusslist.size()!=0&&discusslist!=null) {
					for(Discuss discuss:discusslist) {
						discussids.add(discuss.getId());
					}
				}
				searchMusicDTO.setDiscussids(discussids);
				list.add(searchMusicDTO);
			}
			searchResponseDTO.setList(list);
			searchResponseDTO.setSuccess(true);
			searchResponseDTO.setMsg("搜索成功");
		}else {
			List<Music> fulllist=(List<Music>) musicDao.findAll();
			if(fulllist.size()!=0&&fulllist!=null) {
				int x=(int)(Math.random()*100);
				List<Music> somelist=fulllist.subList(x, fulllist.size());
				for(Music music:somelist) {
					SearchMusicDTO searchMusicDTO=new SearchMusicDTO();
					searchMusicDTO=musicTomusicDRTO(music,searchMusicDTO);
					//获取该歌曲的所有评论id
					List<Long> discussids=new ArrayList<Long>();
					List<Discuss> discusslist=music.getDiscusslist();//获取歌曲的评论
					if(discusslist.size()!=0&&discusslist!=null) {
						for(Discuss discuss:discusslist) {
							discussids.add(discuss.getId());
						}
					}
					searchMusicDTO.setDiscussids(discussids);
					list.add(searchMusicDTO);
				}
				searchResponseDTO.setList(list);
				searchResponseDTO.setSuccess(false);
				searchResponseDTO.setMsg("查询无结果，随机选择一些歌曲显示");
			}
			else {
				searchResponseDTO.setSuccess(false);
				searchResponseDTO.setMsg("系统数据库丢失！");
			}
			
		}
		
		return  searchResponseDTO;
	}
	
	private SearchMusicDTO musicTomusicDRTO(Music music,SearchMusicDTO musicdto) {
		musicdto.setId(music.getId());
		musicdto.setCreateTime(music.getCreateTime());
		musicdto.setUpdateTime(music.getUpdateTime());;
		musicdto.setName(music.getName());
		musicdto.setPath(music.getPath());;
		musicdto.setSinger(music.getSinger());
		musicdto.setAlbum(music.getAlbum());
		musicdto.setLanguage(music.getLanguage());
		musicdto.setGenre(music.getGenre());		
		musicdto.setRegion(music.getRegion());
		musicdto.setTheme(music.getTheme());
		musicdto.setDate(music.getDate());
		return musicdto;
		
	}

	@Override
	public ExtAjaxResponse downsong(HttpServletResponse response, Long songid) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		//判断是否为空
		if(songid.equals(0L)) {
			extAjaxResponse.setMsg("传入id为空；系统传入有误！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		//判断是否存在数据库，不存在则返回
		if(!musicDao.existsById(songid)) {
			extAjaxResponse.setMsg("系统找不到此歌曲！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		//存在则进行返回下载流
		 Music music=musicDao.findById(songid).get();
		 
		// String  urlStr ="G://KuGou/Bigbang - LOSER(Japanese Ver.).mp3";
		 File imageFile = new File(music.getPath());
		 //判断歌曲所在的地址是否存在该歌曲
		 if(!imageFile.exists()) {
			    extAjaxResponse.setMsg("系统中的该歌曲地址有误，或该歌曲已下架！");
				extAjaxResponse.setSuccess(false);
				return extAjaxResponse;
		 }
		 String type=music.getPath().substring(music.getPath().lastIndexOf("."),music.getPath().length());
		 String fullname=music.getName()+type;
		 response.setContentType("application/octet-stream");
		 response.setContentType("application/force-download");// 设置强制下载不打开
		 response.addHeader("Content-Disposition", "attachment;fileName=" +fullname );// 设置文件名
		 try {
		        FileInputStream fis = new FileInputStream(imageFile);
		        byte[] content = new byte[fis.available()];
		        fis.read(content);
		        fis.close();
		 
		        ServletOutputStream sos = response.getOutputStream();
		        sos.write(content);
		 
		        sos.flush();
		        sos.close();
		        extAjaxResponse.setMsg("success");
				extAjaxResponse.setSuccess(true);
		        return extAjaxResponse;
		    } catch (Exception e) {
	            e.printStackTrace();
	            extAjaxResponse.setMsg("fail");
				extAjaxResponse.setSuccess(false);
		        return extAjaxResponse;
		    }
	}

	@Override
	public ExtAjaxResponse playsong(Long songid) {
		// TODO Auto-generated method stub
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(!songid.equals(0L)) {
			if(musicDao.existsById(songid)) {
				Music music=musicDao.findById(songid).get();
				File file=new File(music.getPath());
				if(file.exists()) {
					
					extAjaxResponse.setMsg(url+file.getName());
					extAjaxResponse.setSuccess(true);
				}else {
					extAjaxResponse.setMsg("存放该歌曲的地址不正确！");
					extAjaxResponse.setSuccess(false);
				}
				
			}else {
				extAjaxResponse.setMsg("播放失败，资源查询不到");
				extAjaxResponse.setSuccess(false);
			}
		}else {
			extAjaxResponse.setMsg("播放失败，系统发生传输错误");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}
}
