package com.example.myMusic.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
import com.example.myMusic.user.entities.User;



public interface UserService {
	public LoginRspDTO phonelogin(UserloginDTO userloginDTO); 
	public ExtAjaxResponse replyother(AdddiscussDTO adddiscussDTO);
	 public DiscussResponseDTO deletemydiscuss( AdddiscussDTO adddiscussDTO); 
	 public DiscussResponseDTO lookmydiscuss( AdddiscussDTO adddiscussDTO);
	 public ExtAjaxResponse adddiscuss( AdddiscussDTO adddiscussDTO);
	 public ExtAjaxResponse deletelike( AddsongDTO AddsongDTO);
	public ExtAjaxResponse getlike( AddsongDTO AddsongDTO);
	public DiscussResponseDTO getdiscuss( AddsongDTO addsongDTO); 
	 public ExtAjaxResponse addsongtomy(AddsongDTO addsongDTO); 
	public ExtAjaxResponse searchcheck( ExtAjaxResponse extAjaxResponse);
	public ExtAjaxResponse savemymessage(LoginRspDTO loginRspDTO) ;
	public LoginRspDTO getmymessage(Long id) ;
	public LoginRspDTO userlogin(UserloginDTO userlogindto) ;
	public ExtAjaxResponse userregistered(UserregisteredDTO userregisteredDTO) ;
	 public ExtAjaxResponse userreset(UserresetDTO userresetDTO);
	 public ExtAjaxResponse sendcheckcode(CheckcCodeDTO userresetDTO);
	 public SearchResponseDTO mySongList(UserSongListDTO userSongListDTO);
	 public ExtAjaxResponse createSongList(UserSongListDTO userSongListDTO);
	 public ExtAjaxResponse deleteSongList(UserSongListDTO userSongListDTO);
	 public ExtAjaxResponse updateSongList(UserSongListDTO userSongListDTO);
	 public SongListRspDTO lookSongList(Long id);
	 public ExtAjaxResponse likeSongList(UserSongListDTO userSongListDTO);
	 public SongListRspDTO check_likeSongList(Long id);
	 public ExtAjaxResponse delete_likeSongList(UserSongListDTO userSongListDTO);
	 public ExtAjaxResponse add_likeSongList(UserSongListDTO userSongListDTO);
	 public SongListRspDTO detail_likeSongList( Long id);
	public User save(User entity);
	public Optional<User> findById(Long id);
	public boolean existsById(Long id);
	public long count();
	public void deleteById(Long id);
	
	public void deleteAll(Long[] ids);
	
	public Page<User> findAll(Specification<User> spec, Pageable pageable);
    
	
	
	public List<User> findAll();
	public ExtAjaxResponse deletemysong(AddsongDTO addsongDTO);
	public ExtAjaxResponse checkinmylike(AddsongDTO addsongDTO);
}
