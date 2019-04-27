package com.example.myMusic.user.service.impl;

import java.util.Date;
import java.util.List;
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
import com.example.myMusic.common.dto.user.UserresetDTO;
import com.example.myMusic.common.util.BeanUtil;
import com.example.myMusic.common.util.Rules;
import com.example.myMusic.common.web.ExtAjaxResponse;
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
    private CodeDao codeDao;
    
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
	public ExtAjaxResponse userlogin(UserloginDTO userlogindto) {
		//这里三种登录方式，0-用户名，1-手机号，2-邮箱
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse(false,"");
	
		User user=new User();
		if(userlogindto.getCheckcode().intValue()==0) {
			logger.info("this is :"+userlogindto.getMessage());
			if(userlogindto.getMessage().trim()==null||userlogindto.getMessage().trim().equals(""))
			{
				extAjaxResponse.setMsg("输入用户名为空！"+userlogindto.getMessage());
				extAjaxResponse.setSuccess(false);
			}else if(userlogindto.getPassword().trim()==null||userlogindto.getPassword().trim().equals("")) {
				extAjaxResponse.setMsg("输入密码为空！");
				extAjaxResponse.setSuccess(false);
			}
			
			else {
				List<User> list=userDao.findByUsername(userlogindto.getMessage().trim());
				if(list.size()!=0&&list!=null) {
					user=list.get(0);
					if(user.getPassword().equals(userlogindto.getPassword().trim()))
					{
						extAjaxResponse.setMsg("登录成功！");
						extAjaxResponse.setSuccess(true);
					}else 
					{
						extAjaxResponse.setMsg("输入密码不正确！！");
						extAjaxResponse.setSuccess(false);
					}
				}else {
					extAjaxResponse.setMsg("系统查无此账号！");
					extAjaxResponse.setSuccess(false);
				}
			}
			
			
		}else if(userlogindto.getCheckcode().intValue()==1) {
			if(userlogindto.getMessage().trim()==null||userlogindto.getMessage().trim().equals(""))
			{
				extAjaxResponse.setMsg("输入手机号为空！");
				extAjaxResponse.setSuccess(false);
			}else if(userlogindto.getPassword().trim()==null||userlogindto.getPassword().trim().equals("")) {
				extAjaxResponse.setMsg("输入密码为空！");
				extAjaxResponse.setSuccess(false);
			}
			
			else {
				List<User> list=userDao.findByUserphone(userlogindto.getMessage().trim());
				if(list.size()!=0&&list!=null) {
					user=list.get(0);
					if(user.getPassword().equals(userlogindto.getPassword().trim()))
					{
						extAjaxResponse.setMsg("登录成功！");
						extAjaxResponse.setSuccess(true);
					}else 
					{
						extAjaxResponse.setMsg("输入密码不正确！！");
						extAjaxResponse.setSuccess(false);
					}
				}else {
					extAjaxResponse.setMsg("系统查无此账号！");
					extAjaxResponse.setSuccess(false);
				}
			}
		}else if(userlogindto.getCheckcode().intValue()==2) {
			if(userlogindto.getMessage().trim()==null||userlogindto.getMessage().trim().equals(""))
			{
				extAjaxResponse.setMsg("输入邮箱为空！");
				extAjaxResponse.setSuccess(false);
			}else if(userlogindto.getPassword().trim()==null||userlogindto.getPassword().trim().equals("")) {
				extAjaxResponse.setMsg("输入密码为空！");
				extAjaxResponse.setSuccess(false);
			}
			
			else {
				List<User> list=userDao.findByUseremail(userlogindto.getMessage().trim());
				if(list.size()!=0&&list!=null) {
					user=list.get(0);
					if(user.getPassword().equals(userlogindto.getPassword().trim()))
					{
						extAjaxResponse.setMsg("登录成功！");
						extAjaxResponse.setSuccess(true);
					}else 
					{
						extAjaxResponse.setMsg("输入密码不正确！！");
						extAjaxResponse.setSuccess(false);
					}
				}else {
					extAjaxResponse.setMsg("系统查无此账号！");
					extAjaxResponse.setSuccess(false);
				}
			}
		}else{
			logger.info("登录方式的选择出现问题");
			extAjaxResponse.setMsg("系统发生错误！");
			extAjaxResponse.setSuccess(false);
			
		}
		return extAjaxResponse;
		
		
		
	
	}

	
	public ExtAjaxResponse userregistered(UserregisteredDTO userregisteredDTO) {
		// TODO Auto-generated method stub
		
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse(true,"");
		if(userregisteredDTO.getName().trim()==null||userregisteredDTO.getName().trim().equals("")) {
			extAjaxResponse.setMsg("用户名为空！");
			extAjaxResponse.setSuccess(false);
		}else if(userregisteredDTO.getName().length()<4||userregisteredDTO.getName().length()>8) {
			extAjaxResponse.setMsg("用户名长度在 4 到 8个字符！");
			extAjaxResponse.setSuccess(false);
		}else if(!(userDao.findByUsername(userregisteredDTO.getName().trim()).size()==0)) {
			extAjaxResponse.setMsg("已存在该用户！");
			extAjaxResponse.setSuccess(false);
		}
		
		else if(userregisteredDTO.getPassword().trim()==null||userregisteredDTO.getPassword().trim().equals(""))
		{
			extAjaxResponse.setMsg("密码为空！");
			extAjaxResponse.setSuccess(false);
		}else if(userregisteredDTO.getCpassword().trim()==null||userregisteredDTO.getCpassword().trim().equals("")) {
			extAjaxResponse.setMsg("确认密码为空！");
			extAjaxResponse.setSuccess(false);
		}else if(!userregisteredDTO.getPassword().trim().equals(userregisteredDTO.getCpassword().trim())) {
			extAjaxResponse.setMsg("密码与确认密码不相同！");
			extAjaxResponse.setSuccess(false);
		}else if(!Rules.passwordrule(userregisteredDTO.getPassword().trim())) {
			extAjaxResponse.setMsg("密码要包含数字、字母、下划线，长度在 5 到 10 个字符，并且要同时含有数字和字母！");
			extAjaxResponse.setSuccess(false);
		}
		
		else if((!userregisteredDTO.getPhone().trim().equals(""))&&(userregisteredDTO.getPhone().trim()!=null)) {
			boolean b=Rules.phonerule(userregisteredDTO.getPhone().trim());
			if(!b) {
				extAjaxResponse.setMsg("电话号码不正确！");
				extAjaxResponse.setSuccess(false);
			}
			else {
				if(!(userDao.findByUserphone(userregisteredDTO.getPhone().trim()).size()==0)) {
					extAjaxResponse.setMsg("电话号码已存在！");
					extAjaxResponse.setSuccess(false);
				}
			}
		}else {}
		
		if((!userregisteredDTO.getEmail().trim().equals(""))&&(userregisteredDTO.getEmail().trim()!=null)) {
			boolean b=Rules.emailrule(userregisteredDTO.getEmail().trim());
			
			if(b==false) {
				
				extAjaxResponse.setMsg("邮箱格式不正确！");
				extAjaxResponse.setSuccess(false);
			}
			else {
				
				if(!(userDao.findByUseremail(userregisteredDTO.getEmail().trim()).size()==0)) {
					extAjaxResponse.setMsg("邮箱已存在！");
					extAjaxResponse.setSuccess(false);
					
				}
			}
		}else {	}
		//logger.info(extAjaxResponse.isSuccess());
		if(extAjaxResponse.isSuccess()) {
			User user=new User();
			user=BeanUtil.UserregisteredDTOtoUser(userregisteredDTO, new User());
			
			try{
				userDao.save(user);
				//logger.info("成功");
				extAjaxResponse.setMsg("注册成功！");
				extAjaxResponse.setSuccess(true);
			}catch(Exception e) {
				extAjaxResponse.setMsg("注册失败，系统错误！");
				extAjaxResponse.setSuccess(false);
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
		for(Code savecode:list) {
			long time=nowtime-savecode.getBuildtime();
			if(time<=BeanUtil.getLimittime()) {
				
			}else {
				codeDao.delete(savecode);
			}
			
		}
		ExtAjaxResponse extAjaxResponse=new ExtAjaxResponse();
		ExtAjaxResponse pswcheck=BeanUtil.checkpsw(userresetDTO.getPsw(), userresetDTO.getConpsw());
		if(userresetDTO.getId()!=null) {
			if(BeanUtil.isNull(userresetDTO.getCode())) {
				extAjaxResponse.setMsg("验证码为空");
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
							 List<User> maillist=userDao.findByUserphone(userresetDTO.getSender().trim());
						     if(maillist.size()!=0&&maillist!=null) {
						    	 User user=maillist.get(0);
						    	 user.setPassword(userresetDTO.getPsw().trim());
						    	 userDao.save(user);
						    	    extAjaxResponse.setMsg("success");
									extAjaxResponse.setSuccess(true);
						     }else {
						    	   extAjaxResponse.setMsg("该邮箱没有注册");
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
		}else {
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
					
				}else if(checkDTO.getId().equals(new Integer(1))) {//邮箱
					ExtAjaxResponse checkemail=BeanUtil.checkemail(checkDTO.getSender());
					if(checkemail.isSuccess()) {
						//发送验证码
						String code=BeanUtil.getCode();
						ExtAjaxResponse sendmail=BeanUtil.sendEmail(checkDTO.getSender().trim(), code);
						if(sendmail.isSuccess()) {
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

}
