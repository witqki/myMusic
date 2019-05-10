package com.example.myMusic.common.dto.adminUser;

public class CheckDTO {
	     private Long discussid;
         private boolean isSuperAdmin=false;
         private boolean success=false;
         private Long id;




		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

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
