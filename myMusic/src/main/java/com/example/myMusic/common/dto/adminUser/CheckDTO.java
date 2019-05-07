package com.example.myMusic.common.dto.adminUser;

public class CheckDTO {
	     private Long discussid;
         private boolean isSuperAdmin=false;

		public Long getDiscussid() {
			return discussid;
		}

		public void setDiscussid(Long discussid) {
			this.discussid = discussid;
		}

		public boolean isSuperAdmin() {
			return isSuperAdmin;
		}

		public void setSuperAdmin(boolean isSuperAdmin) {
			this.isSuperAdmin = isSuperAdmin;
		}
         
}
