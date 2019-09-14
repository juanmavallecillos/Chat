package controller.chat;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class OperatorChatBean {
	private String history = "This is your chat box, as soon as you	"
			+ "recieve/send your message will appear here!";
	private ConversationDetails currentConversation;
	private List<ConversationDetails> conversations;
	
	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}
	
	public ConversationDetails getCurrentConversation() {
		return currentConversation;
	}
	
	public void setCurrentConversation(ConversationDetails currentConversation) {
		this.currentConversation = currentConversation;
	}
	
	public List<ConversationDetails> getConversations() {
		return conversations;
	}
	
	public void setConversations(List<ConversationDetails> conversations) {
		this.conversations = conversations;
	}

	
	
	
	public static class ConversationDetails{
		private long conversationId;
		private String user;
		private String conversationLinkStyle;
		private static final String UNSELECTED_CHAT_STYLE = "border: 1px solid black; color: black; padding: 1px; margin: 10px;";
		private static final String SELECTED_CHAT_STYLE = "border: 1px solid black; padding: 1px; margin: 10px; background-color: cyan; color: black; text-decoration: none;";
		
		private ConversationDetails(long conversationId, String user, String conversationLinkStyle) {
			this.conversationId = conversationId;
			this.user = user;
			this.conversationLinkStyle = conversationLinkStyle;
		}
		
		/**
		* Builds a ConversationDetails object setting the css style to
		*unselected_chat_style.
		* @param conversationId The id of the conversation
		* @param user The user who created the conversation
		* @return a ConversationDetails object.
		*/
		public static ConversationDetails getUnselectedConversationDetailsInstance(long conversationId, String user) {
			
			ConversationDetails newConversationDetails = new ConversationDetails(conversationId, user, UNSELECTED_CHAT_STYLE);
			return newConversationDetails;
		}
		
		/**
		* Builds a ConversationDetails object setting the css style to *
		* selected_chat_style
		* @param conversationId The id of the conversation
		* @param user The user who created the conversation
		* @return a ConversationDetails object.
		*/
		public static ConversationDetails getSelectedConversationDetailsInstance(long conversationId, String user) {
			ConversationDetails newConversationDetails = new ConversationDetails(conversationId, user, SELECTED_CHAT_STYLE);
			return newConversationDetails;
		}
		
		public long getConversationId() {
			return conversationId;
		}
		
		public String getConversationLinkStyle() {
			return conversationLinkStyle;
		}
		
		public String getUser() {
			return user;
		}
		
		public void switchLinkStyle() {
			if(conversationLinkStyle == UNSELECTED_CHAT_STYLE) {
				conversationLinkStyle = SELECTED_CHAT_STYLE;
			}
			else {
				if(conversationLinkStyle == SELECTED_CHAT_STYLE) {
					conversationLinkStyle = UNSELECTED_CHAT_STYLE;
				}
			}
		}	
	}
}
