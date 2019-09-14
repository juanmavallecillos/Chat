package controller.login;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.db.OperatorManager;

@ManagedBean
@RequestScoped
public class OperatorLoginController {
	@ManagedProperty(value="#{operatorLoginBean}")
	private OperatorLoginBean operatorLoginBean;
	@EJB
	private OperatorManager operatorManager;
	private static final String USER_NOT_EXIST = "L'usuari no està registrat a la Base de Dades";
	
	public OperatorLoginController() {}
	
	public void setOperatorLoginBean(OperatorLoginBean operatorLoginBean) {
		this.operatorLoginBean = operatorLoginBean;
	}
	
	public String verifyUser() {
		String value = "";
		if (operatorManager.isARegisteredOperator(operatorLoginBean.getLogin(), operatorLoginBean.getPasswd())) {
			value = "operatorChat.xhtml";
			operatorManager.getOperatorByLogin(operatorLoginBean.getLogin()).setOnline();
			operatorLoginBean.setAsLogged();
			operatorLoginBean.setOperatorId(operatorManager.getOperatorByLogin(operatorLoginBean.getLogin()).getId());
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(USER_NOT_EXIST));
		}
		return value;
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index.xhtml?faces-redirect=true";
	}
}
