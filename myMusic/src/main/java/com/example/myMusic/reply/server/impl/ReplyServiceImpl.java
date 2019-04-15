package com.example.myMusic.reply.server.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myMusic.common.dto.discuss.DiscussDTO;
import com.example.myMusic.common.dto.reply.ReplyDTO;
import com.example.myMusic.common.dto.reply.ReplyPageDTO;
import com.example.myMusic.common.dto.reply.ReplyResponseDTO;
import com.example.myMusic.common.dto.reply.ReplySaveDTO;
import com.example.myMusic.common.util.BeanUtil;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.discuss.dao.DiscussDao;
import com.example.myMusic.discuss.entities.Discuss;
import com.example.myMusic.music.dao.MusicDao;
import com.example.myMusic.reply.dao.ReplyDao;
import com.example.myMusic.reply.entities.Reply;
import com.example.myMusic.reply.server.ReplyService;
import com.example.myMusic.user.dao.UserDao;
import com.example.myMusic.user.entities.User;
@Service
@Transactional
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyDao replyDao;
   
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DiscussDao discussDao;
	@Override
	public ExtAjaxResponse save(ReplySaveDTO replySaveDTO) {
		// TODO Auto-generated method stub
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(replySaveDTO.getUser_id().equals(0L)) {
			extAjaxResponse.setMsg("传输的用户id为空。");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		if(replySaveDTO.getDiscuss_id().equals(0L)) {
			extAjaxResponse.setMsg("传输的评论id为空。");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		if(StringUtils.isBlank(replySaveDTO.getContent().trim())) {
			extAjaxResponse.setMsg("输入点内容再提交吧！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		Reply reply=new Reply();
		if(userDao.existsById(replySaveDTO.getUser_id())) {
			User user=userDao.findById(replySaveDTO.getUser_id()).get();
			reply.setUser(user);
			user.getReplylist().add(reply);
			userDao.save(user);
		}else {
			extAjaxResponse.setMsg("该用户id不存在！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		if(discussDao.existsById(replySaveDTO.getDiscuss_id())) {
			Discuss discuss=discussDao.findById(replySaveDTO.getDiscuss_id()).get();
			discuss.getReplylist().add(reply);
			reply.setDiscuss(discuss);
			discussDao.save(discuss);
		}else {
			extAjaxResponse.setMsg("该评论id不存在！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		
		reply.setContent(replySaveDTO.getContent());
		reply.setLikernumber(new Integer(0));
		replyDao.save(reply);
		extAjaxResponse.setMsg("回复成功！");
		extAjaxResponse.setSuccess(true);
		return extAjaxResponse;
	}
	@Override
	public ExtAjaxResponse delete(Long id) {
		// TODO Auto-generated method stub
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(id.equals(0L)) {
			extAjaxResponse.setMsg("传输的回复id为空！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		if(replyDao.existsById(id)) {
			Reply reply=replyDao.findById(id).get();
			User user=reply.getUser();
			Discuss discuss=reply.getDiscuss();
			if(user!=null) {
				reply.setUser(null);
				user.getReplylist().remove(reply);
				userDao.save(user);
			}
			if(discuss!=null) {
				reply.setDiscuss(null);
				discuss.getReplylist().remove(reply);
				discussDao.save(discuss);
			}
			replyDao.save(reply);
			replyDao.deleteById(id);
			
			extAjaxResponse.setMsg("删除成功");
			extAjaxResponse.setSuccess(true);
			return extAjaxResponse ;
		}else {
			extAjaxResponse.setMsg("该回复id找不到！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		
	}
	@Override
	public ExtAjaxResponse addlike(Long id) {
		// TODO Auto-generated method stub
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(replyDao.existsById(id)) {
			Reply reply=replyDao.findById(id).get();
			if(reply.getLikernumber()==null) {
				reply.setLikernumber(new Integer(0));
			}
			reply.setLikernumber(new Integer(reply.getLikernumber().intValue()+1));
			replyDao.save(reply);
			extAjaxResponse.setSuccess(true);
			extAjaxResponse.setMsg("点赞成功！");
			return extAjaxResponse;
		}else {
			extAjaxResponse.setSuccess(false);
			extAjaxResponse.setMsg("点赞失败！");
			return extAjaxResponse;
		}
		
	}
	@Override
	public ExtAjaxResponse deletelike(Long id) {
		// TODO Auto-generated method stub
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(replyDao.existsById(id)) {
			Reply reply=replyDao.findById(id).get();
			if(reply.getLikernumber()==null) {
				reply.setLikernumber(new Integer(0));
			}
			reply.setLikernumber(new Integer(reply.getLikernumber().intValue()-1));
			replyDao.save(reply);
			extAjaxResponse.setSuccess(true);
			extAjaxResponse.setMsg("取消点赞成功！");
			return extAjaxResponse;
		}else {
			extAjaxResponse.setSuccess(false);
			extAjaxResponse.setMsg("取消点赞失败！");
			return extAjaxResponse;
		}
	}
	@Override
	public ReplyResponseDTO getReplypage(ReplyPageDTO replyPageDTO) {
		// TODO Auto-generated method stub
		ReplyResponseDTO replyResponseDTO=new ReplyResponseDTO();
		if(replyPageDTO.getDiscuss_id().equals(0L)) {
			replyResponseDTO.setSuccess(false);
			replyResponseDTO.setMsg("传入的评论id为空");
			return replyResponseDTO;
		}
		if(discussDao.existsById(replyPageDTO.getDiscuss_id())) {
			Discuss discuss=discussDao.findById(replyPageDTO.getDiscuss_id()).get();
			List<Reply> replylist=discuss.getReplylist();
			if(replylist.size()==0) {
				replyResponseDTO.setSuccess(false);
				replyResponseDTO.setMsg("没有数据！");
				return replyResponseDTO;
			}
			List<Reply> pagelist=BeanUtil.fenye(replylist,replyPageDTO.getPage());
			List<ReplyDTO> replydtolist=new ArrayList<ReplyDTO>();
			for(Reply reply:pagelist) {
				ReplyDTO replyDTO=new ReplyDTO();
				BeanUtil.replyToreplyDTO(reply, replyDTO);
				replydtolist.add(replyDTO);
			}
			
			replyResponseDTO.setSuccess(true);
			replyResponseDTO.setDiscussdtolist(replydtolist);;
			replyResponseDTO.setNownumber(pagelist.size());
			replyResponseDTO.setNowpage(BeanUtil.nowpage(replydtolist,replyPageDTO.getPage()));
			replyResponseDTO.setTotalnumber(replylist.size());
			replyResponseDTO.setTotalpage(BeanUtil.pageTotal(replydtolist));
				
			if(replyResponseDTO.getNowpage()==replyResponseDTO.getTotalpage())
				replyResponseDTO.setMsg("已经到底了！");
			return replyResponseDTO;
		}else {
			replyResponseDTO.setSuccess(false);
			replyResponseDTO.setMsg("此评论id不存在");
			return replyResponseDTO;
		}
		
	}
	
	
	
	
}
