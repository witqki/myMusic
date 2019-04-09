package com.example.myMusic.user.service.impl;

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

import com.example.myMusic.common.dto.UserloginDTO;
import com.example.myMusic.common.dto.UserregisteredDTO;
import com.example.myMusic.common.util.BeanUtil;
import com.example.myMusic.common.util.Rules;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.user.dao.UserDao;
import com.example.myMusic.user.entities.User;
import com.example.myMusic.user.service.UserService;
import com.example.myMusic.user.web.UserController;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    
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
				extAjaxResponse.setMsg("输入用户名为空！");
				extAjaxResponse.setSuccess(false);
			}else if(userlogindto.getPassword().trim()==null||userlogindto.getPassword().trim().equals("")) {
				extAjaxResponse.setMsg("输入密码为空！");
				extAjaxResponse.setSuccess(false);
			}
			
			else {
				user=userDao.findByUsername(userlogindto.getMessage().trim()).get(0);
				if(user!=null) {
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
					extAjaxResponse.setMsg("系统查无此账号！"+userlogindto.getMessage());
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
				user=userDao.findByUserphone(userlogindto.getMessage().trim()).get(0);
				if(user!=null) {
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
				user=userDao.findByUseremail(userlogindto.getMessage().trim()).get(0);
				if(user!=null) {
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

}
