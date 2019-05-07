package com.example.myMusic.music.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.myMusic.common.dto.music.SearchMusicDTO;
import com.example.myMusic.common.dto.music.SearchResponseDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.music.entities.Music;

public interface MusicService {
   public boolean existsById(Long id);
   public boolean save(Music music);
   public SearchResponseDTO searchContent(String searchcontent);
//   public ExtAjaxResponse downsong(HttpServletResponse response, Long songid);
//   public ExtAjaxResponse playsong(Long songid);
//   
//   public SearchResponseDTO songlist(Long id);

}
