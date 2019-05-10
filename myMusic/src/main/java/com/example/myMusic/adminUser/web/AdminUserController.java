package com.example.myMusic.adminUser.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.myMusic.adminUser.service.AdminUserService;
import com.example.myMusic.common.dto.adminUser.CheckDTO;
import com.example.myMusic.common.dto.adminUser.ComUserDTO;
import com.example.myMusic.common.dto.adminUser.PathDTO;
import com.example.myMusic.common.dto.adminUser.SuperDTO;
import com.example.myMusic.common.dto.adminUser.UserRspDTO;
import com.example.myMusic.common.dto.discuss.DiscussDTO;
import com.example.myMusic.common.dto.discuss.DiscussResponseDTO;
import com.example.myMusic.common.dto.discuss.ThisDTO;
import com.example.myMusic.common.dto.music.SearchMusicDTO;
import com.example.myMusic.common.dto.music.SearchResponseDTO;
import com.example.myMusic.common.dto.songList.SongListDTO;
import com.example.myMusic.common.dto.songList.SongListRspDTO;
import com.example.myMusic.common.dto.user.LoginRspDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.music.entities.Music;
@RestController
@RequestMapping("/adminuser")
public class AdminUserController {
	@Autowired
	private AdminUserService adminUserService=null;
	
	@PostMapping(value="/superlogin",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse superlogin(@RequestBody SuperDTO superDTO) {
//		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
//		extAjaxResponse.setMsg(superDTO.getUsername());
//		extAjaxResponse.setSuccess(false);
		return adminUserService.superlogin(superDTO);
	}
	
	/*
	 * 歌曲管理
	 */
	@GetMapping("/test")
	public String show(HttpServletRequest request){
	HttpSession sessoin=request.getSession();//这就是session的创建
	sessoin.setAttribute("username","TOM");
	
	sessoin.setAttribute("password","tommmm");
	
	System.out.println(sessoin.getId());
	return (String) sessoin.getAttribute("username");
	
	}
	@GetMapping("/demo")
	public void demo(HttpServletRequest request){
	HttpSession sessoin=request.getSession();//这就是session的创建
	sessoin.setAttribute("username","TOM");
	//session.setAttribute("username","TOM");//给session添加属性属性name： username,属性 value：TOM
	sessoin.setAttribute("password","tommmm");
	//session.setAttribute("password","tommmm");//添加属性 name: password; value: tommmm
	System.out.println(sessoin.getId());
	}

//	//导入歌曲
//	@PostMapping(value="/importsong",consumes=MediaType.APPLICATION_JSON_VALUE)
//	public ExtAjaxResponse importsong(@RequestBody PathDTO pathDTO) {
//	
//		return adminUserService.importsong(pathDTO.getPath());
//	}
	//查看歌曲
	@PostMapping(value="/looksong")
	public SearchResponseDTO looksong() {
	
		return adminUserService.looksong();
	}
	//删除歌曲
	@PostMapping(value="/deletesong/{id}")
	public ExtAjaxResponse deletesong(@PathVariable("id") Long id) {
//		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
//		extAjaxResponse.setMsg(""+id);
//		extAjaxResponse.setSuccess(false);
		return adminUserService.deletesong(id);
	}
	//修改歌曲信息
//	@PostMapping(value="/changesong",consumes=MediaType.APPLICATION_JSON_VALUE)
//	public ExtAjaxResponse changesong(@RequestBody SearchMusicDTO searchMusicDTO) {
//	
//		return adminUserService.changesong(searchMusicDTO);
//	}
	/*
	 * 歌单管理
	 */
	//获取单个歌单信息
	@PostMapping(value="/getsongList/{id}")
	public SongListRspDTO getsongList(@PathVariable("id") Long id) {
	
		return adminUserService.getsongList(id);
	}
	//查看所有歌单信息
	@PostMapping(value="/looksongList")
	public SongListRspDTO looksongList() {
	
		return adminUserService.looksongList();
	}
	//删除歌单信息
		@PostMapping(value="/deletesongList/{id}")
		public ExtAjaxResponse deletesongList(@PathVariable("id") Long id) {
		
			return adminUserService.deletesongList(id);
		}
	//修改歌单信息	
		@PostMapping(value="/changesongList",consumes=MediaType.APPLICATION_JSON_VALUE)
		public ExtAjaxResponse changesongList(@RequestBody SongListDTO songListDTO) {
		
			return adminUserService.changesongList(songListDTO);
		}
		//添加歌单信息	
		@PostMapping(value="/addsongList",consumes=MediaType.APPLICATION_JSON_VALUE)
		public ExtAjaxResponse addsongList(@RequestBody SongListDTO songListDTO) {
		
			return adminUserService.addsongList(songListDTO);
		}
		//获取申请公开歌单列表
		@PostMapping(value="/getApplesongList")
		public SongListRspDTO getApplesongList() {
		
			return adminUserService.getApplesongList();
		}
		//允许该歌单的公开申请
		@PostMapping(value="/agreeApplesongList/{id}")
		public ExtAjaxResponse agreeApplesongList(@PathVariable("id") Long id) {
		
			return adminUserService.agreeApplesongList(id);
		}
		//拒绝该歌单的公开申请
		@PostMapping(value="/refuseApplesongList/{id}")
		public ExtAjaxResponse refuseApplesongList(@PathVariable("id") Long id) {
		
			return adminUserService.refuseApplesongList(id);
		}
	/*
	 * 用户管理
	 */
	//权限修改
	@PostMapping(value="/change_authority",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse change_authority(@RequestBody ComUserDTO comUserDTO) {
//		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
//		extAjaxResponse.setSuccess(false);
//		extAjaxResponse.setMsg(""+comUserDTO.isWrite());
		return adminUserService.change_authority(comUserDTO);
	}
	//获取用户信息列表，如果是超级管理员，则返回所有用户信息，如果是普通管理员，返回用户信息
	@PostMapping(value="/findUser",consumes=MediaType.APPLICATION_JSON_VALUE)
	public UserRspDTO findUser(@RequestBody LoginRspDTO loginRspDTO) {
	
		return adminUserService.findUser(loginRspDTO);
	}
	//删除用户-id=1
		@PostMapping(value="/deleteUser/{id}")
		public ExtAjaxResponse deleteUser(@PathVariable("id") Long id) {
			return adminUserService.deleteUser(id);
		}
	/*
	 * 评论管理
	 *///查看用户评论
		@PostMapping(value="/checkDiscuss",consumes=MediaType.APPLICATION_JSON_VALUE)
		public DiscussResponseDTO checkDiscuss(@RequestBody ExtAjaxResponse extAjaxResponse) {
			DiscussResponseDTO discussResponseDTO=new DiscussResponseDTO();
		
			
			return adminUserService.checkDiscuss(extAjaxResponse);
		}
		//删除用户评论
		@PostMapping(value="/deleteuserDiscuss",consumes=MediaType.APPLICATION_JSON_VALUE)
		public ExtAjaxResponse deleteuserDiscuss(@RequestBody ExtAjaxResponse extAjaxResponse) {
			ExtAjaxResponse ext=new ExtAjaxResponse();
			ext.setMsg(""+extAjaxResponse.getDiscussid()+extAjaxResponse.isSuccess());
			ext.setSuccess(extAjaxResponse.isSuccess());
			
			return adminUserService.deleteuserDiscuss(extAjaxResponse);
		}
		
		
}
