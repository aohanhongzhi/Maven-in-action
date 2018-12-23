package cn.com.mvnbook.ssh.demo.entity;

public class MvnUser {
	private int urId;
    private String urUserName;
    private String urPassword;
    private int urAge;
    private String urStatus = Status.ACTIVE.getStatus();//Active
	public int getUrId() {
		return urId;
	}
	public void setUrId(int urId) {
		this.urId = urId;
	}
	public String getUrUserName() {
		return urUserName;
	}
	public void setUrUserName(String urUserName) {
		this.urUserName = urUserName;
	}
	public String getUrPassword() {
		return urPassword;
	}
	public void setUrPassword(String urPassword) {
		this.urPassword = urPassword;
	}
	public int getUrAge() {
		return urAge;
	}
	public void setUrAge(int urAge) {
		this.urAge = urAge;
	}
	public String getUrStatus() {
		return urStatus;
	}
	public void setUrStatus(String urStatus) {
		this.urStatus = urStatus;
	}
}
