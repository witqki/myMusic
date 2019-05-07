package com.example.myMusic.user.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.myMusic.common.dto.CheckcCodeDTO;
import com.example.myMusic.common.dto.UserloginDTO;
import com.example.myMusic.common.dto.UserregisteredDTO;
import com.example.myMusic.common.dto.discuss.AdddiscussDTO;
import com.example.myMusic.common.dto.discuss.DiscussResponseDTO;
import com.example.myMusic.common.dto.music.SearchResponseDTO;
import com.example.myMusic.common.dto.singet.SingerDTO;
import com.example.myMusic.common.dto.singet.SingerRspDTO;
import com.example.myMusic.common.dto.songList.SongListRspDTO;
import com.example.myMusic.common.dto.songList.UserSongListDTO;
import com.example.myMusic.common.dto.user.AddsongDTO;
import com.example.myMusic.common.dto.user.LoginRspDTO;
import com.example.myMusic.common.dto.user.UserresetDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.user.dao.UserDao;
import com.example.myMusic.user.entities.User;
import com.example.myMusic.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService=null;
	@Autowired
	private UserDao userDao=null;
	 @GetMapping(value="/test")
	 public String hello() {
		 return "success";
	 }
	 @PostMapping(value="/search",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public ExtAjaxResponse searchcheck(@RequestBody ExtAjaxResponse extAjaxResponse) {
		 return  userService.searchcheck(extAjaxResponse);
	 }
	 @PostMapping(value="/savemymessage",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public ExtAjaxResponse savemymessage(@RequestBody LoginRspDTO LoginRspDTO)
	 {
		 ExtAjaxResponse ExtAjaxResponse=new ExtAjaxResponse();
		 if(LoginRspDTO.isSuccess()) {
				return userService.savemymessage(LoginRspDTO);
		 }
		 else {
			 ExtAjaxResponse.setMsg("请您登录");
			 ExtAjaxResponse.setSuccess(false);
			 return ExtAjaxResponse;
		 }
		 
	 }
	 @PostMapping(value="/getmymessage/{id}")
	 public LoginRspDTO getmymessage(@PathVariable("id") Long id)
	 {
		return userService.getmymessage(id);
		 
	 }
	//用户登录
	 @PostMapping(value="/login",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public LoginRspDTO userlogin(@RequestBody UserloginDTO userlogindto) {
		// RestfulResponse<Map<String, Object>> response = new RestfulResponse<>(false, "用户名或密码错误, 登陆失败!");
		
		
		 return userService.userlogin(userlogindto);
	 }
	 
	 @PostMapping(value="/register",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public ExtAjaxResponse userregistered(@RequestBody UserregisteredDTO userregisteredDTO) {
		 return userService.userregistered(userregisteredDTO);
	 }
	 //重置密码
	 @PostMapping(value="/resetpsw",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public ExtAjaxResponse userreset(@RequestBody UserresetDTO userresetDTO) {	
		 return userService.userreset(userresetDTO);
	 }
	 //发送验证码
	 @PostMapping(value="/sendcode",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public ExtAjaxResponse sendcheckcode(@RequestBody CheckcCodeDTO checkcCodeDTO) {	
		 return userService.sendcheckcode(checkcCodeDTO);
	 }
	 
	 //获取我自己创建的歌单的歌曲list
	 @PostMapping(value="/mySongList",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public SearchResponseDTO mySongList(@RequestBody UserSongListDTO userSongListDTO) {
		 return userService.mySongList(userSongListDTO);//返回歌曲的网易云 id
	 }
	 //用户创建新歌单
	 @PostMapping(value="/createSongList",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public ExtAjaxResponse createSongList(@RequestBody UserSongListDTO userSongListDTO) {
	
		 return userService.createSongList(userSongListDTO);
	 }
	 
	 //用户删除新歌单
	 @PostMapping(value="/deleteSongList",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public ExtAjaxResponse deleteSongList(@RequestBody UserSongListDTO userSongListDTO) {
		
		 return userService.deleteSongList(userSongListDTO);
	 }
	//用户修改新歌单
	 @PostMapping(value="/updateSongList",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public ExtAjaxResponse updateSongList(@RequestBody UserSongListDTO userSongListDTO) {
		 return userService.updateSongList(userSongListDTO);
	 }
	//用户查看自己创建的歌单
	 @PostMapping(value="/lookSongList/{id}")
	 public SongListRspDTO lookSongList(@PathVariable("id") Long id) {
		 return userService.lookSongList(id);
	 }
	//用户收藏歌单
		 @PostMapping(value="/likeSongList",consumes=MediaType.APPLICATION_JSON_VALUE)
		 public ExtAjaxResponse likeSongList(@RequestBody UserSongListDTO userSongListDTO) {
			 
			 return userService.likeSongList(userSongListDTO);
		 }

	//用户查看收藏歌单
		 @PostMapping(value="/check_likeSongList/{id}")
		 public SongListRspDTO check_likeSongList(@PathVariable("id") Long id) {
			 return userService.check_likeSongList(id);
		 }
		 //用户查看自己收藏歌单的歌曲
		 @PostMapping(value="/detail_likeSongList/{id}")
		 public SongListRspDTO detail_likeSongList(@PathVariable("id") Long id) {
			 return userService.detail_likeSongList(id);
		 }
			//用户删除收藏歌单
		 @PostMapping(value="/delete_likeSongList",consumes=MediaType.APPLICATION_JSON_VALUE)
		 public ExtAjaxResponse delete_likeSongList(@RequestBody UserSongListDTO userSongListDTO) {
			 return userService.delete_likeSongList(userSongListDTO);
		 }
		 //把别人的歌单添加到自己的歌单
		 @PostMapping(value="/add_likeSongList",consumes=MediaType.APPLICATION_JSON_VALUE)
		 public ExtAjaxResponse add_likeSongList(@RequestBody UserSongListDTO userSongListDTO) {
			 return userService.add_likeSongList(userSongListDTO);
		 }
	
		 //把歌曲添加到自己的创建歌单
		 @PostMapping(value="/addsongtomy",consumes=MediaType.APPLICATION_JSON_VALUE)
		 public ExtAjaxResponse addsongtomy(@RequestBody AddsongDTO AddsongDTO) {
//			 ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
//			 extAjaxResponse.setMsg("");
//			 extAjaxResponse.set
			 return userService.addsongtomy(AddsongDTO);
		 }
		 //获取此歌曲评论
		 @PostMapping(value="/getdiscuss",consumes=MediaType.APPLICATION_JSON_VALUE)
		 public DiscussResponseDTO getdiscuss(@RequestBody AddsongDTO AddsongDTO) {
//			 ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
//			 extAjaxResponse.setMsg("");
//			 extAjaxResponse.set
			 return userService.getdiscuss(AddsongDTO);
		 }
		 //点赞评论  ;点赞用户的id，评论的id
		 @PostMapping(value="/getlike",consumes=MediaType.APPLICATION_JSON_VALUE)
		 public ExtAjaxResponse getlike(@RequestBody AddsongDTO AddsongDTO) {
//			 ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
//			 extAjaxResponse.setMsg("");
//			 extAjaxResponse.set
			 return userService.getlike(AddsongDTO);
		 }
		 //取消点赞的用户的id，评论的id
		 @PostMapping(value="/deletelike",consumes=MediaType.APPLICATION_JSON_VALUE)
		 public ExtAjaxResponse deletelike(@RequestBody AddsongDTO AddsongDTO) {
//			 ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
//			 extAjaxResponse.setMsg("");
//			 extAjaxResponse.set
			 return userService.deletelike(AddsongDTO);
		 }
		 //发表评论
		 @PostMapping(value="/adddiscuss",consumes=MediaType.APPLICATION_JSON_VALUE)
		 public ExtAjaxResponse adddiscuss(@RequestBody AdddiscussDTO adddiscussDTO) {
//			 ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
//			 extAjaxResponse.setMsg("");
//			 extAjaxResponse.set
			 return userService.adddiscuss(adddiscussDTO);
		 }
		 //查看用户自己的评论 用户id
		 @PostMapping(value="/lookmydiscuss",consumes=MediaType.APPLICATION_JSON_VALUE)
		 public DiscussResponseDTO lookmydiscuss(@RequestBody AdddiscussDTO adddiscussDTO) {
//			 ExtAjaxResponse extAjaxResponse =new ExtAjaxResponse();
//			 extAjaxResponse.setMsg("");
//			 extAjaxResponse.set
			 return userService.lookmydiscuss(adddiscussDTO);
		 }
		 //删除评论 用户id 评论id
		 @PostMapping(value="/deletemydiscuss",consumes=MediaType.APPLICATION_JSON_VALUE)
		 public DiscussResponseDTO deletemydiscuss(@RequestBody AdddiscussDTO adddiscussDTO) {

			 return userService.deletemydiscuss(adddiscussDTO);
		 }
		 //回复评论 是否登录、内容、回复评论的id、用户id 
		 @PostMapping(value="/replyother",consumes=MediaType.APPLICATION_JSON_VALUE)
		 public ExtAjaxResponse replyother(@RequestBody AdddiscussDTO adddiscussDTO) {

			 return userService.replyother(adddiscussDTO);
		 }
		 
		 
		 
}
