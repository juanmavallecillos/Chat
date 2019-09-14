package model.core;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import model.db.OperatorManager;

@Singleton
public class ConversationsManager {
	
	private static long lastConversationId = System.currentTimeMillis();
	private Map<Long, Conversation> conversations = new HashMap<>();
	@EJB private OperatorManager operatorManager;
	
	public ConversationsManager() {
		super();
	}
	
	@PostConstruct
	private void init() {
	}
	
	public long createConversation(String userName) {
		long conversationId = generateConversationId();
		Operator operator = operatorManager.getAvailableOperator();
		Conversation newConversation = new Conversation(conversationId, operator.getId(), userName);
		operator.addConversation(newConversation);
		conversations.put(conversationId, newConversation);
		if (operator.getCurrentConversation() == null) {
			operator.setCurrentConversation(newConversation);
		}
		return conversationId;
	}
	
	public Conversation getConversationById(long conversationId) {
		return conversations.get(conversationId);
	}
	
	public boolean containsConverstaion(long conversationId) {
		return conversations.containsKey(conversationId);
	}
	
	private static long generateConversationId() {
		lastConversationId++;
		return lastConversationId;
	}

}
