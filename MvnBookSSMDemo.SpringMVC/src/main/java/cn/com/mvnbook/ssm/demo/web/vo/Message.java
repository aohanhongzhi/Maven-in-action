package cn.com.mvnbook.ssm.demo.web.vo;
public class Message {
    private String code;
    private String message;
    
	public Message(String message) {
		super();
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	} 
}

