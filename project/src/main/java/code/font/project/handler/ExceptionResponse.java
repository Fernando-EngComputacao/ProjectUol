package code.font.project.handler;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer status_code;
	private String message;
	
	
	//Constructor
	public ExceptionResponse(Integer status_code, String message) {
		this.status_code = status_code;
		this.message = message;
	}
	
	//Getters
	public Integer getStatus_code() {
		return status_code;
	}
	public String getMessage() {
		return message;
	}	
}