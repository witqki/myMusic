package com.example.myMusic.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.myMusic.common.dto.CheckcCodeDTO;
import com.example.myMusic.common.dto.UserloginDTO;
import com.example.myMusic.common.dto.UserregisteredDTO;
import com.example.myMusic.common.dto.discuss.AdddiscussDTO;
import com.example.myMusic.common.dto.discuss.DiscussDTO;
import com.example.myMusic.common.dto.discuss.DiscussResponseDTO;
import com.example.myMusic.common.dto.discuss.ReplyDTO;
import com.example.myMusic.common.dto.music.SearchMusicDTO;
import com.example.myMusic.common.dto.music.SearchResponseDTO;
import com.example.myMusic.common.dto.singet.SingerDTO;
import com.example.myMusic.common.dto.singet.SingerRspDTO;
import com.example.myMusic.common.dto.songList.SongListDTO;
import com.example.myMusic.common.dto.songList.SongListRspDTO;
import com.example.myMusic.common.dto.songList.UserSongListDTO;
import com.example.myMusic.common.dto.user.AddsongDTO;
import com.example.myMusic.common.dto.user.LoginRspDTO;
import com.example.myMusic.common.dto.user.UserresetDTO;
import com.example.myMusic.common.jwt.Jwt;
import com.example.myMusic.common.util.BeanUtil;
import com.example.myMusic.common.util.Rules;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.discuss.dao.DiscussDao;
import com.example.myMusic.discuss.entities.Discuss;
import com.example.myMusic.music.dao.MusicDao;
import com.example.myMusic.music.entities.Music;
import com.example.myMusic.songList.dao.SongListDao;
import com.example.myMusic.songList.entities.SongList;
import com.example.myMusic.user.dao.CodeDao;
import com.example.myMusic.user.dao.UserDao;
import com.example.myMusic.user.entities.Code;
import com.example.myMusic.user.entities.User;
import com.example.myMusic.user.service.UserService;
import com.example.myMusic.user.web.UserController;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private DiscussDao discussDao;
    @Autowired
    private CodeDao codeDao;
    @Autowired
	private UserService userService=null;
    @Autowired
    private SongListDao songListDao;
    
    @Autowired
    private MusicDao musicDao;
    
	@Override
	public User save(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Long[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<User> findAll(Specification<User> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginRspDTO userlogin(UserloginDTO userlogindto) {
		Map<String, Object> payload = new HashMap<>();
		Long startTime = System.currentTimeMillis();
		payload.put("iat", startTime);
		payload.put("ext", startTime + 60 * 60 * 1000);
		//这里三种登录方式，0-用户名，1-手机号，2-邮箱
		LoginRspDTO loginRspDTO=new LoginRspDTO();
	
		User user=new User();
		if(userlogindto.getCheckcode().intValue()==0) {
			logger.info("this is :"+userlogindto.getMessage());
			if(userlogindto.getMessage().trim()==null||userlogindto.getMessage().trim().equals(""))
			{
				loginRspDTO.setMsg("输入用户名为空！");
				loginRspDTO.setSuccess(false);
			}else if(userlogindto.getPassword().trim()==null||userlogindto.getPassword().trim().equals("")) {
				loginRspDTO.setMsg("输入密码为空！");
				loginRspDTO.setSuccess(false);
			}
			
			else {
				List<User> list=userDao.findByUsername(userlogindto.getMessage().trim());
				if(list.size()!=0&&list!=null) {
					user=list.get(0);
					if(user.getPassword().equals(userlogindto.getPassword().trim()))
					{
						if(user.isIsadmin()) {
							loginRspDTO.setAdmin(true);
						}
					//	payload.put("uid", user.getId());
					//	String token = Jwt.createToken(payload);
						loginRspDTO.setMsg("登录成功");
						loginRspDTO.setUserid(user.getId());
						//loginRspDTO.setToken(token);
						loginRspDTO.setName(user.getName());
					//	loginRspDTO.setPicturepath(user.getPictureUrl());
						loginRspDTO.setSuccess(true);
						loginRspDTO.setAdmin(user.isIsadmin());
						loginRspDTO.setSuperadmin(false);
					}else 
					{
						loginRspDTO.setMsg("输入密码不正确！！");
						loginRspDTO.setSuccess(false);
					}
				}else {
					loginRspDTO.setMsg("系统查无此账号！");
					loginRspDTO.setSuccess(false);
				}
			}
			
			
		}else if(userlogindto.getCheckcode().intValue()==1) {
			if(userlogindto.getMessage().trim()==null||userlogindto.getMessage().trim().equals(""))
			{
				loginRspDTO.setMsg("输入手机号为空！");
				loginRspDTO.setSuccess(false);
			}else if(userlogindto.getPassword().trim()==null||userlogindto.getPassword().trim().equals("")) {
				loginRspDTO.setMsg("输入密码为空！");
				loginRspDTO.setSuccess(false);
			}
			
			else {
				List<User> list=userDao.findByUserphone(userlogindto.getMessage().trim());
				if(list.size()!=0&&list!=null) {
					user=list.get(0);
					if(user.getPassword().equals(userlogindto.getPassword().trim()))
					{
						if(user.isIsadmin()) {
							loginRspDTO.setAdmin(true);
						}
						payload.put("uid", user.getId());
						//String token = Jwt.createToken(payload);
						loginRspDTO.setMsg("登录成功");
						loginRspDTO.setUserid(user.getId());
						if(BeanUtil.isNull(user.getName())) {
							loginRspDTO.setName(user.getPhone());
						}else {
							loginRspDTO.setName(user.getName());
						}
						
						//loginRspDTO.setToken(token);
					//	loginRspDTO.setPicturepath(user.getPictureUrl());
						loginRspDTO.setSuccess(true);
						loginRspDTO.setAdmin(user.isIsadmin());
						loginRspDTO.setSuperadmin(false);
					}else 
					{
						loginRspDTO.setMsg("输入密码不正确！！");
						loginRspDTO.setSuccess(false);
					}
				}else {
					loginRspDTO.setMsg("系统查无此账号！");
					loginRspDTO.setSuccess(false);
				}
			}
		}else if(userlogindto.getCheckcode().intValue()==2) {
			if(userlogindto.getMessage().trim()==null||userlogindto.getMessage().trim().equals(""))
			{
				loginRspDTO.setMsg("输入邮箱为空！");
				loginRspDTO.setSuccess(false);
			}else if(userlogindto.getPassword().trim()==null||userlogindto.getPassword().trim().equals("")) {
				loginRspDTO.setMsg("输入密码为空！");
				loginRspDTO.setSuccess(false);
			}
			
			else {
				List<User> list=userDao.findByUseremail(userlogindto.getMessage().trim());
				if(list.size()!=0&&list!=null) {
					user=list.get(0);
					if(user.getPassword().equals(userlogindto.getPassword().trim()))
					{
						if(user.isIsadmin()) {
							loginRspDTO.setAdmin(true);
						}
						payload.put("uid", user.getId());
						//String token = Jwt.createToken(payload);
						loginRspDTO.setMsg("登录成功");
						loginRspDTO.setUserid(user.getId());
						if(BeanUtil.isNull(user.getName())) {
							loginRspDTO.setName(user.getEmail());
						}else {
							loginRspDTO.setName(user.getName());
						}
					
					//	loginRspDTO.setToken(token);
					//	loginRspDTO.setPicturepath(user.getPictureUrl());
						loginRspDTO.setSuccess(true);
						loginRspDTO.setAdmin(user.isIsadmin());
						loginRspDTO.setSuperadmin(false);
					}else 
					{
						loginRspDTO.setMsg("输入密码不正确！！");
						loginRspDTO.setSuccess(false);
					}
				}else {
					loginRspDTO.setMsg("系统查无此账号！");
					loginRspDTO.setSuccess(false);
				}
			}
		}else{
			logger.info("登录方式的选择出现问题");
			loginRspDTO.setMsg("系统发生错误！");
			loginRspDTO.setSuccess(false);
			
		}
		return loginRspDTO;
		
		
		
	
	}

	
	public ExtAjaxResponse userregistered(UserregisteredDTO userregisteredDTO) {
		// TODO Auto-generated method stub
		
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse(true,"");
		if(userregisteredDTO.getName().trim()==null||userregisteredDTO.getName().trim().equals("")) {
			extAjaxResponse.setMsg("用户名为空！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}else if(userregisteredDTO.getName().length()<2||userregisteredDTO.getName().length()>8) {
			extAjaxResponse.setMsg("用户名长度在 2 到 8个字符！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}else if(!(userDao.findByUsername(userregisteredDTO.getName().trim()).size()==0)) {
			extAjaxResponse.setMsg("已存在该用户！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		
		else if(userregisteredDTO.getPassword()==null||userregisteredDTO.getPassword().trim().equals(""))
		{
			extAjaxResponse.setMsg("密码为空！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}else if(userregisteredDTO.getCpassword().trim()==null||userregisteredDTO.getCpassword().trim().equals("")) {
			extAjaxResponse.setMsg("确认密码为空！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}else if(!userregisteredDTO.getPassword().trim().equals(userregisteredDTO.getCpassword().trim())) {
			extAjaxResponse.setMsg("密码与确认密码不相同！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}else if(!Rules.passwordrule(userregisteredDTO.getPassword().trim())) {
			extAjaxResponse.setMsg("密码要包含数字、字母、下划线，长度在 5 到 10 个字符，并且要同时含有数字和字母！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		
		else if((!userregisteredDTO.getPhone().trim().equals(""))&&(userregisteredDTO.getPhone().trim()!=null)) {
			boolean b=Rules.phonerule(userregisteredDTO.getPhone().trim());
			if(!b) {
				extAjaxResponse.setMsg("电话号码不正确！");
				extAjaxResponse.setSuccess(false);
				return extAjaxResponse;
			}
			else {
				if(!(userDao.findByUserphone(userregisteredDTO.getPhone().trim()).size()==0)) {
					extAjaxResponse.setMsg("电话号码已存在！");
					extAjaxResponse.setSuccess(false);
					return extAjaxResponse;
				}
			}
		}else {}
		
		if((!userregisteredDTO.getEmail().trim().equals(""))&&(userregisteredDTO.getEmail().trim()!=null)) {
			boolean b=Rules.emailrule(userregisteredDTO.getEmail().trim());
			
			if(b==false) {
				
				extAjaxResponse.setMsg("邮箱格式不正确！");
				extAjaxResponse.setSuccess(false);
				return extAjaxResponse;
			}
			else {
				
				if(!(userDao.findByUseremail(userregisteredDTO.getEmail().trim()).size()==0)) {
					extAjaxResponse.setMsg("邮箱已存在！");
					extAjaxResponse.setSuccess(false);
					return extAjaxResponse;
					
				}
			}
		}
	
		if(extAjaxResponse.isSuccess()) {
			User user=new User();
			user=BeanUtil.UserregisteredDTOtoUser(userregisteredDTO, new User());
			
			try{
				user.setCreateTime(new Date());
				user.setUpdateTime(new Date());
				userDao.save(user);
				//logger.info("成功");
				extAjaxResponse.setMsg("注册成功！");
				extAjaxResponse.setSuccess(true);
				return extAjaxResponse;
			}catch(Exception e) {
				extAjaxResponse.setMsg("注册失败，系统错误！");
				extAjaxResponse.setSuccess(false);
				return extAjaxResponse;
			}
		}
		
		return extAjaxResponse;
	}

	@Override
	public ExtAjaxResponse userreset(UserresetDTO userresetDTO) {
		// TODO Auto-generated method stub
		//进行清除过期验证码
		List<Code> list=(List<Code>) codeDao.findAll();
		long nowtime=new Date().getTime();
		if(list==null||list.size()==0) {
			
		}else {
			for(Code savecode:list) {
				long time=nowtime-savecode.getBuildtime();
				if(time<=BeanUtil.getLimittime()) {
					
				}else {
					codeDao.delete(savecode);
				}
				
			}
		}
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		ExtAjaxResponse pswcheck=BeanUtil.checkpsw(userresetDTO.getPsw(), userresetDTO.getConpsw());	
		
		if(userresetDTO.getId()!=null) {
			if(BeanUtil.isNull(userresetDTO.getCode())) {
				extAjaxResponse.setMsg("输入的验证码为空");
				extAjaxResponse.setSuccess(false);
			}else if(!pswcheck.isSuccess()) {
				extAjaxResponse.setMsg(pswcheck.getMsg());
				extAjaxResponse.setSuccess(false);
			}
			else {
				if(userresetDTO.getId().equals(new Integer(0))) {//手机号
					ExtAjaxResponse checkphone=BeanUtil.checkphone(userresetDTO.getSender());
					if(checkphone.isSuccess()) {
						 List<Code> listphone=codeDao.exitPhone(userresetDTO.getSender().trim(), userresetDTO.getCode().trim());
						 if(listphone.size()==0||listphone==null) {//找不到
							 extAjaxResponse.setMsg("验证码不存在或已过期！");
								extAjaxResponse.setSuccess(false);
						 }else {//找到
							 List<User> phonelist=userDao.findByUserphone(userresetDTO.getSender().trim());
						     if(phonelist.size()!=0&&phonelist!=null) {
						    	 User user=phonelist.get(0);
						    	 user.setPassword(userresetDTO.getPsw().trim());
						    	 userDao.save(user);
						    	    extAjaxResponse.setMsg("success");
									extAjaxResponse.setSuccess(true);
						     }else {
						    	   extAjaxResponse.setMsg("该手机号没有注册");
									extAjaxResponse.setSuccess(false);
						     }
						 }
								 
					}else {//手机号不正确
						extAjaxResponse.setMsg(checkphone.getMsg());
						extAjaxResponse.setSuccess(false);
					}
					
				}
				else if(userresetDTO.getId().equals(new Integer(1))) {//邮箱
					ExtAjaxResponse checkemail=BeanUtil.checkemail(userresetDTO.getSender());
					if(checkemail.isSuccess()) {
						 List<Code> listemail=codeDao.exitEmail(userresetDTO.getSender().trim(), userresetDTO.getCode().trim());
						 if(listemail.size()==0||listemail==null) {//找不到
							 extAjaxResponse.setMsg("验证码不存在或已过期！");
								extAjaxResponse.setSuccess(false);
						 }else {//找到
							 List<User> maillist=userDao.findByUseremail(userresetDTO.getSender().trim());
						     if(maillist.size()!=0&&maillist!=null) {
						    	 User user=maillist.get(0);
						    	 user.setPassword(userresetDTO.getPsw().trim());
						    	 userDao.save(user);
						    	    extAjaxResponse.setMsg("success");
									extAjaxResponse.setSuccess(true);
						     }
						     else {
						    	   extAjaxResponse.setMsg("该邮箱没有存在！");
									extAjaxResponse.setSuccess(false);
						     }
						 }
					}else {//邮箱不正确
						extAjaxResponse.setMsg(checkemail.getMsg());
						extAjaxResponse.setSuccess(false);
					}
				}else {
					extAjaxResponse.setMsg("传输的id有误");
					extAjaxResponse.setSuccess(false);
				}
			}
		}
		else {
			extAjaxResponse.setMsg("传输的id有为空");
			extAjaxResponse.setSuccess(false);
		}
		
		return extAjaxResponse;
	}

	@Override
	public ExtAjaxResponse sendcheckcode(CheckcCodeDTO checkDTO) {
		// TODO Auto-generated method stub
		//进行清除过期验证码
		List<Code> list=(List<Code>) codeDao.findAll();
		long nowtime=new Date().getTime();
		for(Code savecode:list) {
			long time=nowtime-savecode.getBuildtime();
			if(time<=BeanUtil.getLimittime()) {
				
			}else {
				codeDao.delete(savecode);
			}
			
		}
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(checkDTO.getId()!=null) {
			if(BeanUtil.isNull(checkDTO.getSender())) {
				extAjaxResponse.setMsg("请输入对应的邮箱或手机号");
				extAjaxResponse.setSuccess(false);
			}else {
				if(checkDTO.getId().equals(new Integer(0))) {//手机
					ExtAjaxResponse checkphone=BeanUtil.checkphone(checkDTO.getSender());
					if(checkphone.isSuccess()) {
						//发送验证码
						String code=BeanUtil.getCode();
						ExtAjaxResponse sendphone=BeanUtil.sendPhone(checkDTO.getSender().trim(), code);
						if(sendphone.isSuccess()) {
							extAjaxResponse.setMsg("success");
							extAjaxResponse.setSuccess(true);
						}else {
							extAjaxResponse.setMsg(sendphone.getMsg());
							extAjaxResponse.setSuccess(false);
						}
					}else {
						extAjaxResponse.setMsg(checkphone.getMsg());
						extAjaxResponse.setSuccess(false);
					}
					
				}
				else if(checkDTO.getId().equals(new Integer(1))) {//邮箱
					ExtAjaxResponse checkemail=BeanUtil.checkemail(checkDTO.getSender().trim());
					if(checkemail.isSuccess()) {
						//发送验证码
						String code=BeanUtil.getCode();
						ExtAjaxResponse sendmail=BeanUtil.sendEmail(checkDTO.getSender().trim(), code);
						if(sendmail.isSuccess()) {
							Code c=new Code();
							c.setBuildtime((new Date()).getTime());
							c.setCode(code);
							c.setEmail(checkDTO.getSender().trim());
							codeDao.save(c);
							extAjaxResponse.setMsg("success");
							extAjaxResponse.setSuccess(true);
						}else {
							extAjaxResponse.setMsg(sendmail.getMsg());
							extAjaxResponse.setSuccess(false);
						}
					}else {
						extAjaxResponse.setMsg(checkemail.getMsg());
						extAjaxResponse.setSuccess(false);
					}
				}else {
					extAjaxResponse.setMsg("传输的id有有误！");
					extAjaxResponse.setSuccess(false);
				}
			}
		}
		else {
			extAjaxResponse.setMsg("传输的id有为空");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}

	@Override
	public ExtAjaxResponse createSongList(UserSongListDTO userSongListDTO) {
		// TODO Auto-generated method stub
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(userSongListDTO.getUserId()!=null) {
			if(userDao.existsById(userSongListDTO.getUserId())) {
				if(BeanUtil.isNull(userSongListDTO.getName().trim())) {
					extAjaxResponse.setMsg("请填写歌单名");
					extAjaxResponse.setSuccess(false);
				}else {
					//songListDao
					ExtAjaxResponse ExtAjaxResponse=BeanUtil.badword(userSongListDTO.getName().trim());
					if(!ExtAjaxResponse.isSuccess()) {
						extAjaxResponse.setMsg("含有敏感词："+ExtAjaxResponse.getMsg());
						extAjaxResponse.setSuccess(false);
						return extAjaxResponse;
					}
					if((userSongListDTO.getName().trim().length()<1)||(userSongListDTO.getName().trim().length()>7))
					{
						extAjaxResponse.setMsg("请输入长度在1-7内的字符！");
						extAjaxResponse.setSuccess(false);
						return extAjaxResponse;
					}
					User user=userDao.findById(userSongListDTO.getUserId()).get();
					SongList songList=new SongList();
					List<SongList> songlist=user.getSonglist();//判断名字是否存在
					if(songlist.size()==0||songlist==null) {
						List<SongList> list=new ArrayList<SongList>();
						user.setSonglist(list);
						
					}
					else {
						for(SongList SongList:songlist) {
							if(!SongList.isIsopen()) {
								if(SongList.getName().equals(userSongListDTO.getName().trim())) {
									extAjaxResponse.setMsg("歌单名已存在！");
									extAjaxResponse.setSuccess(false);
									return extAjaxResponse;
								}
							}
						
						}
					}
					
					songList.setCreateTime(new Date());
					songList.setUpdateTime(new Date());
					songList.setName(userSongListDTO.getName().trim());
					List<User> userlist=new ArrayList<User>();
					userlist.add(user);
					songList.setUserlist(userlist);
					
					userDao.save(user);
					songListDao.save(songList);
					extAjaxResponse.setMsg("创建成功！");
					extAjaxResponse.setSuccess(true);
				}
			}else{
				extAjaxResponse.setMsg("传输的用户id不存在数据库！");
				extAjaxResponse.setSuccess(false);
			}
		}else {
			extAjaxResponse.setMsg("传输的id有为空");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}

	@Override
	public ExtAjaxResponse deleteSongList(UserSongListDTO userSongListDTO) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(userSongListDTO.getSongListId().equals(0L)) {//判断是否为“我喜欢”基础歌单
			extAjaxResponse.setMsg("此歌单不支持删除！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		if(userSongListDTO.getUserId()!=null&&userSongListDTO.getSongListId()!=null) {
			if(userDao.existsById(userSongListDTO.getUserId())&&songListDao.existsById(userSongListDTO.getSongListId()))
			{
				User user=userDao.findById(userSongListDTO.getUserId()).get();
				SongList songList=songListDao.findById(userSongListDTO.getSongListId()).get();				
				
				List<User> userList=songList.getUserlist();
				if(userList.size()==0||userList==null) {
					extAjaxResponse.setMsg("发生错误：该歌单的用户为空");
					extAjaxResponse.setSuccess(false);
					return extAjaxResponse;
				}else {
					for(User ww:userList) {
					
						ww.getSonglist().remove(songList);
						songList.setUserlist(null);
						userDao.save(ww);
					}	
				}
					
				if(songList.getMusiclist()!=null&&songList.getMusiclist().size()!=0) {
					List<Music> list=songList.getMusiclist();
					for(Music music:list) {
						music.getSonglist().remove(songList);
						songList.getMusiclist().remove(music);
						musicDao.save(music);
						
					}
				}
				songListDao.save(songList);
				songListDao.deleteById(userSongListDTO.getSongListId());
				extAjaxResponse.setMsg("删除成功！");
				extAjaxResponse.setSuccess(true);
			}else {
				extAjaxResponse.setMsg("传输的id中有不存在数据库的id！");
				extAjaxResponse.setSuccess(false);
			}
		}else {
			extAjaxResponse.setMsg("传输的id有为空");
			extAjaxResponse.setSuccess(false);
		}
		
		return extAjaxResponse;
	}

	@Override
	public ExtAjaxResponse updateSongList(UserSongListDTO userSongListDTO) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(userSongListDTO.getSongListId().equals(0L)) {
			extAjaxResponse.setMsg("此歌单不支持修改名字！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		if(userSongListDTO.getUserId()!=null&&userSongListDTO.getSongListId()!=null) {
			if(BeanUtil.isNull(userSongListDTO.getName())) {
				extAjaxResponse.setMsg("修改的歌单名为空");
				extAjaxResponse.setSuccess(false);
			}else {
				ExtAjaxResponse ExtAjaxResponse=BeanUtil.badword(userSongListDTO.getName().trim());
				if(!ExtAjaxResponse.isSuccess()) {
					extAjaxResponse.setMsg("含有敏感词："+ExtAjaxResponse.getMsg());
					extAjaxResponse.setSuccess(false);
					return extAjaxResponse;
				}
				if((userSongListDTO.getName().trim().length()<1)||(userSongListDTO.getName().trim().length()>7))
				{
					extAjaxResponse.setMsg("请输入长度在1-7内的字符！");
					extAjaxResponse.setSuccess(false);
					return extAjaxResponse;
				}
				if(userDao.existsById(userSongListDTO.getUserId())&&songListDao.existsById(userSongListDTO.getSongListId()))
				{
					User user=userDao.findById(userSongListDTO.getUserId()).get();
					SongList songList=songListDao.findById(userSongListDTO.getSongListId()).get();
					User yy=songList.getUserlist().get(0);
					if(yy.getId().equals(user.getId())) {
						//首先判断歌单名是否重复
						List<SongList> list=user.getSonglist();
						if(list.size()!=0&&list!=null) {
							for(SongList songlist:list ) {
								if(!songlist.isIsopen()) {
									if(songlist.getName().equals(userSongListDTO.getName().trim())) {
										extAjaxResponse.setMsg("歌单名已存在！");
										extAjaxResponse.setSuccess(false);
										return extAjaxResponse;
									}
								}
							}
							songList.setName(userSongListDTO.getName().trim());
							songListDao.save(songList);
							extAjaxResponse.setMsg("修改成功！");
							extAjaxResponse.setSuccess(true);
						}else {
							extAjaxResponse.setMsg("找不到您的歌单,系统发生错误！");
							extAjaxResponse.setSuccess(false);
						}
					}
					else {
						extAjaxResponse.setMsg("此歌单您不能修改！");
						extAjaxResponse.setSuccess(false);
					}
				}else {
					extAjaxResponse.setMsg("传输的id有不在数据库！");
					extAjaxResponse.setSuccess(false);
				}
			}
		}else {
			extAjaxResponse.setMsg("传输的id有为空");
			extAjaxResponse.setSuccess(false);
		}
		
		
		return extAjaxResponse;
	}

	@Override
	public SongListRspDTO lookSongList(Long id) {
		SongListRspDTO songListRspDTO=new SongListRspDTO();
		List<SongListDTO> songList=new ArrayList<SongListDTO>();
		if(id!=null) {
			if(userDao.existsById(id)) {
				User user=userDao.findById(id).get();
				SongListDTO mylike=new SongListDTO();
				mylike.setSongListId(0L);
				mylike.setName("我喜欢");
			//	mylike.setPicturepath(null);
				if(user.getMusiclist()==null||user.getMusiclist().size()==0) {
				  mylike.setSongNumber(new Integer(0));
				}else {
					mylike.setSongNumber(new Integer(user.getMusiclist().size()));
				}
				songList.add(mylike);
				if(user.getSonglist()!=null&&user.getSonglist().size()!=0) {
					List<SongList> list=user.getSonglist();
					for(SongList SongList:list) {
						SongListDTO songListDTO=new SongListDTO();
						if(SongList.isIsopen()) {//判断是自己创建的还是收藏的
						}else {
							songListDTO.setName(SongList.getName());
							songListDTO.setSongListId(SongList.getId());
							//songListDTO.setPicturepath(SongList.getPicturepath());
							if(SongList.getMusiclist()!=null) {
								songListDTO.setSongNumber(new Integer(SongList.getMusiclist().size()));
							}else {
								songListDTO.setSongNumber(new Integer(0));
							}
							songList.add(songListDTO);
						}
					}
					
				}
				songListRspDTO.setSongList(songList);
				songListRspDTO.setMsg("success！");
				songListRspDTO.setSuccess(true);
			}else {
				songListRspDTO.setMsg("该用户查找不到！");
				songListRspDTO.setSuccess(false);
			}
		}else {
			songListRspDTO.setMsg("传输的id为空！");
			songListRspDTO.setSuccess(false);
		}
		return songListRspDTO;
	}

	@Override
	public ExtAjaxResponse likeSongList(UserSongListDTO userSongListDTO) {
		// TODO Auto-generated method stub
		ExtAjaxResponse extAjaxResponse= new ExtAjaxResponse();
		if(userSongListDTO.getUserId()!=null&&userSongListDTO.getSongListId()!=null) {
			//检查数据库有无保存此歌单信息
			List<SongList> listdto=(List<SongList>) songListDao.findAll();
			boolean b=false;
			if(listdto==null||listdto.size()==0) {
				
			}
			else 
			{
				for(SongList ss:listdto) {
					if(ss.isIsopen()) {//如果为网易云的歌单
						if(ss.getTrueid().equals(userSongListDTO.getSongListId())) {
							b=true;
							break;
						}
					}
				}
			}
		
			if(b==false) {//如果不存在，就存入
				SongList kk=new SongList();
				kk.setIsopen(true);
				kk.setTrueid(userSongListDTO.getSongListId());
				List<User> mm=new ArrayList<User>();
				kk.setUserlist(mm);
		       	songListDao.save(kk);
			}
			if(userDao.existsById(userSongListDTO.getUserId()))//检查用户是否存在
			{
				User user=userDao.findById(userSongListDTO.getUserId()).get();
				SongList songList=new SongList();
				List<SongList> listyy=(List<SongList>) songListDao.findAll();
				if(listyy==null||listyy.size()==0) {
					extAjaxResponse.setMsg("系统错误：找不到歌单信息");
					extAjaxResponse.setSuccess(false);
					return extAjaxResponse;
				}
				else {		
					for(SongList jj:listyy) {
						if(jj.isIsopen()) {
							if(jj.getTrueid().equals(userSongListDTO.getSongListId())) {
								songList=jj;								
								break;
							}
						}
					}			
				}	
				List<User> userList=songList.getUserlist();
//				extAjaxResponse.setMsg("您已收藏此歌单！"+(userList==null));
//				extAjaxResponse.setSuccess(false);
//				return extAjaxResponse;
				 if((userList.size()==0)||(userList==null)) {//此歌单无用户收藏
					 List<User> oo=new ArrayList<User>();
						oo.add(user);
						songList.setUserlist(oo);
				
				 }
				else 
				{
					for(User User:userList) {
						if(User.getId().equals(userSongListDTO.getUserId())) {
							extAjaxResponse.setMsg("您已收藏此歌单！");
							extAjaxResponse.setSuccess(false);
							return extAjaxResponse;
						}
				     }
					
					songList.getUserlist().add(user);
				}
				
				List<SongList> pp=user.getSonglist();
				if(pp==null||pp.size()==00) {//此用户无歌单
					List<SongList> nn=new ArrayList<SongList>();
					nn.add(songList);
					user.setSonglist(nn);
				}else {
					user.getSonglist().add(songList);		
				}
					
				userDao.save(user);
				songListDao.save(songList);
				extAjaxResponse.setMsg("收藏成功！");
				extAjaxResponse.setSuccess(true);
			}else {
				extAjaxResponse.setMsg("传输的id在数据库查找不到！");
				extAjaxResponse.setSuccess(false);
			}
		}else {
			extAjaxResponse.setMsg("传输的id为空！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}

	@Override
	public SongListRspDTO check_likeSongList(Long id) {
		SongListRspDTO songListRspDTO=new SongListRspDTO();
		List<SongListDTO> songListDTOlist=new ArrayList<SongListDTO>();
	     if(id!=null) {
	    	 if(userDao.existsById(id)) {
	    		 User user=userDao.findById(id).get();
	    		 List<SongList> songList= user.getSonglist();
		    		for(SongList SongList:songList) {
		    			if(SongList.isIsopen()) {
						SongListDTO songListDTO=new SongListDTO();
						songListDTO.setSongListId(SongList.getTrueid());
						songListDTOlist.add(songListDTO);
		    				
		    			}
		    		}
	    		    songListRspDTO.setSongList(songListDTOlist);
	    		    songListRspDTO.setMsg("success！");
					songListRspDTO.setSuccess(true);
	    		 
	    		 
	    	 }else {
	    		 songListRspDTO.setMsg("该用户查找不到！");
					songListRspDTO.setSuccess(false);
	    	 }
	     }else {
	    	    songListRspDTO.setMsg("传输的id为空！");
				songListRspDTO.setSuccess(false);
	     }
		return songListRspDTO;
	}

	@Override
	public ExtAjaxResponse delete_likeSongList(UserSongListDTO userSongListDTO) {
		// TODO Auto-generated method stub
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(userSongListDTO.getUserId()!=null&&userSongListDTO.getSongListId()!=null) {
			if(userDao.existsById(userSongListDTO.getUserId())&&songListDao.existsById(userSongListDTO.getSongListId()))
			{
				User user=userDao.findById(userSongListDTO.getUserId()).get();
				SongList songList=songListDao.findById(userSongListDTO.getSongListId()).get();
				user.getSonglist().remove(songList);
				songList.getUserlist().remove(user);
				userDao.save(user);
				songListDao.save(songList);
				extAjaxResponse.setMsg("删除成功！");
				extAjaxResponse.setSuccess(true);
				
				
			}else {
				extAjaxResponse.setMsg("传输的id在数据库查找不到！");
				extAjaxResponse.setSuccess(false);
			}
		}else {
			extAjaxResponse.setMsg("传输的id为空！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}

	@Override
	public ExtAjaxResponse add_likeSongList(UserSongListDTO userSongListDTO) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(userSongListDTO.getUserId()!=null&&userSongListDTO.getSongListId()!=null) {
			if(userDao.existsById(userSongListDTO.getUserId())&&songListDao.existsById(userSongListDTO.getSongListId()))
			{
				if(BeanUtil.isNull(userSongListDTO.getName())) {
					extAjaxResponse.setMsg("输入歌单名为空！");
					extAjaxResponse.setSuccess(false);
				}else {
					User user=userDao.findById(userSongListDTO.getUserId()).get();
					SongList songList=songListDao.findById(userSongListDTO.getSongListId()).get();
					List<SongList> songlist=user.getSonglist();
					if(songlist.size()==0||songlist==null) {//没有歌单
						SongList SongList=new SongList();
						SongList.setName(userSongListDTO.getName());
						SongList.setCreateTime(new Date());
						SongList.setUpdateTime(new Date());
//						SongList.setPicturepath(songList.getPicturepath());
//						SongList.setUserid(user.getId());
						SongList.setMusiclist(songList.getMusiclist());
						SongList.getUserlist().add(user);
						songListDao.save(SongList);
					}else {//有歌单检查歌单名重复
						songlist=user.getSonglist();
						for(SongList copy:songlist) {
							if(copy.getName().equals(userSongListDTO.getName().trim())) {
								if(copy.getId().equals(user.getId())) {
									extAjaxResponse.setMsg("歌单名已存在！");
									extAjaxResponse.setSuccess(false);
									return extAjaxResponse;
								}
							}
						}
						SongList SongList=new SongList();
						SongList.setName(userSongListDTO.getName());
						SongList.setCreateTime(new Date());
						SongList.setUpdateTime(new Date());
//						SongList.setPicturepath(songList.getPicturepath());
//						SongList.setUserid(user.getId());
						SongList.setMusiclist(songList.getMusiclist());
						SongList.getUserlist().add(user);
						songListDao.save(SongList);
					}
					userDao.save(user);
					extAjaxResponse.setMsg("已成功添加到新建歌单！");
					extAjaxResponse.setSuccess(false);
				}
			}else {
				extAjaxResponse.setMsg("传输的id在数据库查找不到！");
				extAjaxResponse.setSuccess(false);
			}
		}else {
			extAjaxResponse.setMsg("传输的id为空！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}

	@Override
	public SearchResponseDTO mySongList(UserSongListDTO userSongListDTO) {
		SearchResponseDTO searchResponseDTO=new SearchResponseDTO();
		List<SearchMusicDTO> list=new ArrayList<SearchMusicDTO>();
		if(userSongListDTO.getUserId()!=null) {			
			if(userDao.existsById(userSongListDTO.getUserId())) {//判断用户
				User user=userDao.findById(userSongListDTO.getUserId()).get();
				if(userSongListDTO.getSongListId().equals(0L)||userSongListDTO.getSongListId()==null) {//如果为0，选择我喜欢的歌曲			
					List<Music> musicList=user.getMusiclist();
					if(musicList!=null&&musicList.size()!=0) {
						for(Music music:musicList) {
							SearchMusicDTO searchMusicDTO=new SearchMusicDTO();
							searchMusicDTO.setTrueid(music.getTrueid());
							//searchMusicDTO=BeanUtil.musicTomusicDRTO(music,searchMusicDTO);
							list.add(searchMusicDTO);
						}
						searchResponseDTO.setList(list);
						searchResponseDTO.setSuccess(true);
						searchResponseDTO.setMsg("success");
					}else {
						searchResponseDTO.setMsg("您还没收藏喜欢的歌曲");
						searchResponseDTO.setSuccess(false);
					}
				}
				else {
					if(songListDao.existsById(userSongListDTO.getSongListId())) {
						List<Music> musicList=songListDao.findById(userSongListDTO.getSongListId()).get().getMusiclist();
						if(musicList.size()==0||musicList==null) {
							searchResponseDTO.setMsg("还没有歌曲！");
							searchResponseDTO.setSuccess(false);
						}else {
							for(Music music:musicList) {
								SearchMusicDTO searchMusicDTO=new SearchMusicDTO();
								searchMusicDTO.setTrueid(music.getTrueid());
							//	searchMusicDTO=BeanUtil.musicTomusicDRTO(music,searchMusicDTO);
								list.add(searchMusicDTO);
							}
							searchResponseDTO.setList(list);
							searchResponseDTO.setSuccess(true);
							searchResponseDTO.setMsg("success");
						}
					}else {
						searchResponseDTO.setMsg("系统错误！");
						searchResponseDTO.setSuccess(false);
					}
					
				}
			}else {
				searchResponseDTO.setMsg("传入id不存在数据库");
				searchResponseDTO.setSuccess(false);
			}
		}else {
			searchResponseDTO.setMsg("传入id为空");
			searchResponseDTO.setSuccess(false);
		}
		return searchResponseDTO;
	}

	@Override
	public LoginRspDTO getmymessage(Long id) {
		LoginRspDTO loginRspDTO=new LoginRspDTO();
		try {
			if(userDao.existsById(id)) {
				User user=userDao.findById(id).get();
				loginRspDTO.setAdmin(user.isIsadmin());
				loginRspDTO.setName(user.getName());
				loginRspDTO.setPhone(user.getPhone());
				loginRspDTO.setEmail(user.getEmail());
				loginRspDTO.setSex(user.isSex());
				loginRspDTO.setMsg("success");
				loginRspDTO.setSuccess(true);
			}else {
				loginRspDTO.setMsg("用户不存在！");
				loginRspDTO.setSuccess(false);
			}
		}catch(Exception e) {
			loginRspDTO.setMsg("系统错误");
			loginRspDTO.setSuccess(false);
		}
		return loginRspDTO;
	}

	@Override
	public ExtAjaxResponse savemymessage(LoginRspDTO loginRspDTO) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		try {
			if(userDao.existsById(loginRspDTO.getUserid())) {
				User user=userDao.findById(loginRspDTO.getUserid()).get();
				
				if(BeanUtil.isNull(loginRspDTO.getName())) {
					extAjaxResponse.setMsg("用户名为空！");
					extAjaxResponse.setSuccess(false);
					return extAjaxResponse;
				}else {
					if(loginRspDTO.getName().trim().equals(user.getName())) {
						
					}else {
						List<User> list=userDao.findByUsername(loginRspDTO.getName().trim());
						if(list.size()==0||list==null) {
							
						}else {
							
							extAjaxResponse.setMsg("用户名已存在！");
							extAjaxResponse.setSuccess(false);
							return extAjaxResponse;
						}
					}
					
				}
				
				if(BeanUtil.isNull(loginRspDTO.getEmail()))
				{
					
				}else {
					if(user.getEmail().equals(loginRspDTO.getEmail().trim())) {
						
					}else {
						List<User> list=userDao.findByUseremail(loginRspDTO.getEmail().trim());
						if(list.size()==0||list==null) {
							if(Rules.emailrule(loginRspDTO.getEmail().trim())) {
								
							}else {
								extAjaxResponse.setMsg("邮箱不符合要求！");
								extAjaxResponse.setSuccess(false);
								return extAjaxResponse;
							}
							
						}else {
							extAjaxResponse.setMsg("邮箱已存在！");
							extAjaxResponse.setSuccess(false);
							return extAjaxResponse;
						}
						
						
					}
				}
				if(BeanUtil.isNull(loginRspDTO.getPhone()))
				{
					
				}else {
					if(user.getPhone().equals(loginRspDTO.getPhone().trim())) {
						
					}else {
						List<User> list=userDao.findByUserphone(loginRspDTO.getPhone().trim());
						if(list.size()==0||list==null) {
							if(Rules.phonerule(loginRspDTO.getPhone().trim())) {
								
							}else {
								extAjaxResponse.setMsg("手机号不符合要求！");
								extAjaxResponse.setSuccess(false);
								return extAjaxResponse;
							}
							
						}else {
							extAjaxResponse.setMsg("手机号已存在！");
							extAjaxResponse.setSuccess(false);
							return extAjaxResponse;
						}
						
						
					}
				}
				user.setEmail(loginRspDTO.getEmail().trim());
				user.setPhone(loginRspDTO.getPhone().trim());
				user.setName(loginRspDTO.getName().trim());
				user.setSex(loginRspDTO.isSex());
				userDao.save(user);
				extAjaxResponse.setMsg("保存成功！");
				extAjaxResponse.setSuccess(true);
			}else {
				extAjaxResponse.setMsg("用户不存在！");
				extAjaxResponse.setSuccess(false);
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}

	@Override
	public SongListRspDTO detail_likeSongList(Long id) {
		
		return null;
	}

	@Override
	public ExtAjaxResponse searchcheck(ExtAjaxResponse extAjaxResponse) {
		ExtAjaxResponse ext=new ExtAjaxResponse();
		try {
			if(BeanUtil.isNull(extAjaxResponse.getMsg().trim())) {
				ext.setMsg("请输入内容！");
				ext.setSuccess(false);
			}
		else 
			{
				ExtAjaxResponse ExtAjaxResponse=BeanUtil.badword(extAjaxResponse.getMsg().trim());
				if(ExtAjaxResponse.isSuccess()) {
					ext.setSuccess(true);
				}else {
					ext.setMsg("输入的内容含有以下敏感词："+ExtAjaxResponse.getMsg());
					ext.setSuccess(false);
				}
			
			}
		}catch(Exception e) {
			ext.setMsg("系统错误！");
			ext.setSuccess(false);
		}
		return ext;
	}

	@Override
	public ExtAjaxResponse addsongtomy(AddsongDTO addsongDTO) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		try {
			if(addsongDTO.getUserid().equals(0L)||addsongDTO.getSongid().equals(0L)) {
				extAjaxResponse.setMsg("系统错误传入id有为空！");
				extAjaxResponse.setSuccess(false);
			}else {
				if(userDao.existsById(addsongDTO.getUserid())) {
					List<Music> list=(List<Music>) musicDao.findAll();
					if(list.size()==0||list==null) {//如果数据库没数据
						Music music=new Music();
						music.setTrueid(addsongDTO.getSongid());
						musicDao.save(music);
					}else {//如果数据库有数据
						boolean b=false;
						for(Music m:list) {
							if(m.getTrueid().equals(addsongDTO.getSongid())) {
								b=true;
								break;
							}
						}
						if(b==false) {
							Music music=new Music();
							music.setTrueid(addsongDTO.getSongid());
							musicDao.save(music);
						}
					}
					List<Music> musiclist=(List<Music>) musicDao.findAll();
					Music mu=new Music();
					for(Music g:musiclist) {
						if(g.getTrueid().equals(addsongDTO.getSongid())) {
							mu=g;
						}
					}
					//Music mu=musiclist.get(0);
					if(addsongDTO.getSonglistid().equals(0L)) {//添加到“我喜欢”的歌单
						User user=userDao.findById(addsongDTO.getUserid()).get();
						List<Music> musicdto=user.getMusiclist();
						if(musicdto.size()==0||musicdto==null) {//用户没有歌曲
							List<Music> hh=new ArrayList<Music>();
							hh.add(mu);
							user.setMusiclist(hh);
							//user.getMusiclist().add(mu);
							List<User> kk=mu.getUserlist();
							if(kk.size()==0||kk==null) {
								List<User> dd=new ArrayList<User>();
								dd.add(user);
								mu.setUserlist(dd);
								//musicDao.save(entity)
							}else {
								mu.getUserlist().add(user);
							}	
							userDao.save(user);
							musicDao.save(mu);
						}else {//用户有歌曲
							for(Music Music:musicdto) {
								if(Music.getTrueid().equals(mu.getTrueid())) {
									extAjaxResponse.setMsg("已存在您的歌单里！");
									extAjaxResponse.setSuccess(false);
									return extAjaxResponse;
								}
							}
							user.getMusiclist().add(mu);
							List<User> ii=mu.getUserlist();
							if(ii.size()==0||ii==null) {//歌曲无收藏用户
								List<User> dd=new ArrayList<User>();
								dd.add(user);
								mu.setUserlist(dd);
							}else {//歌曲有收藏用户
								mu.getUserlist().add(user);
							}
						
							userDao.save(user);
							musicDao.save(mu);
						}
						
						extAjaxResponse.setMsg("添加成功！");
						extAjaxResponse.setSuccess(true);
						return extAjaxResponse;
					}else {//添加到其它的自己创建的歌单
						if(songListDao.existsById(addsongDTO.getSonglistid())) {
							SongList songList=songListDao.findById(addsongDTO.getSonglistid()).get();
							List<Music> musicDTO=songList.getMusiclist();
							if((musicDTO.size()==0)||(musicDTO==null)) {
								List<Music> ss=new ArrayList<Music>();
								ss.add(mu);
								songList.setMusiclist(ss);
							}
							//else {//歌单存在歌曲
//								for(Music Music:musicDTO) {
//									if(Music.getTrueid().equals(mu.getTrueid())) {//判断是否含有此歌曲
//										extAjaxResponse.setMsg("已存在您的歌单里！");
//										extAjaxResponse.setSuccess(false);
//										return extAjaxResponse;
//									}
//								}
//								//此歌单不含有此歌曲
//								songList.getMusiclist().add(mu);
//							}
//							//判断歌曲是否含有歌单
//							List<SongList> hh=mu.getSonglist();
//							if(hh.size()==0||hh==null) {//为空
//								List<SongList> ff=new ArrayList<SongList>();
//								ff.add(songList);
//								mu.setSonglist(ff);
//							}else {//此歌曲存在歌单
//								mu.getSonglist().add(songList);
//							}
//
//							musicDao.save(mu);
//							songListDao.save(songList);
							extAjaxResponse.setMsg("添加成功！");
							extAjaxResponse.setSuccess(true);
							return extAjaxResponse;
						}else {
							extAjaxResponse.setMsg("系统错误:此歌单不存在！");
							extAjaxResponse.setSuccess(false);
						}
					}
				}else {
					extAjaxResponse.setMsg("系统错误:此用户还没注册！");
					extAjaxResponse.setSuccess(false);
				}
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}

	@Override
	public DiscussResponseDTO getdiscuss(AddsongDTO addsongDTO) {
		DiscussResponseDTO discussResponseDTO=new DiscussResponseDTO();
		try {
			if(addsongDTO.isIslogin()==false) {//用户没有登录
				if(addsongDTO.getSongid().equals(0L)) {
					discussResponseDTO.setMsg("系统错误！传输的id为空");
					discussResponseDTO.setSuccess(false);
				}else {
					//先判断数据库有无此歌曲信息，无就添加				
					List<Music> musiclist=(List<Music>) musicDao.findAll();
					if((musiclist.size()==0)||(musiclist==null)) {//数据库为空，则添加此歌曲
						Music music=new Music();
						music.setTrueid(addsongDTO.getSongid());
						List<User> userlist=new ArrayList<User>(); 
						List<Discuss> discusslist=new ArrayList<Discuss>();
						List<SongList> songlist=new ArrayList<SongList>();
						music.setCreateTime(new Date());
						music.setUpdateTime(new Date());
						music.setDiscusslist(discusslist);
						music.setSonglist(songlist);
						music.setUserlist(userlist);
						musicDao.save(music);
					}else {//数据库不为空，则查找trueid是否存在
						boolean b=false;//判断是否存在，false为不存在
						for(Music m:musiclist) {
							if(m.getTrueid().equals(addsongDTO.getSongid())) {
								b=true;
								break;
							}
						}
						if(b==false) {//不存在数据库，创建
							Music music=new Music();
							music.setTrueid(addsongDTO.getSongid());
							List<User> userlist=new ArrayList<User>(); 
							List<Discuss> discusslist=new ArrayList<Discuss>();
							List<SongList> songlist=new ArrayList<SongList>();
							music.setCreateTime(new Date());
							music.setUpdateTime(new Date());
							music.setDiscusslist(discusslist);
							music.setSonglist(songlist);
							music.setUserlist(userlist);
							musicDao.save(music);
						}
						
					}
					//此处确认完此歌在数据库中,寻找出此歌在数据库的内容
					List<Music> kk=(List<Music>) musicDao.findAll();
					Music dto=new Music();
					for(Music jj:kk) {//一般此歌已经在库内，所以不判断
						if(jj.getTrueid().equals(addsongDTO.getSongid())) {
							dto=jj;
							break;
						}
					}
					//找到此歌在数据库的信息
					//因为是非登录 状态，所以
					List<Discuss> discuss=dto.getDiscusslist();
					if(discuss==null||discuss.size()==0) {//如果为空
						discussResponseDTO.setMsg("暂无数据！");
						discussResponseDTO.setSuccess(true);
						discussResponseDTO.setDiscussdtolist(null);
					}else {//此歌曲含有评论信息，进行获取
						List<DiscussDTO> discussdtolist=new ArrayList<DiscussDTO>();
						
						for(Discuss ss:discuss) {
							DiscussDTO discussDTO=new DiscussDTO();
							
							discussDTO.setId(ss.getId());
							discussDTO.setName(ss.getUser().getName());
							discussDTO.setContent(ss.getContent());
							if(ss.getUserlist()==null||ss.getUserlist().size()==0) {
								discussDTO.setLikernumber(new Integer(0));
							}
							else {
								discussDTO.setLikernumber(ss.getUserlist().size());
							}
						    
							if(ss.getDiscuss()==null) {//这条评论不是回复
								
							}else {//这条评论是回复
								ReplyDTO replyDTO=new ReplyDTO();
								replyDTO.setName(ss.getDiscuss().getUser().getName());
								replyDTO.setContent(ss.getDiscuss().getContent());
								discussDTO.setReplydto(replyDTO);
							}
							discussdtolist.add(discussDTO);
						}
						//此处已经获取完所有评论信息
						discussResponseDTO.setMsg("success！");
						discussResponseDTO.setSuccess(true);
						discussResponseDTO.setDiscussdtolist(discussdtolist);
					}
					
					
				}
			}else {//用户登录
				if(addsongDTO.getSongid().equals(0L)||addsongDTO.getUserid().equals(0L)) 
				{
					discussResponseDTO.setMsg("系统错误！传输的id为空");
					discussResponseDTO.setSuccess(false);
				}
				else {
					if(userDao.existsById(addsongDTO.getUserid())) {//判断用户是否存在
						//用户存在
						//先判断数据库有无此歌曲信息，无就添加				
						List<Music> musiclist=(List<Music>) musicDao.findAll();
						if((musiclist.size()==0)||(musiclist==null)) {//数据库为空，则添加此歌曲
							Music music=new Music();
							music.setTrueid(addsongDTO.getSongid());
							List<User> userlist=new ArrayList<User>(); 
							List<Discuss> discusslist=new ArrayList<Discuss>();
							List<SongList> songlist=new ArrayList<SongList>();
							music.setCreateTime(new Date());
							music.setUpdateTime(new Date());
							music.setDiscusslist(discusslist);
							music.setSonglist(songlist);
							music.setUserlist(userlist);
							musicDao.save(music);
						}else {//数据库不为空，则查找trueid是否存在
							boolean b=false;//判断是否存在，false为不存在
							for(Music m:musiclist) {
								if(m.getTrueid()!=null)
								if(m.getTrueid().equals(addsongDTO.getSongid())) {
									b=true;
									break;
								}
							}
							if(b==false) {//不存在数据库，创建
								Music music=new Music();
								music.setTrueid(addsongDTO.getSongid());
								List<User> userlist=new ArrayList<User>(); 
								List<Discuss> discusslist=new ArrayList<Discuss>();
								List<SongList> songlist=new ArrayList<SongList>();
								music.setCreateTime(new Date());
								music.setUpdateTime(new Date());
								music.setDiscusslist(discusslist);
								music.setSonglist(songlist);
								music.setUserlist(userlist);
								musicDao.save(music);
							}
							
						}
						//此处已经对给进行判断是否在数据库中，
						//先找到此歌曲在数据库的数据
						List<Music> kk=(List<Music>) musicDao.findAll();
						Music dto=new Music();
						for(Music jj:kk) {//一般此歌已经在库内，所以不判断
							if(jj.getTrueid()!=null)
							if(jj.getTrueid().equals(addsongDTO.getSongid())) {
								dto=jj;
								break;
							}
						}
						//此处已经找到此歌的数据库信息dto
						//因为是非登录 状态，所以
						List<Discuss> discuss=dto.getDiscusslist();
						if(discuss==null||discuss.size()==0) {//如果为空
							discussResponseDTO.setMsg("暂无数据！");
							discussResponseDTO.setSuccess(true);
							discussResponseDTO.setDiscussdtolist(null);
						}
						else {//此歌曲含有评论信息，进行获取
							List<DiscussDTO> discussdtolist=new ArrayList<DiscussDTO>();
							
							for(Discuss ss:discuss) {
								DiscussDTO discussDTO=new DiscussDTO();
								
								discussDTO.setId(ss.getId());
								discussDTO.setName(ss.getUser().getName());
								discussDTO.setContent(ss.getContent());
								if(ss.getUserlist()==null||ss.getUserlist().size()==0) {
									discussDTO.setLikernumber(new Integer(0));
								}
								else {
									discussDTO.setLikernumber(ss.getUserlist().size());
								}
								//判断是否点赞
								List<User> islike=ss.getUserlist();
								if(islike==null||islike.size()==0) //无点赞人
								{
									
								}
								else {//存在点赞的人
									for(User ud:islike) {//查找点赞中是否有
										if(ud.getId().equals(addsongDTO.getUserid())) {//如果相等
											discussDTO.setIslike(true);
											break;
										}
										
									}
								}
								if(ss.getDiscuss()==null) {//这条评论不是回复
									
								}else {//这条评论是回复
									ReplyDTO replyDTO=new ReplyDTO();
									replyDTO.setName(ss.getDiscuss().getUser().getName());
									replyDTO.setContent(ss.getDiscuss().getContent());
									discussDTO.setReplydto(replyDTO);
								}
								discussdtolist.add(discussDTO);
							}
							//此处已经获取完所有评论信息
							discussResponseDTO.setMsg("success！");
							discussResponseDTO.setSuccess(true);
							discussResponseDTO.setDiscussdtolist(discussdtolist);
						}				
					}else {
						discussResponseDTO.setMsg("系统错误！用户不存在！");
						discussResponseDTO.setSuccess(false);
					}
				}
			}
			
			
		}catch(Exception e) {
			discussResponseDTO.setMsg("系统错误！");
			discussResponseDTO.setSuccess(false);
		}
		
		return discussResponseDTO;
	}

	@Override
	public ExtAjaxResponse deletelike(AddsongDTO addsongDTO) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		try {
			if(addsongDTO.getDiscussid().equals(0L)||addsongDTO.getUserid().equals(0L)) {
				extAjaxResponse.setMsg("系统错误：传输数据为空！");
				extAjaxResponse.setSuccess(false);
			}else {
				if(userDao.existsById(addsongDTO.getUserid())&&discussDao.existsById(addsongDTO.getDiscussid()))
				{
					User user=userDao.findById(addsongDTO.getUserid()).get();
					Discuss discuss=discussDao.findById(addsongDTO.getDiscussid()).get();
					List<User> list=discuss.getUserlist();
					if(list==null) {
						List<User> ff=new ArrayList<User>();
						ff.add(user);
						discuss.setUserlist(ff);
						discussDao.save(discuss);
					}
				    List<Discuss> dislist=user.getLikelist();
					if(dislist==null) {
						List<Discuss> jj=new ArrayList<Discuss>();
						jj.add(discuss);
						user.setLikelist(jj);
						userDao.save(user);
					}
					//如果用户或评论的list为空，先设初值
					User user1=userDao.findById(addsongDTO.getUserid()).get();
					Discuss discuss1=discussDao.findById(addsongDTO.getDiscussid()).get();
					user1.getLikelist().add(discuss1);
					discuss1.getUserlist().add(user1);
					userDao.save(user1);
					discussDao.save(discuss1);
					extAjaxResponse.setMsg("success！");
					extAjaxResponse.setSuccess(true);
				}
				else {
					extAjaxResponse.setMsg("系统错误：存在数据不在数据库！");
					extAjaxResponse.setSuccess(false);
				}
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统错误");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}

	@Override
	public ExtAjaxResponse getlike(AddsongDTO addsongDTO) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
	try {
		if(addsongDTO.getDiscussid().equals(0L)||addsongDTO.getUserid().equals(0L)) {
			extAjaxResponse.setMsg("系统错误：传输数据为空！");
			extAjaxResponse.setSuccess(false);
		}else {
			if(userDao.existsById(addsongDTO.getUserid())&&discussDao.existsById(addsongDTO.getDiscussid()))
			{
				//确认
				User user1=userDao.findById(addsongDTO.getUserid()).get();
				Discuss discuss1=discussDao.findById(addsongDTO.getDiscussid()).get();
				user1.getDiscusslist().remove(discuss1);
				
				discuss1.getUserlist().remove(user1);
				userDao.save(user1);
				discussDao.save(discuss1);
				extAjaxResponse.setMsg("success！");
				extAjaxResponse.setSuccess(true);
			}
			else {
				extAjaxResponse.setMsg("系统错误：存在数据不在数据库！");
				extAjaxResponse.setSuccess(false);
			}
		}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统错误");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}

	@Override
	public ExtAjaxResponse adddiscuss(AdddiscussDTO adddiscussDTO) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		try {
			if(adddiscussDTO.isIslogin()==true) {//判断是否登录
				if(adddiscussDTO.getSongid().equals(0L)||adddiscussDTO.getUserid().equals(0L)) {
					extAjaxResponse.setMsg("系统错误！传入id为空");
					extAjaxResponse.setSuccess(false);
				}
				else 
				{		
				if(userDao.existsById(adddiscussDTO.getUserid())) {
					//用户存在，则判断歌曲
					List<Music> musiclist=(List<Music>) musicDao.findAll();
					if(musiclist.size()==0||musiclist==null) {
						Music music=new Music();
						music.setCreateTime(new Date());
						music.setUpdateTime(new Date());
						music.setTrueid(adddiscussDTO.getSongid());
						List<User> userlist=new ArrayList<User>(); 
						List<Discuss> discusslist=new ArrayList<Discuss>();
						List<SongList> songlist=new ArrayList<SongList>();					
						music.setDiscusslist(discusslist);
						music.setSonglist(songlist);
						music.setUserlist(userlist);
						musicDao.save(music);
					}
					else {
						boolean b=false;
						for(Music mm:musiclist) {
							if(mm.getTrueid()!=null)
							if(mm.getTrueid().equals(adddiscussDTO.getSongid())) {
								b=true;
								break;
							}
						}
						if(b==false) {
							Music music=new Music();
							music.setCreateTime(new Date());
							music.setUpdateTime(new Date());
							music.setTrueid(adddiscussDTO.getSongid());
							List<User> userlist=new ArrayList<User>(); 
							List<Discuss> discusslist=new ArrayList<Discuss>();
							List<SongList> songlist=new ArrayList<SongList>();					
							music.setDiscusslist(discusslist);
							music.setSonglist(songlist);
							music.setUserlist(userlist);
							musicDao.save(music);
						}
					}
					
					//完成之后
					//找出此歌曲的数据库信息
					List<Music> dto=(List<Music>) musicDao.findAll();
					Music thi=new Music();
					for(Music mj:dto) {
						if(mj.getTrueid()!=null)
						if(mj.getTrueid().equals(adddiscussDTO.getSongid())) {
							thi=mj;
							break;
						}
					}
					//找到歌曲信息
					//先判断输入的评论内容
					
					if(BeanUtil.isNull(adddiscussDTO.getContent().trim())) {
						extAjaxResponse.setMsg("请您输入内容！");
						extAjaxResponse.setSuccess(false);
						return extAjaxResponse;
					}
					if(adddiscussDTO.getContent().trim().length()>100) {
						extAjaxResponse.setMsg("输入内容超过100字符了！");
						extAjaxResponse.setSuccess(false);
						return extAjaxResponse;
					}
					ExtAjaxResponse ext=BeanUtil.badword(adddiscussDTO.getContent().trim());
					if(!ext.isSuccess()) {
						extAjaxResponse.setMsg("含有敏感词："+ext.getMsg());
						extAjaxResponse.setSuccess(false);
						return extAjaxResponse;
					}
					//检测内容完毕，开始添加
					Discuss discuss=new Discuss();
					discuss.setContent(adddiscussDTO.getContent());
					discuss.setCreateTime(new Date());
					discuss.setMusic(thi);
					thi.getDiscusslist().add(discuss);
					musicDao.save(thi);
					User user=userDao.findById(adddiscussDTO.getUserid()).get();
					if(!user.isIswrite()) {
						extAjaxResponse.setMsg("您没有发表评论权限！");
						extAjaxResponse.setSuccess(false);
						return extAjaxResponse;
					}
					discuss.setUser(user);
					user.getDiscusslist().add(discuss);
					userDao.save(user);
					discussDao.save(discuss);
					extAjaxResponse.setMsg("发表成功！");
					extAjaxResponse.setSuccess(true);
					
				}else {
					extAjaxResponse.setMsg("系统错误！此用户没注册！");
					extAjaxResponse.setSuccess(false);
				}
			    }
			}else {
				extAjaxResponse.setMsg("请您登录！");
				extAjaxResponse.setSuccess(false);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			extAjaxResponse.setMsg("系统错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}

	@Override
	public DiscussResponseDTO lookmydiscuss(AdddiscussDTO adddiscussDTO) {
		DiscussResponseDTO discussResponseDTO=new DiscussResponseDTO();
		try {
			if(adddiscussDTO.equals(0L)) {
				discussResponseDTO.setMsg("系统错误！传入id为空！");
				discussResponseDTO.setSuccess(false);
			}else {
				if(userDao.existsById(adddiscussDTO.getUserid())) {
					User user=userDao.findById(adddiscussDTO.getUserid()).get();
					List<Discuss> list=user.getDiscusslist();
					if(list==null||list.size()==0) {
						discussResponseDTO.setMsg("您还没有任何评论！");
						discussResponseDTO.setSuccess(false);
					}
					else {
						List<DiscussDTO> discussdtolist=new ArrayList<DiscussDTO>();
						for(Discuss dis:list) {
							DiscussDTO discussDTO=new DiscussDTO();
							discussDTO.setId(dis.getId());
							discussDTO.setContent(dis.getContent());
							if(dis.getUserlist()==null) {
								discussDTO.setLikernumber(new Integer(0));
							}
							else {
								discussDTO.setLikernumber(dis.getUserlist().size());
							}
							discussDTO.setTime(dis.getCreateTime().getTime());
							//discussDTO.setMusicname(dis.getMusic().get);
							discussdtolist.add(discussDTO);
						}
						discussResponseDTO.setDiscussdtolist(discussdtolist);
						discussResponseDTO.setMsg("sucess");
						discussResponseDTO.setSuccess(true);
					}
				}else {
					discussResponseDTO.setMsg("系统错误！用户没有注册");
					discussResponseDTO.setSuccess(false);
				}
			}
		}catch(Exception e) {
			discussResponseDTO.setMsg("系统错误！");
			discussResponseDTO.setSuccess(false);
		}
		return discussResponseDTO;
	}

	@Override
	public DiscussResponseDTO deletemydiscuss(AdddiscussDTO adddiscussDTO) {
		DiscussResponseDTO discussResponseDTO=new DiscussResponseDTO();
		try {
			if(adddiscussDTO.getUserid().equals(0L)||adddiscussDTO.getDiscussid().equals(0L)) {
				discussResponseDTO.setMsg("系统错误！传入数据为空");
				discussResponseDTO.setSuccess(false);
			}else {
				if(userDao.existsById(adddiscussDTO.getUserid())&&discussDao.existsById(adddiscussDTO.getDiscussid()))
				{
					User user=userDao.findById(adddiscussDTO.getUserid()).get();
					Discuss discuss=discussDao.findById(adddiscussDTO.getDiscussid()).get();
				
					//清除评论的附带
					
					user.getDiscusslist().remove(discuss);
				    Music music=discuss.getMusic();
				    music.getDiscusslist().remove(discuss);
				    discuss.setUser(null);
				    discuss.setDiscuss(null);
				    List<User> list=discuss.getUserlist();//清除点赞人
				    if(list==null||list.size()==0) {
				    	discuss.setUserlist(null);
				    }else {
				    	for(User uu:list) {
				    		uu.getLikelist().remove(discuss);
				    		userDao.save(uu);
				    	}
				    	discuss.setUserlist(null);
				    }
				    userDao.save(user);
				    discussDao.save(discuss);
				    musicDao.save(music);
				    discussDao.deleteById(adddiscussDTO.getDiscussid());
				    discussResponseDTO.setMsg("删除成功！");
					discussResponseDTO.setSuccess(true);
					
					
				}else {
					discussResponseDTO.setMsg("系统错误！用户或评论查找不到！");
					discussResponseDTO.setSuccess(false);
				}
			}
		}catch(Exception e) {
			discussResponseDTO.setMsg("系统错误！");
			discussResponseDTO.setSuccess(false);
		}
		return discussResponseDTO;
	}

	@Override//回复评论 是否登录、内容、回复评论的id、用户id 
	public ExtAjaxResponse replyother(AdddiscussDTO adddiscussDTO) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		try {
			if(adddiscussDTO.isIslogin()) {
				if(adddiscussDTO.getDiscussid().equals(0L)||adddiscussDTO.getUserid().equals(0L))
				{
					extAjaxResponse.setMsg("系统错误！传入数据为空");
					extAjaxResponse.setSuccess(false);
				}
				else {
					if(discussDao.existsById(adddiscussDTO.getDiscussid())&&userDao.existsById(adddiscussDTO.getUserid()))
					{
						
						User user=userDao.findById(adddiscussDTO.getUserid()).get();
						if(user.isIswrite()) {
							//开始判断内容
							if(BeanUtil.isNull(adddiscussDTO.getContent())) {
								extAjaxResponse.setMsg("请输入内容！");
								extAjaxResponse.setSuccess(false);
								return extAjaxResponse;
							}
							ExtAjaxResponse ext=BeanUtil.badword(adddiscussDTO.getContent());
						    if(!ext.isSuccess()) {
						    	extAjaxResponse.setMsg("含有敏感词："+ext.getMsg());
								extAjaxResponse.setSuccess(false);
								return extAjaxResponse;
						    }						    
						    //内容检测完毕
						    Discuss discuss=new Discuss();
						    Discuss theboss=discussDao.findById(adddiscussDTO.getDiscussid()).get();
						    Music music=theboss.getMusic();
						    discuss.setContent(adddiscussDTO.getContent());
						    discuss.setCreateTime(new Date());
						    discuss.setUpdateTime(new Date());
						    discuss.setUser(user);
						    user.getDiscusslist().add(discuss);
						    discuss.setMusic(music);
						    music.getDiscusslist().add(discuss);
						    discuss.setDiscuss(theboss);
						    userDao.save(user);
						    musicDao.save(music);
						    discussDao.save(discuss);
						    extAjaxResponse.setMsg("回复成功！");
							extAjaxResponse.setSuccess(true);						    
						}
						else {
							extAjaxResponse.setMsg("您没有发表评论的权限！");
							extAjaxResponse.setSuccess(false);
							
						}				
					}
					else {
						extAjaxResponse.setMsg("系统错误！用户或评论没有存在数据库！");
						extAjaxResponse.setSuccess(false);
					}
				}
			}else {
				extAjaxResponse.setMsg("请您登录！");
				extAjaxResponse.setSuccess(false);
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}

	@Override
	public LoginRspDTO phonelogin(UserloginDTO userloginDTO) {
		LoginRspDTO loginRspDTO=new LoginRspDTO();
		try {
			if(BeanUtil.isNull(userloginDTO.getMessage())||BeanUtil.isNull(userloginDTO.getPassword()))
			{
				loginRspDTO.setMsg("输入的账号或密码为空！");
				loginRspDTO.setSuccess(false);
			}else{
				User user1=userDao.findByname(userloginDTO.getMessage(), userloginDTO.getPassword());
				if(user1!=null) {
					loginRspDTO.setUserid(user1.getId());
					loginRspDTO.setName(user1.getName());
					loginRspDTO.setMsg("登录成功！");
					loginRspDTO.setSuccess(true);
					return loginRspDTO;
				}else {
					User user2=userDao.findByphone(userloginDTO.getMessage(), userloginDTO.getPassword());
				    if(user2!=null) {
				    	loginRspDTO.setUserid(user1.getId());
						loginRspDTO.setName(user1.getName());
						loginRspDTO.setMsg("登录成功！");
						loginRspDTO.setSuccess(true);
						return loginRspDTO;
				    }else {
				    	User user3=userDao.findByemail(userloginDTO.getMessage(), userloginDTO.getPassword());
				        if(user3!=null) {
				        	loginRspDTO.setUserid(user1.getId());
							loginRspDTO.setName(user1.getName());
							loginRspDTO.setMsg("登录成功！");
							loginRspDTO.setSuccess(true);
							return loginRspDTO;
				        }else {
				        	loginRspDTO.setMsg("账号或密码错误！");
							loginRspDTO.setSuccess(false);
							return loginRspDTO;
				        }
				    }
				}
			}
		}catch(Exception e) {
			loginRspDTO.setMsg("系统错误！");
			loginRspDTO.setSuccess(false);
		}
		
		return loginRspDTO;
	}

	@Override
	public ExtAjaxResponse deletemysong(AddsongDTO adddiscussDTO) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		try {
			if(adddiscussDTO.getSongid().equals(0L)||adddiscussDTO.getUserid().equals(0L)) {
				extAjaxResponse.setMsg("系统错误！传入数据为空！");
				extAjaxResponse.setSuccess(false);
			}else {
				if(adddiscussDTO.getSonglistid().equals(0L)) {//删除我喜欢歌单的歌曲
					if(userDao.existsById(adddiscussDTO.getUserid())) {
						User user=userDao.findById(adddiscussDTO.getUserid()).get();
						List<Music> list=(List<Music>) musicDao.findAll();
						Music music=new Music();
						if(list==null||list.size()==0) {
							extAjaxResponse.setMsg("系统错误！数据库无音乐信息！");
							extAjaxResponse.setSuccess(false);
							return extAjaxResponse;
						}else {
							for(Music mm:list) {
								if(mm.getTrueid().equals(adddiscussDTO.getSongid())) {
									music=mm;
									break;
								}
							}
						}
						user.getMusiclist().remove(music);
						userDao.save(user);
						extAjaxResponse.setMsg("删除成功");
						extAjaxResponse.setSuccess(true);
					}else {
						extAjaxResponse.setMsg("系统错误！此用户没有注册！");
						extAjaxResponse.setSuccess(false);
					}
				}else {//删除自己创建的歌单歌曲
					if(songListDao.existsById(adddiscussDTO.getSonglistid())) {
						SongList songlist=songListDao.findById(adddiscussDTO.getSonglistid()).get();
						List<Music> list=songlist.getMusiclist();
						Music music=new Music();
						if(list==null||list.size()==0) {
							extAjaxResponse.setMsg("系统错误！此歌单无歌曲信息！");
							extAjaxResponse.setSuccess(false);
							return extAjaxResponse;
						}else {
							for(Music mm:list) {
								if(mm.getTrueid().equals(adddiscussDTO.getSongid())) {
									music=mm;
									break;
								}
							}
						}
						songlist.getMusiclist().remove(music);
						music.getSonglist().remove(songlist);
						musicDao.save(music);
						songListDao.save(songlist);
						extAjaxResponse.setMsg("删除成功");
						extAjaxResponse.setSuccess(true);
					}else {
						extAjaxResponse.setMsg("系统错误！此歌单不存在");
						extAjaxResponse.setSuccess(false);
					}
					
				}
			
				
					
				
			
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}

	@Override
	public ExtAjaxResponse checkinmylike(AddsongDTO addsongDTO) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		try {
			if(addsongDTO.getSongid().equals(0L)||addsongDTO.getUserid().equals(0L)) {
				extAjaxResponse.setMsg("系统错误！传入数据为空");
				extAjaxResponse.setSuccess(false);
			}else {
				if(userDao.existsById(addsongDTO.getUserid())) {
					User user=userDao.findById(addsongDTO.getUserid()).get();
					List<Music> list=user.getMusiclist();
					Music music=new Music();
					if(list==null||list.size()==0) {
						
					}else {
						for(Music mm:list) {
							if(mm.getTrueid().equals(addsongDTO.getSongid())) {
								extAjaxResponse.setMsg("success");
								extAjaxResponse.setIsmylike(true);
								extAjaxResponse.setSuccess(true);
								return extAjaxResponse;
							}
						}
					
					}
					extAjaxResponse.setMsg("success");
					extAjaxResponse.setIsmylike(false);
					extAjaxResponse.setSuccess(true);
					
				}else {
					extAjaxResponse.setMsg("系统错误！此用户没有注册");
					extAjaxResponse.setSuccess(false);
				}
			}
		}catch(Exception e) {
			extAjaxResponse.setMsg("系统错误！");
			extAjaxResponse.setSuccess(false);
		}
		return extAjaxResponse;
	}


}
