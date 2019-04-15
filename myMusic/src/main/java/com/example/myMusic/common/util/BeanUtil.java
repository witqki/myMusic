package com.example.myMusic.common.util;

import java.util.List;

import com.example.myMusic.common.dto.UserregisteredDTO;
import com.example.myMusic.common.dto.discuss.DiscussDTO;
import com.example.myMusic.common.dto.reply.ReplyDTO;
import com.example.myMusic.discuss.entities.Discuss;
import com.example.myMusic.reply.entities.Reply;
import com.example.myMusic.user.entities.User;

public class BeanUtil {
	
	private static int pagesize=3;
        public static User UserregisteredDTOtoUser(UserregisteredDTO userregisteredDTO,User user) {
        	user.setName(userregisteredDTO.getName());
        	user.setPassword(userregisteredDTO.getPassword());
        	user.setSex(userregisteredDTO.getSex());
        	user.setPhone(userregisteredDTO.getPhone());
        	user.setEmail(userregisteredDTO.getEmail());
        	return user;
        }
        public static void discussTodiscussDTO(Discuss discuss,DiscussDTO discussdto) {
        	discussdto.setId(discuss.getId());
        	discussdto.setContent(discuss.getContent());
        	discussdto.setLikernumber(discuss.getLikernumber());
        }
        public static void replyToreplyDTO(Reply reply,ReplyDTO replydto) {
        	replydto.setId(reply.getId());
        	replydto.setContent(reply.getContent());
        	replydto.setLikernumber(reply.getLikernumber());
        }
        public static List fenye(List list,int page){
        	if(page<=0)
        		page=1;
            int totalcount=list.size();//总数据
            int pagecount=0;//页面总数
            int m=totalcount%pagesize;
            //获取页面总数
            if  (m>0){
                pagecount=totalcount/pagesize+1;
            }else{
                pagecount=totalcount/pagesize;
            }
            if(page>pagecount)
            	page=pagecount;
            //如果只有一页
            if (m==0){
                List subList= list.subList(0,totalcount);
                return subList;
            }
            else{
            	 if (page==pagecount){//如果取最后一页
                     List subList= list.subList(pagesize*(page-1),totalcount);
                     return subList;
                 }else{
                     List subList= list.subList((page-1)*pagesize,pagesize*(page));
                     return subList;
                 }
            }
           
        }
        public static int  pageTotal(List list) {
        	 int totalcount=list.size();//总数据
        	 int pagecount=0;//页面总数
             int m=totalcount%pagesize;
             //获取页面总数
             if  (m>0){
                 pagecount=totalcount/pagesize+1;
             }else{
                 pagecount=totalcount/pagesize;
             }
			return pagecount;
        	
        }
        public static int nowpage(List list,int page) {
        	if(page<=0)
        		page=1;
            int totalcount=list.size();//总数据
            int pagecount=0;//页面总数
            int m=totalcount%pagesize;
            //获取页面总数
            if  (m>0){
                pagecount=totalcount/pagesize+1;
            }else{
                pagecount=totalcount/pagesize;
            }
            if(page>pagecount)
            	page=pagecount;
            return page;
        }
}
