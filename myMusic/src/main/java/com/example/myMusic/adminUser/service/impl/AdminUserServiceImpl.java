package com.example.myMusic.adminUser.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myMusic.adminUser.service.AdminUserService;
import com.example.myMusic.common.dto.adminUser.CheckDTO;
import com.example.myMusic.common.dto.adminUser.ComUserDTO;
import com.example.myMusic.common.dto.adminUser.PathDTO;
import com.example.myMusic.common.dto.adminUser.UserDTO;
import com.example.myMusic.common.dto.adminUser.UserRspDTO;
import com.example.myMusic.common.dto.discuss.DiscussDTO;
import com.example.myMusic.common.dto.discuss.DiscussResponseDTO;
import com.example.myMusic.common.dto.music.SearchMusicDTO;
import com.example.myMusic.common.dto.music.SearchResponseDTO;
import com.example.myMusic.common.dto.songList.SongListDTO;
import com.example.myMusic.common.dto.songList.SongListRspDTO;
import com.example.myMusic.common.dto.user.LoginRspDTO;
import com.example.myMusic.common.util.BeanUtil;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.discuss.dao.DiscussDao;
import com.example.myMusic.discuss.entities.Discuss;
import com.example.myMusic.music.dao.MusicDao;
import com.example.myMusic.music.entities.Music;

import com.example.myMusic.songList.dao.SongListDao;
import com.example.myMusic.songList.entities.SongList;
import com.example.myMusic.user.dao.UserDao;
import com.example.myMusic.user.entities.User;
@Service
@Transactional
public class AdminUserServiceImpl implements AdminUserService{
	@Autowired
    private MusicDao musicDao=null;
	@Autowired
    private DiscussDao discussDao=null;
	@Autowired
    private SongListDao songListDao=null;
	@Autowired
    private UserDao userDao=null;
//	@Override
//	public ExtAjaxResponse importsong(String path) {
//		// TODO Auto-generated method stub
//		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
//		if(path==null||path.length()==0||path.trim().equals("")) {//传入为空
//			extAjaxResponse.setMsg("传入的音乐地址为空！");
//			extAjaxResponse.setSuccess(false);
//			return extAjaxResponse;
//		}else {
//			File file=new File(path);
//			if(file.exists()) {//如果地址存在
//				if(file.isFile()) {//如果是文件
//					//假如是.flac文件
//					if(BeanUtil.isNeedFile(file.getAbsolutePath(), 1)) {
//						
//						 Music music=BeanUtil.readMp3(file.getAbsolutePath());
//						 List<Music> list=musicDao.exitMusicpath(music.getPath());
//						 if(list.size()!=0&&list!=null){
//							 extAjaxResponse.setMsg("文件已存在！");
//							 extAjaxResponse.setSuccess(false);
//						 }else {
//							 musicDao.save(music);
//							 List<Singer>  singerList=singerDao.iscreate(music.getSinger());
//							 if(singerList.size()==0||singerList==null) {
//								 Singer singer=new Singer();
//								 singer.setName(music.getSinger());
//								 singer.setFirstword(BeanUtil.getFirstPinYinHeadChar(music.getSinger()));
//								 singer.setLocation(music.getRegion());
//								 singer.setType("未知");
//								 singer.setPicturepath(music.getPicturepath());
//								 singer.setGenre(music.getGenre());
//								 singerDao.save(singer);
//							 }
//							 extAjaxResponse.setMsg("success！");
//							 extAjaxResponse.setSuccess(true);
//						 }
//						
//					}
//					else if(BeanUtil.isNeedFile(file.getAbsolutePath(), 2)) {
//						Music music=BeanUtil.readflac(file.getAbsolutePath());
//						 List<Music> list=musicDao.exitMusicpath(music.getPath());
//						 if(list.size()!=0&&list!=null){
//							 extAjaxResponse.setMsg("文件已存在！");
//							 extAjaxResponse.setSuccess(false);
//						 }else {
//							 List<Singer>  singerList=singerDao.iscreate(music.getSinger());
//							 if(singerList.size()==0||singerList==null) {
//								 Singer singer=new Singer();
//								 singer.setName(music.getSinger());
//								 singer.setFirstword(BeanUtil.getFirstPinYinHeadChar(music.getSinger()));
//								 singer.setLocation(music.getRegion());
//								 singer.setType("未知");
//								 singer.setPicturepath(music.getPicturepath());
//								 singer.setGenre(music.getGenre());
//								 singerDao.save(singer);
//							 }
//							 musicDao.save(music);
//							 extAjaxResponse.setMsg("success！");
//							 extAjaxResponse.setSuccess(true);
//						 }
//					}
//					else {
//						extAjaxResponse.setMsg("选择的文件不是所要求的音频文件类型！");
//						extAjaxResponse.setSuccess(false);
//					}
//					
//				}if(file.isDirectory()) {//如果是文件夹
//					List<String> allfile=BeanUtil.readAllfile(file.getAbsolutePath());
//					if(allfile!=null&&allfile.size()!=0) {
//						 List<String> filter=BeanUtil.filter(allfile);
//						 if(filter!=null&&filter.size()!=0) {
//							 //进行信息导入
//							 for(String str:filter) {
//								 if(BeanUtil.isNeedFile(str, 1)) {//是MP3文件
//									 Music music=BeanUtil.readMp3(str);
//									 List<Music> list=musicDao.exitMusicpath(music.getPath());
//									 if(list!=null&&list.size()!=0) {
//										 //存在则不做任何事
//									 }else {	
//										 List<Singer>  singerList=singerDao.iscreate(music.getSinger());
//										 if(singerList.size()==0||singerList==null) {
//											 Singer singer=new Singer();
//											 singer.setName(music.getSinger());
//											 singer.setFirstword(BeanUtil.getFirstPinYinHeadChar(music.getSinger()));
//											 singer.setLocation(music.getRegion());
//											 singer.setType("未知");
//											 singer.setPicturepath(music.getPicturepath());
//											 singer.setGenre(music.getGenre());
//											 singerDao.save(singer);
//										 }
//										 musicDao.save(music);
//									 }
//								 }
//								 else if(BeanUtil.isNeedFile(str, 2)) {//是flac文件
//									 Music music=BeanUtil.readflac(str);
//									 List<Music> list=musicDao.exitMusicpath(music.getPath());
//									 if(list!=null&&list.size()!=0) {
//										 //存在则不做任何事
//									 }else {
//										 List<Singer>  singerList=singerDao.iscreate(music.getSinger());
//										 if(singerList.size()==0||singerList==null) {
//											 Singer singer=new Singer();
//											 singer.setName(music.getSinger());
//											 singer.setFirstword(BeanUtil.getFirstPinYinHeadChar(music.getSinger()));
//											 singer.setLocation(music.getRegion());
//											 singer.setType("未知");
//											 singer.setPicturepath(music.getPicturepath());
//											 singer.setGenre(music.getGenre());
//											 singerDao.save(singer);
//										 }
//										 musicDao.save(music);
//									 }
//								 }else {
//									    extAjaxResponse.setMsg("信息导入有误！");
//										extAjaxResponse.setSuccess(false);
//										return extAjaxResponse;
//								 }
//								 extAjaxResponse.setMsg("信息导入完成！");
//									extAjaxResponse.setSuccess(true);
//							 }
//						 }else {
//							    extAjaxResponse.setMsg("文件夹无对应格式的音频文件！");
//								extAjaxResponse.setSuccess(false);
//						 }
//					}else {
//						extAjaxResponse.setMsg("文件夹为空！");
//						extAjaxResponse.setSuccess(false);
//					}
//					
//				}
//				return extAjaxResponse;
//			}
//			else {
//				extAjaxResponse.setMsg("传入的音乐地址不存在！"+path);
//				extAjaxResponse.setSuccess(false);
//				return extAjaxResponse;
//			}
//		}
//	
//	}


	@Override
	public ExtAjaxResponse change_authority(ComUserDTO comUserDTO) {
		ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
		try {
			if(userDao.existsById(comUserDTO.getId())) {		
					User user=userDao.findById(comUserDTO.getId()).get();
					user.setIswrite(comUserDTO.isWrite());
					user.setIsapply(comUserDTO.isIsapply());
					userDao.save(user);
					extAjaxResponse.setMsg("success");
					extAjaxResponse.setSuccess(true);						
			}else {
				extAjaxResponse.setMsg("系统发生错误:用户不存在");
				extAjaxResponse.setSuccess(false);
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统发生错误");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}


	@Override
	public UserRspDTO findUser(LoginRspDTO loginRspDTO) {
		UserRspDTO userRspDTO=new UserRspDTO();
		try {
			if(loginRspDTO.isSuccess()) {
				if(loginRspDTO.isSuperadmin()) {
					List<User> userlist=(List<User>) userDao.findAll();
					if(userlist.size()==0||userlist==null) {
						userRspDTO.setMsg("没有适合您管理的用户！");
						userRspDTO.setSuccess(false);
					}else {
						List<UserDTO> userDTO=new ArrayList<UserDTO>();
						for(User user:userlist) {
							UserDTO userdto=new UserDTO();
							userdto.setId(user.getId());
							userdto.setName(user.getName());
							userdto.setAdmin(user.isIsadmin());
							userdto.setWrite(user.isIswrite());
							userDTO.add(userdto);
						}
						userRspDTO.setUserDTO(userDTO);
						userRspDTO.setMsg("success");
						userRspDTO.setSuccess(true);
					}
				}
				else if(loginRspDTO.isAdmin()) {//普通管理员
					List<User> userlist=(List<User>) userDao.findAll();
					if(userlist.size()==0||userlist==null) {
						userRspDTO.setMsg("没有适合您管理的用户！");
						userRspDTO.setSuccess(false);
					}else {
						List<UserDTO> userDTO=new ArrayList<UserDTO>();
						for(User user:userlist) {
							if(user.isIsadmin()) {
								
							}else {
								UserDTO userdto=new UserDTO();
								userdto.setId(user.getId());
								userdto.setName(user.getName());
								userdto.setAdmin(user.isIsadmin());
								userdto.setWrite(user.isIswrite());
								userDTO.add(userdto);
							}
							
						}
						if(userDTO.size()==0||userDTO==null) {
							userRspDTO.setMsg("没有适合您管理的用户！");
							userRspDTO.setSuccess(false);
						}else {
							userRspDTO.setUserDTO(userDTO);
							userRspDTO.setMsg("success");
							userRspDTO.setSuccess(true);
						}
						
					}
				}
				else {
					userRspDTO.setMsg("您没有权限！");
					userRspDTO.setSuccess(false);
				}
			}else {
				userRspDTO.setMsg("请您登录！"+loginRspDTO.isSuccess());
				userRspDTO.setSuccess(false);
			}
		}catch(Exception e) {
			userRspDTO.setMsg("系统错误");
			userRspDTO.setSuccess(false);
		}
		return userRspDTO;
	}


	@Override
	public ExtAjaxResponse deleteUser(Long id) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		try {
			if(userDao.existsById(id)) {			
					userDao.deleteById(id);
					extAjaxResponse.setMsg("success");
					extAjaxResponse.setSuccess(true);	
			}else {
				extAjaxResponse.setMsg("系统发生错误：删除的用户不存在！");
				extAjaxResponse.setSuccess(false);
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统发生错误");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}


	@Override
	public DiscussResponseDTO checkDiscuss(CheckDTO checkDTO) {
		DiscussResponseDTO discussResponseDTO=new DiscussResponseDTO();
		try {
			if(checkDTO.isSuperAdmin()) {//超级管理员
				List<Discuss> list=(List<Discuss>) discussDao.findAll();
				if(list==null||list.size()==0) {
					discussResponseDTO.setMsg("系统暂无评论数据");
					discussResponseDTO.setSuccess(false); 
				}else {
					List<DiscussDTO> discussdtolist=new ArrayList<DiscussDTO>();
					for(Discuss dis:list) {
						DiscussDTO discussDTO=new DiscussDTO();
						discussDTO.setContent(dis.getContent());
						discussDTO.setDiscussid(dis.getId());
						discussDTO.setName(dis.getUser().getName());
						discussdtolist.add(discussDTO);
					}
					discussResponseDTO.setDiscussdtolist(discussdtolist);
					discussResponseDTO.setMsg("success");
					discussResponseDTO.setSuccess(true); 
				}
			}
			else {//普通管理员
				List<Discuss> list=(List<Discuss>) discussDao.findAll();
				if(list==null||list.size()==0) {
					discussResponseDTO.setMsg("系统暂无评论数据");
					discussResponseDTO.setSuccess(false); 
				}else {
					List<DiscussDTO> discussdtolist=new ArrayList<DiscussDTO>();
					for(Discuss dis:list) {
						User user=dis.getUser();
						if(user.isIsadmin()) {
							
						}else {
							DiscussDTO discussDTO=new DiscussDTO();
							discussDTO.setContent(dis.getContent());
							discussDTO.setDiscussid(dis.getId());
							discussDTO.setName(dis.getUser().getName());
							discussdtolist.add(discussDTO);
						}
						
					}
					
					discussResponseDTO.setDiscussdtolist(discussdtolist);
					discussResponseDTO.setMsg("success");
					discussResponseDTO.setSuccess(true); 
				}
			}
		}catch(Exception e) {
			discussResponseDTO.setMsg("系统发生错误");
			discussResponseDTO.setSuccess(false); 
		}
		return discussResponseDTO;
	}


	@Override
	public SearchResponseDTO looksong() {
		SearchResponseDTO searchResponseDTO=new SearchResponseDTO();
		try {
			List<Music> musicList=(List<Music>) musicDao.findAll();
			if(musicList.size()==0||musicList==null) {
				searchResponseDTO.setMsg("暂无歌曲！");
				searchResponseDTO.setSuccess(false);
			}else {
				List<SearchMusicDTO> searchMusicDTOList=new ArrayList<SearchMusicDTO>();
				for(Music music:musicList) {
					SearchMusicDTO searchMusicDTO=new SearchMusicDTO();
					searchMusicDTO.setTrueid(music.getTrueid());
					searchMusicDTOList.add(searchMusicDTO);
				}
				searchResponseDTO.setList(searchMusicDTOList);
				searchResponseDTO.setMsg("success！");
				searchResponseDTO.setSuccess(true);
			}
		}catch(Exception e) {
			searchResponseDTO.setMsg("系统发生错误！");
			searchResponseDTO.setSuccess(false);
		}
		return searchResponseDTO;
	}


	@Override
	public ExtAjaxResponse deletesong(Long id) {
		ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
		try {
			if(id.equals(0L)) {
				extAjaxResponse.setMsg("系统发生错误！传入数据为空");
				extAjaxResponse.setSuccess(false);
				return extAjaxResponse;
			}
			if(musicDao.existsById(id)) {
				Music music=musicDao.findById(id).get();
				List<User> userlist=music.getUserlist();
				List<Discuss> discusslist=music.getDiscusslist();
				if(userlist==null||userlist.size()==0) {
					
				}else {
					for(User uu:userlist) {
						uu.getMusiclist().remove(music);
						userDao.save(uu);
					}
					music.setUserlist(null);
					
				}
				
	            if(discusslist==null||discusslist.size()==0) {
					
				}else {
					for(Discuss dd:discusslist) {
					     User tt=dd.getUser();
					     tt.getDiscusslist().remove(dd);
					     userDao.save(tt);
					     dd.setUser(null);
					     dd.setMusic(null);
					     dd.setDiscuss(null);
					     dd.setUserlist(null);
						discussDao.save(dd);
						discussDao.deleteById(dd.getId());
					}
					music.setUserlist(null);
					
				}
	            musicDao.save(music);
				musicDao.deleteById(id);
				extAjaxResponse.setMsg("删除成功！");
				extAjaxResponse.setSuccess(true);
			}else {
				extAjaxResponse.setMsg("歌曲不存在！");
				extAjaxResponse.setSuccess(false);
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统发生错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}


//	@Override
//	public ExtAjaxResponse changesong(SearchMusicDTO searchMusicDTO) {
//		ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
//		try {
//			if(musicDao.existsById(searchMusicDTO.getId())) {
//				Music music=musicDao.findById(searchMusicDTO.getId()).get();				
//				music.setName(searchMusicDTO.getName());
//				music.setSinger(searchMusicDTO.getSinger());
//				music.setAlbum(searchMusicDTO.getAlbum());				
//				music.setLanguage(searchMusicDTO.getLanguage());
//				music.setGenre(searchMusicDTO.getGenre());
//				music.setRegion(searchMusicDTO.getRegion());
//				music.setTheme(searchMusicDTO.getTheme());
//				music.setDate(searchMusicDTO.getDate());
//				music.setTime(searchMusicDTO.getTime());
//				//searchMusicDTO.setPicturepath(music.getPicturepath());
//				musicDao.save(music);
//				extAjaxResponse.setMsg("success！");
//				extAjaxResponse.setSuccess(true);
//			}else {
//				extAjaxResponse.setMsg("歌曲不存在！");
//				extAjaxResponse.setSuccess(false);
//			}
//		}catch(Exception e) {
//			extAjaxResponse.setMsg("系统发生错误！");
//			extAjaxResponse.setSuccess(false);
//		}
//		return extAjaxResponse;
//	}


	@Override
	public SongListRspDTO looksongList() {
		SongListRspDTO songListRspDTO=new SongListRspDTO();
		try {
			List<SongList> songList=(List<SongList>) songListDao.findAll();
			
			if(songList.size()==0||songList==null) {
				songListRspDTO.setMsg("系统查无歌单！");
				songListRspDTO.setSuccess(false);
			}else {
				List<SongListDTO> list=new ArrayList<SongListDTO>();
				for(SongList s :songList) {
					if(s.isIsopen()) {
						SongListDTO songListDTO=new SongListDTO();
						songListDTO.setSongListId(s.getTrueid());
			
						list.add(songListDTO);
					}							
				}
				songListRspDTO.setSongList(list);
				songListRspDTO.setMsg("success！");
				songListRspDTO.setSuccess(true);
				
			}
		}catch(Exception e) {
			songListRspDTO.setMsg("系统发生错误！");
			songListRspDTO.setSuccess(false);
		}
		return songListRspDTO;
	}


	@Override
	public ExtAjaxResponse deletesongList(Long id) {
		ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
		try {
			if(songListDao.existsById(id) ){
				songListDao.deleteById(id);
				extAjaxResponse.setMsg("success！");
				extAjaxResponse.setSuccess(true);
				
			}else {
				extAjaxResponse.setMsg("歌曲不存在！");
				extAjaxResponse.setSuccess(false);
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统发生错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}


	@Override
	public SongListRspDTO getsongList(Long id) {
		SongListRspDTO songListRspDTO=new SongListRspDTO();
		try {
			if(songListDao.existsById(id)) {
				SongList songList=songListDao.findById(id).get();
				List<SongListDTO> list=new ArrayList<SongListDTO>();
				
				if(songList.isIsopen()) {
					SongListDTO songListDTO=new SongListDTO();
					songListDTO.setName(songList.getName());
				//	songListDTO.setPicturepath(songList.getPicturepath());
					songListDTO.setSongNumber(songList.getMusiclist().size());
				//	User user=userDao.findById(songList.getUserid()).get();
				//	songListDTO.setUserName(user.getName());
					list.add(songListDTO);
					songListRspDTO.setSongList(list);
					songListRspDTO.setMsg("success！");
					songListRspDTO.setSuccess(true);
				}
				else {
					songListRspDTO.setMsg("系统错误： 此歌单非公开歌单！");
					songListRspDTO.setSuccess(false);
				}
				
			}else {
				songListRspDTO.setMsg("系统错误：查无此歌单！");
				songListRspDTO.setSuccess(false);
			}
		
		}catch(Exception e) {
			songListRspDTO.setMsg("系统发生错误！");
			songListRspDTO.setSuccess(false);
		}
		return songListRspDTO;
	}


	@Override
	public ExtAjaxResponse changesongList(SongListDTO songListDTO) {
		ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
		try {
			if(songListDao.existsById(songListDTO.getSongListId()) ){
				SongList songList =songListDao.findById(songListDTO.getSongListId()).get();
				songList.setName(songListDTO.getName());
				//songList.setPicturepath(songListDTO.getPicturepath());
				//songList.setIntro(songListDTO.getIntro());
				songList.setIsopen(songListDTO.isOpen());
				songListDao.save(songList);
				extAjaxResponse.setMsg("success！");
				extAjaxResponse.setSuccess(true);
				
			}else {
				extAjaxResponse.setMsg("歌单不存在！");
				extAjaxResponse.setSuccess(false);
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统发生错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}


	@Override
	public ExtAjaxResponse addsongList(SongListDTO songListDTO) {
		ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
		try {
			    if(BeanUtil.isNull(songListDTO.getName().trim())||BeanUtil.isNull(songListDTO.getIntro()))
				{
			    	extAjaxResponse.setMsg("填写信息存在为空！");
					extAjaxResponse.setSuccess(false);
				
			}else {	
			    SongList songList =new SongList();
				songList.setName(songListDTO.getName());
				//songList.setPicturepath(songListDTO.getPicturepath());
				//songList.setIntro(songListDTO.getIntro());
				songList.setIsopen(true);
				SongList back=songListDao.save(songList);
				if(userDao.existsById(1L)) {		
				}else {
					List<User> userList=(List<User>) userDao.findAll();
					if(userList.size()==0||userList==null) {
						User user=new User();
						user.setName("Admin");
						user.setPassword("admin");
						user.setIsadmin(true);
						user.setPhone("13538627844");
						User s=userDao.save(user);
						if(s.getId().equals(1L)) {
							
						}else {
							extAjaxResponse.setMsg("系统无法创建指定的id为1的系统发布歌单的用户！");
							extAjaxResponse.setSuccess(false);
							return extAjaxResponse;
						}
					}else {
						extAjaxResponse.setMsg("系统查无指定系统发布歌单的id为1的用户！");
						extAjaxResponse.setSuccess(false);
						return extAjaxResponse;
					}
				}
			//	songList.setUserid(1L);
				User user=userDao.findById(1L).get();
				user.getSonglist().add(back);
				songList.getUserlist().add(user);
				userDao.save(user);
				songListDao.save(back);
				extAjaxResponse.setMsg("success！");
				extAjaxResponse.setSuccess(true);
				
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统发生错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}


	@Override
	public SongListRspDTO getApplesongList() {
		SongListRspDTO songListRspDTO=new SongListRspDTO();
		try {
			List<SongList> songList=(List<SongList>) songListDao.findAll();
			if(songList.size()==0||songList==null) {
				songListRspDTO.setMsg("查无歌单！");
				songListRspDTO.setSuccess(false);
			}else {
				List<SongListDTO> songListDTOList=new ArrayList<SongListDTO>();
				for(SongList song:songList) {
					if(true) {//申请公开歌单
						SongListDTO songListDTO=new SongListDTO();
						songListDTO.setSongListId(song.getId());
						songListDTO.setName(song.getName());
					//	songListDTO.setPicturepath(song.getPicturepath());
					//	songListDTO.setIntro(song.getIntro());
						songListDTO.setSongNumber(new Integer(song.getMusiclist().size()));
					//	User user=userDao.findById(song.getUserid()).get();
					//	songListDTO.setUserName(user.getName());
						songListDTOList.add(songListDTO);
					}			
				}
				songListRspDTO.setSongList(songListDTOList);
				songListRspDTO.setMsg("success!");
				songListRspDTO.setSuccess(true);
			}
			
		}catch(Exception e) {
			songListRspDTO.setMsg("系统发生错误！");
			songListRspDTO.setSuccess(false);
		}
		return songListRspDTO;
	}


	@Override
	public ExtAjaxResponse agreeApplesongList(Long id) {
		ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
		try {
			if(songListDao.existsById(id) ){
				SongList songList =songListDao.findById(id).get();
			//	songList.setIsapple_to_open(false);
				songList.setIsopen(true);
				songListDao.save(songList);
				extAjaxResponse.setMsg("success！");
				extAjaxResponse.setSuccess(true);
				
			}else {
				extAjaxResponse.setMsg("歌单不存在！");
				extAjaxResponse.setSuccess(false);
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统发生错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}


	@Override
	public ExtAjaxResponse refuseApplesongList(Long id) {
		ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
		try {
			if(songListDao.existsById(id) ){
				SongList songList =songListDao.findById(id).get();
			//	songList.setIsapple_to_open(false);
				
				songListDao.save(songList);
				extAjaxResponse.setMsg("success！");
				extAjaxResponse.setSuccess(true);
				
			}else {
				extAjaxResponse.setMsg("歌单不存在！");
				extAjaxResponse.setSuccess(false);
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统发生错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}


	@Override
	public ExtAjaxResponse deleteuserDiscuss(CheckDTO checkDTO) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		try {
			if(checkDTO.getDiscussid().equals(0L)) {
				if(discussDao.existsById(checkDTO.getDiscussid())) {
					Discuss discuss=discussDao.findById(checkDTO.getDiscussid()).get();
					User user=discuss.getUser();
					Music music=discuss.getMusic();
					user.getDiscusslist().remove(discuss);
					music.getDiscusslist().remove(discuss);
					discuss.setUserlist(null);
					discuss.setDiscuss(null);
					userDao.save(user);
					musicDao.save(music);
					discussDao.save(discuss);
					discussDao.deleteById(checkDTO.getDiscussid());
					extAjaxResponse.setMsg("删除成功！");
					extAjaxResponse.setSuccess(true);
					
					
				}else {
					extAjaxResponse.setMsg("系统发生错误！此数据已经不存在数据库！");
					extAjaxResponse.setSuccess(false);
				}
			}else {
				extAjaxResponse.setMsg("系统发生错误！传入数据为空！");
				extAjaxResponse.setSuccess(false);
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统发生错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}
}
