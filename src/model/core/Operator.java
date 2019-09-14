package model.core;

import java.util.ArrayList;
import java.util.List;

public class Operator {
	
	private Conversation currentConversation;
	private List<Conversation> activeChats = new ArrayList<>();
	
	private boolean online;
	private String login;
	private String passwd;
	private long id;
	
	public Operator(long id, String login, String passwd) {
		this.id = id;
		this.login = login;
		this.passwd = passwd;
	}
	
	public boolean isOnline() {
		return online;
	}
	
	public void setOnline() {
		online = true;
	}
	
	public Conversation getCurrentConversation() {
		return currentConversation;                                                                                                                                                                                                                                                                                                          
	}
	
	public void setCurrentConversation(Conversation currentConversation) {
	  	this.currentConversation = currentConversation;
	}
	 
	public List<Conversation> getActiveChats() {
	  	return activeChats;
	}
	  
	public int getNumberOfActiveChats() {
	  	return activeChats.size();
	}
	  
	public void addConversation(Conversation conversation) {
	  	activeChats.add(conversation);
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public long getId() {
		return id;
	}
	
}
