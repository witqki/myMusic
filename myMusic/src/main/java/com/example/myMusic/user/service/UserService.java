package com.example.myMusic.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.myMusic.common.dto.CheckcCodeDTO;
import com.example.myMusic.common.dto.UserloginDTO;
import com.example.myMusic.common.dto.UserregisteredDTO;
import com.example.myMusic.common.dto.user.UserresetDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.user.entities.User;



public interface UserService {
	public ExtAjaxResponse userlogin(UserloginDTO userlogindto) ;
	public ExtAjaxResponse userregistered(UserregisteredDTO userregisteredDTO) ;
	 public ExtAjaxResponse userreset(UserresetDTO userresetDTO);
	 public ExtAjaxResponse sendcheckcode(CheckcCodeDTO userresetDTO);
	
	public User save(User entity);
	public Optional<User> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	
	public void deleteAll(Long[] ids);
	
	public Page<User> findAll(Specification<User> spec, Pageable pageable);
    
	
	
	public List<User> findAll();
}
