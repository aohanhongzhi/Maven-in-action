package cn.com.mvnbook.ssh.demo.entity;

public enum Status {
	ACTIVE("Active"),
    INACTIVE("Inactive"),
    DELETED("Deleted"),
    LOCKED("Locked");
	
    private String status;
	private Status(final String status){
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String toString(){
		return this.status;
	}
}

