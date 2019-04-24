package com.example.myMusic.reply.server;

import com.example.myMusic.common.dto.reply.ReplyPageDTO;
import com.example.myMusic.common.dto.reply.ReplyResponseDTO;
import com.example.myMusic.common.dto.reply.ReplySaveDTO;
import com.example.myMusic.common.web.ExtAjaxResponse;

public interface ReplyService {
     public ExtAjaxResponse save(ReplySaveDTO replySaveDTO);
     public ExtAjaxResponse delete(Long id);
     public ExtAjaxResponse addlike(Long id);
     public ExtAjaxResponse deletelike(Long id);
     public ReplyResponseDTO getReplypage(ReplyPageDTO replyPageDTO);
}
