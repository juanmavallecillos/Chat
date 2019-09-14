package model.endPoint;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.EJB;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import model.core.Conversation;
import model.core.ConversationsManager;
import model.core.Message;
import model.db.OperatorManager;

@ServerEndpoint(value = "/chat/{channel}")
public class ChatServerEndPoint {
	
	private static ConcurrentHashMap<String,Session>channelSessionMapping = new ConcurrentHashMap<>();
	@EJB private ConversationsManager conversationsManager;
	@EJB private OperatorManager operatorManager;
	
	
	public ChatServerEndPoint(){
		
	}
	
	/*
	 * This method is executed when a client EP sends a message.
	 * @param message The message that has been sent
	 * @param session the session the message has been sent throught
	 */
	@OnMessage
	public void handleMessage(String message, Session session) throws IOException, EncodeException {
		String fromChannel = session.getPathParameters().get("channel");
		String toChannel = "";
		boolean hasToBeSent = false;
		Session auxSession = null;
		Conversation conversation = null;
		
		if (doesChannelBelongToUser(Long.parseLong(fromChannel))) {
			conversation = conversationsManager.getConversationById(Long.parseLong(fromChannel));
			Long operatorId = conversation.getOperatorID();
			toChannel = operatorId.toString();
			long val1 = operatorManager.getOperatorById(operatorId).getCurrentConversation().getConversationID();
			long val2 = conversation.getConversationID();
			if( val1==val2 ) {
				hasToBeSent = true;
			}
		}
		else {
			conversation = operatorManager.getOperatorById(Long.parseLong(fromChannel)).getCurrentConversation();
			Long conversationId = conversation.getConversationID();
			toChannel = conversationId.toString();
			hasToBeSent = true;
		}
		
		if(hasToBeSent) {
			auxSession = channelSessionMapping.get(toChannel);
			auxSession.getBasicRemote().sendText(message);
		}
		Message m = new Message(message, fromChannel);
		conversation.addMessage(m);
	}
	
	/*
	 * This method is executed when a client opens a websocket session.
	 * @param session the session oppened by a client
	 * @param channel the first path parameter of the websockets connection
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("channel") String channel) {
		
		String fromChannel = session.getPathParameters().get("channel");
		channelSessionMapping.put(fromChannel, session);
		if(doesChannelBelongToUser(Long.parseLong(fromChannel))) {
			sendRefreshMsgUserOpenedASession(fromChannel);
		}
	}
	
	public void onClose(Session session, @PathParam("channel") String channel) {
	}
	
	private boolean doesChannelBelongToUser(Long channel) {
		return conversationsManager.containsConverstaion(channel);
	}
	
	private void sendRefreshMsgUserOpenedASession(String fromChannel) {
		Session auxSession = null;
		String toChannel = "";
		Conversation conversation = null;
		conversation = conversationsManager.getConversationById(Long.parseLong(fromChannel));
		Long operatorId = conversation.getOperatorID();
		toChannel = operatorId.toString();
		auxSession = channelSessionMapping.get(toChannel);
		
		try {
			auxSession.getBasicRemote().sendText(Message.REFRESH_MSG);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}