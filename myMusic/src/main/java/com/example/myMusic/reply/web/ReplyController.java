package com.example.myMusic.reply.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myMusic.common.dto.LikeDTO;

import com.example.myMusic.common.dto.reply.ReplyPageDTO;
import com.example.myMusic.common.dto.reply.ReplyResponseDTO;
import com.example.myMusic.common.dto.reply.ReplySaveDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;
import com.example.myMusic.reply.server.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	@PostMapping(value="/save",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse save(@RequestBody ReplySaveDTO replySaveDTO) {		
		return replyService.save(replySaveDTO);
	}
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable ("id") Long id) {
		
		return replyService.delete(id);
	}
	@PostMapping(value="/addlike",consumes=MediaType.APPLICATION_JSON_VALUE)//
	public ExtAjaxResponse addlike(@RequestBody LikeDTO likeDTO) {

		return replyService.addlike(likeDTO.getId());
	}
	
	@PostMapping(value="/deletelike",consumes=MediaType.APPLICATION_JSON_VALUE)
    public ExtAjaxResponse deletelike(@RequestBody LikeDTO likeDTO) {
		
		return replyService.deletelike(likeDTO.getId());
		
	}
	
	//以音乐id和页数返回相应的评论
	@GetMapping(value="/getReply",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ReplyResponseDTO getReplypage(@RequestBody ReplyPageDTO replyPageDTO) {		
		return replyService.getReplypage(replyPageDTO);	
	}
	
}
