package com.example.myMusic.common.dto.singet;

import java.util.List;

public class SingerRspDTO {
         private boolean success;
         private String msg;
         private List<SingerMsgDTO> list;
         
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public List<SingerMsgDTO> getList() {
			return list;
		}
		public void setList(List<SingerMsgDTO> list) {
			this.list = list;
		}
	
}
