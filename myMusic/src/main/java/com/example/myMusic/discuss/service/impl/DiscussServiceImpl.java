package com.example.myMusic.discuss.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myMusic.common.dto.discuss.DiscussDTO;
import com.example.myMusic.common.dto.discuss.DiscussPageDTO;
import com.example.myMusic.common.dto.discuss.DiscussRequestDTO;
import com.example.myMusic.common.dto.discuss.DiscussResponseDTO;
import com.example.myMusic.common.util.BeanUtil;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.discuss.dao.DiscussDao;
import com.example.myMusic.discuss.entities.Discuss;
import com.example.myMusic.discuss.service.DiscussService;
import com.example.myMusic.music.dao.MusicDao;
import com.example.myMusic.music.entities.Music;
import com.example.myMusic.reply.dao.ReplyDao;
import com.example.myMusic.reply.entities.Reply;
import com.example.myMusic.user.dao.UserDao;
import com.example.myMusic.user.entities.User;
@Service
@Transactional
public class DiscussServiceImpl implements DiscussService{
	private int pageSize=3;
    @Autowired
    private DiscussDao discussDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MusicDao musicDao;
    @Autowired
    private ReplyDao replyDao;
    
	@Override
	public ExtAjaxResponse save(DiscussRequestDTO discussRequestDTO) {
		// TODO Auto-generated method stub
	  
		
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(discussRequestDTO.getUser_id().equals(0L))
		{
			extAjaxResponse.setMsg("传入的用户id为空");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		if(discussRequestDTO.getMusic_id().equals(0L))
		{
			extAjaxResponse.setMsg("传入的音乐id为空");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		if(StringUtils.isBlank(discussRequestDTO.getContent().trim()))	
		{
			extAjaxResponse.setMsg("输入点内容再提交吧！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		Discuss discuss=new Discuss();
		User user=userDao.findById(discussRequestDTO.getUser_id()).get();
		Music music=musicDao.findById(discussRequestDTO.getMusic_id()).get();
		if(user!=null) {
			discuss.setUser(user);
			user.getDiscussList().add(discuss);
			
			userDao.save(user);
		}
		if(music!=null) {
			discuss.setMusic(music);
			music.getDiscusslist().add(discuss);
			musicDao.save(music);
		}
		discuss.setContent(discussRequestDTO.getContent());
		discuss.setLikernumber(new Integer(0));
		discussDao.save(discuss);		
		extAjaxResponse.setMsg("评论成功");
		extAjaxResponse.setSuccess(true);
		return extAjaxResponse;
	}

	@Override
	public ExtAjaxResponse delete(Long id) {
		// TODO Auto-generated method stub
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(id.equals(null)||id.equals(0L)) {
			extAjaxResponse.setMsg("传入的评论id为空,系统传输发生错误！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
		if(discussDao.existsById(id)) {
			Discuss discuss=discussDao.findById(id).get();
			
			if(discuss.getUser()!=null) {
				User user=discuss.getUser();
				user.getDiscussList().remove(discuss);
				userDao.save(user);
			}
			discuss.setUser(null);
			if(discuss.getMusic()!=null) {
				Music music=discuss.getMusic();
				music.getDiscusslist().remove(discuss);
				musicDao.save(music);
			}
			discuss.setMusic(null);
			if(discuss.getReplylist().size()!=0) {
				List<Reply> replylist=discuss.getReplylist();
				for(Reply reply:replylist) {
					if(reply.getUser()!=null) {
						User user=reply.getUser();
						user.getReplyList().remove(reply);
						userDao.save(user);
						
					}
					reply.setUser(null);
					replyDao.save(reply);
				}
			}
			discussDao.save(discuss);
			discussDao.deleteById(id);
			extAjaxResponse.setMsg("删除评论成功！");
			extAjaxResponse.setSuccess(true);
			return extAjaxResponse;
		   }
		else {
			extAjaxResponse.setMsg("该评论不存在！");
			extAjaxResponse.setSuccess(false);
			return extAjaxResponse;
		}
	}

	@Override
	public ExtAjaxResponse addlike(Long id) {
		// TODO Auto-generated method stub
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(discussDao.existsById(id)) {
			Discuss discuss=discussDao.findById(id).get();
			Integer likenember=discuss.getLikernumber();
			if(likenember==null) 
				likenember=new Integer(0);
			int i=likenember.intValue()+1;
			likenember=new Integer(i);
			discuss.setLikernumber(likenember);
			discussDao.save(discuss);
			extAjaxResponse.setSuccess(true);
			extAjaxResponse.setMsg("点赞成功！");
			return extAjaxResponse;
		}
		extAjaxResponse.setSuccess(false);
		extAjaxResponse.setMsg("点赞失败！");
		return extAjaxResponse;
	}

	@Override
	public ExtAjaxResponse deletelike(Long id) {
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		if(discussDao.existsById(id)) {
			Discuss discuss=discussDao.findById(id).get();
			Integer likenember=discuss.getLikernumber();
			if(likenember==null) 
				likenember=new Integer(0);
			int i=likenember.intValue()-1;
			likenember=new Integer(i);
			discuss.setLikernumber(likenember);
			discussDao.save(discuss);
			extAjaxResponse.setSuccess(true);
			extAjaxResponse.setMsg("取消点赞成功！");
			return extAjaxResponse;
		}
		extAjaxResponse.setSuccess(false);
		extAjaxResponse.setMsg("取消点赞失败！");
		return extAjaxResponse;
	}

	@Override
	public DiscussResponseDTO getpage(int pagenumber) {
		// TODO Auto-generated method stub
		if(pagenumber<=0)//如果请求页数小于或等于0，设置默认值
			pagenumber=1;
		
		DiscussResponseDTO discussResponseDTO=new DiscussResponseDTO();
		Pageable page = new PageRequest(pagenumber-1,pageSize);
		Page<Discuss> discussList = discussDao.findAll(page);
		List<Discuss> list=discussList.getContent();
		List<DiscussDTO> discussdtolist=new ArrayList<DiscussDTO>();
		for(Discuss discuss:list) {
			DiscussDTO discussDTO=new DiscussDTO();
			BeanUtil.discussTodiscussDTO(discuss, discussDTO);
//			discussDTO.setContent(discuss.getContent());
//			discussDTO.setLikernumber(discuss.getLikernumber());
			discussdtolist.add(discussDTO);
		}
        if(discussList.getContent().size()==0)
        	discussResponseDTO.setSuccess(false);
        else
        	discussResponseDTO.setSuccess(true);
		discussResponseDTO.setDiscussdtolist(discussdtolist);
		discussResponseDTO.setNownumber(discussList.getContent().size());
		discussResponseDTO.setNowpage(discussList.getNumber()+1);
		//discussResponseDTO.setSuccess(false);
		discussResponseDTO.setTotalnumber(((int)discussList.getTotalElements())+1);
		discussResponseDTO.setTotalpage(discussList.getTotalPages());
		return discussResponseDTO;
	}
    
	@Override
	public DiscussResponseDTO getDiscusspage(DiscussPageDTO discussPageDTO) {
		// TODO Auto-generated method stub
		DiscussResponseDTO discussResponseDTO=new DiscussResponseDTO();
		if(discussPageDTO.getMusic_id().equals(0L)) {
			discussResponseDTO.setSuccess(false);
			discussResponseDTO.setMsg("传入的音乐id为空");
			return discussResponseDTO;
		}
		
		if(musicDao.existsById(discussPageDTO.getMusic_id())) {
		    Music music=musicDao.findById(discussPageDTO.getMusic_id()).get();
		    List<Discuss> discusslist=music.getDiscusslist();
		    if(discusslist.size()==0) {
	        	discussResponseDTO.setSuccess(false);
	        	discussResponseDTO.setMsg("没有数据！");
				return discussResponseDTO;
			}
		    //获取分页后的数据
		    List<Discuss> pagelist=BeanUtil.fenye(discusslist,discussPageDTO.getPage());
		    List<DiscussDTO> discussdtolist=new ArrayList<DiscussDTO>();
			for(Discuss discuss:pagelist) {
				DiscussDTO discussDTO=new DiscussDTO();
				BeanUtil.discussTodiscussDTO(discuss, discussDTO);
				discussdtolist.add(discussDTO);
			}
			
	      
	        discussResponseDTO.setSuccess(true);
			discussResponseDTO.setDiscussdtolist(discussdtolist);;
			discussResponseDTO.setNownumber(pagelist.size());
			discussResponseDTO.setNowpage(BeanUtil.nowpage(discusslist,discussPageDTO.getPage()));
			discussResponseDTO.setTotalnumber(discusslist.size());
			discussResponseDTO.setTotalpage(BeanUtil.pageTotal(discusslist));
				
			if(discussResponseDTO.getNowpage()==discussResponseDTO.getTotalpage())
				discussResponseDTO.setMsg("已经到底了！");
			return discussResponseDTO;
		    
		}
		discussResponseDTO.setSuccess(false);
		discussResponseDTO.setMsg("此音乐不存在");
		return discussResponseDTO;
	}
    
    
    
    
    
}
