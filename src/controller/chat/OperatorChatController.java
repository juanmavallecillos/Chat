package controller.chat;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import controller.chat.OperatorChatBean.ConversationDetails;
import controller.login.OperatorLoginBean;
import model.core.Conversation;
import model.core.ConversationsManager;
import model.core.Operator;
import model.db.OperatorManager;


@SessionScoped
@ManagedBean(name="operatorChatController")
public class OperatorChatController {
	@EJB
	private OperatorManager operatorManager;
	@EJB
	private ConversationsManager conversationsManager;
	@ManagedProperty(value = "#{operatorChatBean}")
	private OperatorChatBean operatorChatBean;
	@ManagedProperty(value = "#{operatorLoginBean}")
	private OperatorLoginBean operatorLoginBean;
	
	public OperatorChatController() {}
	
	public void setOperatorChatBean(OperatorChatBean operatorChatBean) {
		this.operatorChatBean = operatorChatBean;
	}
	
	public void setOperatorLoginBean(OperatorLoginBean operatorLoginBean) {
		this.operatorLoginBean = operatorLoginBean;
	}
	
	public void refreshConversations() {
		List<ConversationDetails> convDet_List = new ArrayList<ConversationDetails>();
		OperatorChatBean.ConversationDetails convDet = null;
		Operator operator = getOperator();
		List<Conversation> activeChats = operator.getActiveChats();
		Conversation currentConv = operator.getCurrentConversation();
		
		for(Conversation conv : activeChats) {
			if (conv.getConversationID() == currentConv.getConversationID()) {
				convDet = ConversationDetails.getSelectedConversationDetailsInstance(conv.getConversationID(), conv.getUserName());
				operatorChatBean.setCurrentConversation(convDet);
			}
			else {	
				convDet = ConversationDetails.getUnselectedConversationDetailsInstance(conv.getConversationID(), conv.getUserName());
			}
			convDet_List.add(convDet);
		}
		operatorChatBean.setConversations(convDet_List);
	}
	
	/**
	* Method called from the view when the user has clicked over one
	* of the conversations in the list of conversations.
	* This method has to set the style of the new selected converation
	* to selected and set the previous selected conversation to unselected.
	* @param newConversation The new conversation clicked over.
	*/
	public void changeToConversation(ConversationDetails convDet_newConv) {
		OperatorChatBean.ConversationDetails convDet_CurrentConv = operatorChatBean.getCurrentConversation();
		convDet_CurrentConv.switchLinkStyle();
		convDet_newConv.switchLinkStyle();
		operatorChatBean.setCurrentConversation(convDet_newConv);
		
		Conversation newConv = conversationsManager.getConversationById(convDet_newConv.getConversationId());
		operatorChatBean.setHistory(newConv.toString());
		Operator operator = getOperator();
		operator.setCurrentConversation(newConv);
	}
	
	private Operator getOperator() {
		return operatorManager.getOperatorById(operatorLoginBean.getOperatorId());
	}
}
