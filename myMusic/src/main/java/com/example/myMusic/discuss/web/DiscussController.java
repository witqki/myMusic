package com.example.myMusic.discuss.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myMusic.common.dto.LikeDTO;
import com.example.myMusic.common.dto.discuss.DiscussDTO;
import com.example.myMusic.common.dto.discuss.DiscussRequestDTO;
import com.example.myMusic.common.dto.discuss.DiscussResponseDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.discuss.entities.Discuss;
import com.example.myMusic.discuss.service.DiscussService;



@RestController
@RequestMapping("/discuss")
public class DiscussController {
	@Autowired
	private DiscussService discussService=null;
	
	@PostMapping(value="/save",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse save(@RequestBody DiscussRequestDTO discussRequestDTO) {
		ExtAjaxResponse extAjaxResponse=discussService.save(discussRequestDTO);
		
		
		return extAjaxResponse;
		
	}
	/*@DeleteMapping(value="/delete",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse delete(@RequestBody Long id) {
		ExtAjaxResponse extAjaxResponse=discussService.delete(id);
		return extAjaxResponse;
	}*/
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable ("id") Long id) {
		ExtAjaxResponse extAjaxResponse=discussService.delete(id);
		return extAjaxResponse;
	}
	@PostMapping(value="/addlike",consumes=MediaType.APPLICATION_JSON_VALUE)//
	public ExtAjaxResponse addlike(@RequestBody LikeDTO likeDTO) {
	
		
		
		return discussService.addlike(likeDTO.getId());
	}
	
	@PostMapping(value="/deletelike",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ExtAjaxResponse deletelike(@RequestBody LikeDTO likeDTO) {
		
		return discussService.deletelike(likeDTO.getId());
		
	}
	
	@GetMapping(value="{page}")
	public DiscussResponseDTO pageList(@PathVariable ("page") int pagenumber){
		
		//DiscussResponseDTO discussResponseDTO=discussService.getpage(pagenumber);
		//List<DiscussResponseDTO> discussResponseDTO=new ArrayList<DiscussResponseDTO>();
		
		return discussService.getpage(pagenumber);
		
	}
	
	
}
