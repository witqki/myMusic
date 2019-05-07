package com.example.myMusic.discuss.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myMusic.common.dto.discuss.DiscussDTO;

import com.example.myMusic.common.dto.discuss.DiscussRequestDTO;
import com.example.myMusic.common.dto.discuss.DiscussResponseDTO;
import com.example.myMusic.common.dto.mix_discuss_reply.IdDTO;
import com.example.myMusic.common.dto.mix_discuss_reply.IsreplyDTO;
import com.example.myMusic.common.dto.mix_discuss_reply.MixDTO;
import com.example.myMusic.common.dto.mix_discuss_reply.MixrspDTO;
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
			user.getDiscusslist().add(discuss);
			
			userDao.save(user);
		}
		if(music!=null) {
			discuss.setMusic(music);
			music.getDiscusslist().add(discuss);
			musicDao.save(music);
		}
		discuss.setContent(discussRequestDTO.getContent());
		//discuss.setLikernumber(new Integer(0));
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
				user.getDiscusslist().remove(discuss);
				userDao.save(user);
			}
			discuss.setUser(null);
			if(discuss.getMusic()!=null) {
				Music music=discuss.getMusic();
				music.getDiscusslist().remove(discuss);
				musicDao.save(music);
			}
			discuss.setMusic(null);
	
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
			//Integer likenember=discuss.getLikernumber();
//			if(likenember==null) 
//				likenember=new Integer(0);
//			int i=likenember.intValue()+1;
//			likenember=new Integer(i);
			//discuss.setLikernumber(likenember);
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
		//	Integer likenember=discuss.getLikernumber();
//			if(likenember==null) 
//				likenember=new Integer(0);
//			int i=likenember.intValue()-1;
//			likenember=new Integer(i);
		//	discuss.setLikernumber(likenember);
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
		
		return discussResponseDTO;
	}
    
//	@Override
//	public DiscussResponseDTO getDiscusspage(DiscussPageDTO discussPageDTO) {
//		// TODO Auto-generated method stub
//		DiscussResponseDTO discussResponseDTO=new DiscussResponseDTO();
//		if(discussPageDTO.getMusic_id().equals(0L)) {
//			discussResponseDTO.setSuccess(false);
//			discussResponseDTO.setMsg("传入的音乐id为空");
//			return discussResponseDTO;
//		}
//		
//		if(musicDao.existsById(discussPageDTO.getMusic_id())) {
//		    Music music=musicDao.findById(discussPageDTO.getMusic_id()).get();
//		    List<Discuss> discusslist=music.getDiscusslist();
//		    if(discusslist.size()==0) {
//	        	discussResponseDTO.setSuccess(false);
//	        	discussResponseDTO.setMsg("没有数据！");
//				return discussResponseDTO;
//			}
//		    //获取分页后的数据
//		    List<Discuss> pagelist=BeanUtil.fenye(discusslist,discussPageDTO.getPage());
//		    List<DiscussDTO> discussdtolist=new ArrayList<DiscussDTO>();
//			for(Discuss discuss:pagelist) {
//				DiscussDTO discussDTO=new DiscussDTO();
//				BeanUtil.discussTodiscussDTO(discuss, discussDTO);
//				discussdtolist.add(discussDTO);
//			}
//			
//	      
//	        discussResponseDTO.setSuccess(true);
//			discussResponseDTO.setDiscussdtolist(discussdtolist);;
//			discussResponseDTO.setNownumber(pagelist.size());
//			discussResponseDTO.setNowpage(BeanUtil.nowpage(discusslist,discussPageDTO.getPage()));
//			discussResponseDTO.setTotalnumber(discusslist.size());
//			discussResponseDTO.setTotalpage(BeanUtil.pageTotal(discusslist));
//				
//			if(discussResponseDTO.getNowpage()==discussResponseDTO.getTotalpage())
//				discussResponseDTO.setMsg("已经到底了！");
//			return discussResponseDTO;
//		    
//		}
//		discussResponseDTO.setSuccess(false);
//		discussResponseDTO.setMsg("此音乐不存在");
//		return discussResponseDTO;
//	}

	@Override
	public MixrspDTO mixdiscuss(Long songid,int page) {
		// TODO Auto-generated method stub
		MixrspDTO mixrspDTO=new MixrspDTO();
		if(songid.equals(0L)) {
			mixrspDTO.setSuccess(false);
			mixrspDTO.setMsg("传入数据发生错误！");
			//mixrspDTO.setMsg("暂无评论！");
		}
		
		else {
			if(musicDao.existsById(songid)) {
				Music music=musicDao.findById(songid).get();
				List<Discuss> discusslist=music.getDiscusslist();
				
				if(discusslist.size()!=0&&discusslist!=null) {
					List<IdDTO> idlist=new ArrayList<IdDTO>();
					for(Discuss discuss:discusslist) {
						IdDTO iddis=new IdDTO();
						iddis.setId(discuss.getId());
						Date date=discuss.getCreateTime();
						if(date!=null) {
							iddis.setDate(date);
						}else {
							mixrspDTO.setSuccess(false);
							mixrspDTO.setMsg("存在评论"+discuss.getId()+"无对应创建时间！");
							return mixrspDTO;
						}
						
						idlist.add(iddis);
						
					
					}
					List<IdDTO> iddto=BeanUtil.sortlist(idlist);
//					Collections.sort(iddto, new Comparator<IdDTO>() {//降序排序，按里面的Date排
//			            @Override
//			            public int compare(IdDTO o1, IdDTO o2) {
//			                int flag = o1.getDate().compareTo(o2.getDate());
//			                if(flag == -1){//
//			                    flag = 1;
//			                }else if(flag == 1){
//			                    flag = -1;
//			                }//把这段删除就成升序排序
//			                return flag;
//			            }
//			        });
					mixrspDTO.setCount(iddto.size());
					mixrspDTO.setNowpage(BeanUtil.nowpage(iddto, page));
					mixrspDTO.setPageTotal(BeanUtil.pageTotal(iddto));
					 List<IdDTO> pagelist=BeanUtil.fenye(iddto,page);
					 if(pagelist.size()==0||pagelist==null) {
						 mixrspDTO.setSuccess(false);
							mixrspDTO.setMsg("分页时发生未知错误！");
							return mixrspDTO;
					 }
					 List<MixDTO> mixdto= new ArrayList<MixDTO>();
					for(IdDTO i:pagelist) {
						MixDTO mixDTO=new MixDTO();
						if(i.isIsreply()) { //这是回复的话
							if(replyDao.existsById(i.getId())) {
							   Reply reply=replyDao.findById(i.getId()).get();
							   mixDTO.setContent(reply.getContent());
							   mixDTO.setSendtime(reply.getCreateTime());
							   mixDTO.setIsreply(true);
							   mixDTO.setLikernumber(reply.getLikernumber());
						
							}else {
								    mixrspDTO.setSuccess(false);
									mixrspDTO.setMsg("不存在id为"+i.getId()+"的回复数据");
									return mixrspDTO;
							}
						}else {//这是评论的话
							if(discussDao.existsById(i.getId())) {
								Discuss discuss=discussDao.findById(i.getId()).get();
								mixDTO.setContent(discuss.getContent());
							//	mixDTO.setLikernumber(discuss.getLikernumber());
								mixDTO.setSendtime(discuss.getCreateTime());
								User user_in_discuss=discuss.getUser();
								if(user_in_discuss==null) {
									mixrspDTO.setSuccess(false);
									mixrspDTO.setMsg("id为"+i.getId()+"的评论数据无对应用户");
									return mixrspDTO;
								}else {
									mixDTO.setUserId(user_in_discuss.getId());
									mixDTO.setUsername(user_in_discuss.getName());
									
								}
								 mixdto.add(mixDTO);
							}else {
								    mixrspDTO.setSuccess(false);
									mixrspDTO.setMsg("不存在id为"+i.getId()+"的评论数据");
									return mixrspDTO;
							}
						}
						
					}
					mixrspDTO.setMsg("success");
					mixrspDTO.setSuccess(true);
					mixrspDTO.setMixdto(mixdto);

				}else {
					mixrspDTO.setCount(new Integer(0));
					mixrspDTO.setSuccess(true);
					mixrspDTO.setMsg("暂无评论！");
				}
			}else {
				mixrspDTO.setSuccess(false);
				mixrspDTO.setMsg("数据库查无此歌曲！");
			}
		}
		return mixrspDTO;
	}
    
    
    
    
    
}
