package com.example.myMusic.common.dto.adminUser;

public class ComUserDTO {
    private Long id;
    private boolean isWrite;//是否能发评论
    private boolean isapply;//能否申请歌单分享
    private boolean isAdmin;//能否申请歌单分享
    
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
	public boolean isIsapply() {
		return isapply;
	}
	public void setIsapply(boolean isapply) {
		this.isapply = isapply;
	}
    
}
