package com.example.myMusic.adminUser.service;



import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.myMusic.common.dto.adminUser.CheckDTO;
import com.example.myMusic.common.dto.adminUser.ComUserDTO;
import com.example.myMusic.common.dto.adminUser.SuperDTO;
import com.example.myMusic.common.dto.adminUser.UserRspDTO;
import com.example.myMusic.common.dto.discuss.DiscussResponseDTO;
import com.example.myMusic.common.dto.music.SearchMusicDTO;
import com.example.myMusic.common.dto.music.SearchResponseDTO;
import com.example.myMusic.common.dto.songList.SongListDTO;
import com.example.myMusic.common.dto.songList.SongListRspDTO;
import com.example.myMusic.common.dto.user.LoginRspDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;

public interface AdminUserService {
	public ExtAjaxResponse deleteuserDiscuss(ExtAjaxResponse extAjaxResponse); 
	 //public ExtAjaxResponse importsong(String  path);
	 public ExtAjaxResponse change_authority(ComUserDTO comUserDTO);
	 public UserRspDTO findUser(LoginRspDTO loginRspDTO) ;
	 public ExtAjaxResponse deleteUser(Long id);
	 public ExtAjaxResponse deletesong(Long id);
	// public ExtAjaxResponse changesong(SearchMusicDTO searchMusicDTO);
	 public SongListRspDTO getsongList(Long id);
	 public SongListRspDTO looksongList();
	 public SearchResponseDTO looksong();
	public DiscussResponseDTO checkDiscuss(ExtAjaxResponse extAjaxResponse);
	public ExtAjaxResponse changesongList(SongListDTO songListDTO) ;
	public ExtAjaxResponse addsongList( SongListDTO songListDTO);
	public SongListRspDTO getApplesongList();
	public ExtAjaxResponse agreeApplesongList(Long id);
	public ExtAjaxResponse refuseApplesongList( Long id);
	public ExtAjaxResponse deletesongList( Long id) ;
	public ExtAjaxResponse superlogin(SuperDTO superDTO);
}
