package controller.login;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import model.core.ConversationsManager;
import model.db.CategoryDB;

@ManagedBean
@RequestScoped
public class UserLoginController {
	@ManagedProperty(value="#{userLoginBean}")
	private UserLoginBean userLoginBean;
	@EJB private CategoryDB categoryDB;
	@EJB
	private ConversationsManager convManager; 
	
	
	public UserLoginController() {}
	
	public void setUserLoginBean(UserLoginBean userLoginBean) {
		this.userLoginBean = userLoginBean;
	}
	
	public Set<String> getCategories(){
		return categoryDB.getCategories();
	}
	
	public List<String> getSubCategories(){
		return categoryDB.getSubCategories(userLoginBean.getSelectedCategory());
	}
	
	public String createConversation() {
		long conversationId = convManager.createConversation(userLoginBean.getName());
		userLoginBean.setConversationId(conversationId);
		return "userChat.xhtml";
		
	}
}
