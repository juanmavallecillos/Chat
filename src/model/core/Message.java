package model.core;

public class Message {
	private String message;
	private String from;
	public static final String REFRESH_MSG = "refresh message";
	
	public Message(String message, String from) {
		super();
		this.message = message;
		this.from = from;
	}
	
	public String getMessage() {
		return message;
	}

	public String getFrom() {
		return from;
	}

	@Override
	public String toString() {
		return message;
	}
	
	
	

}
