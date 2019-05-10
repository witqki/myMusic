package com.example.myMusic.common.dto.adminUser;

public class ComUserDTO {
    private Long id;
    private boolean isWrite;//是否能发评论
    private boolean isAdmin;//能否申请歌单分享
    private boolean iswrite=false;
    private boolean isadmin=false;
    
	public boolean isIswrite() {
		return iswrite;
	}
	public void setIswrite(boolean iswrite) {
		this.iswrite = iswrite;
	}
	public boolean isIsadmin() {
		return isadmin;
	}
	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isWrite() {
		return isWrite;
	}
	public void setWrite(boolean isWrite) {
		this.isWrite = isWrite;
	}

    
}
