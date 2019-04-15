package com.example.myMusic.discuss.service;

import java.util.List;

import com.example.myMusic.common.dto.discuss.DiscussPageDTO;
import com.example.myMusic.common.dto.discuss.DiscussRequestDTO;
import com.example.myMusic.common.dto.discuss.DiscussResponseDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;

public interface DiscussService {
    public ExtAjaxResponse save(DiscussRequestDTO discussRequestDTO);
	public ExtAjaxResponse delete(Long id);
	public ExtAjaxResponse addlike(Long id);
	public ExtAjaxResponse deletelike(Long id);
	public DiscussResponseDTO getpage(int pagenumber);
	public DiscussResponseDTO getDiscusspage(DiscussPageDTO discussPageDTO);
}
