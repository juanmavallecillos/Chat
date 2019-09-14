package controller.login;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.db.CategoryDB;

@ManagedBean
@SessionScoped
public class UserLoginBean {
	@EJB
	private CategoryDB categoryDB;
	private String name;
	private long conversationId;
	private String selectedCategory;
	private String selectedSubCategory;
	
	public UserLoginBean() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getConversationId() {
		return conversationId;
	}

	public void setConversationId(long conversationId) {
		this.conversationId = conversationId;
	}

	public String getSelectedCategory() {
		if(selectedCategory == null) {
			selectedCategory = categoryDB.getFirstCategory();
		}
		return selectedCategory;
	}

	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public String getSelectedSubCategory() {
		
		return selectedSubCategory;
	}

	public void setSelectedSubCategory(String selectedSubCategory) {
		this.selectedSubCategory = selectedSubCategory;
	}
	
	
}
