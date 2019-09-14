package model.db;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.core.Operator;

@Stateless
public class OperatorManager {
	@EJB private OperatorDB operatorDB;
	
	public OperatorManager() {
		
	}
	
	public boolean isARegisteredOperator(String login, String passwd) {
		boolean registered = false;
		Operator op = getOperatorByLogin(login);
		if(op != null) {
			if(op.getPasswd().equals(passwd)) {
				registered = true;
			}
		}
		return registered;
	}
	
	public Operator getOperatorByLogin(String login) {
		Hashtable<Long, Operator> ht = operatorDB.getOperators();
		Set<Long> setOfOperators = ht.keySet();
		Iterator<Long> iterator = setOfOperators.iterator();
		boolean found = false;
		Operator operator = null;
		while(iterator.hasNext() && !found) {
			Long key = iterator.next();
			Operator op = ht.get(key);
			if(op.getLogin().equals(login)) {
				found = true;
				operator = op;
			}
		}
		return operator;
	}
	
	public Operator getOperatorById(long id) {
		return operatorDB.getOperators().get(id);
	}
	
	public Operator getAvailableOperator() {
		Hashtable<Long, Operator> ht = operatorDB.getOperators();
		Set<Long> setOfOperators = ht.keySet();
		Iterator<Long> iterator = setOfOperators.iterator();
		Operator operator = null;
		while(iterator.hasNext()) {
			Long key = iterator.next();
			Operator op = ht.get(key);
			if(op.isOnline()) {
				if(operator!=null) {
					if((op.getNumberOfActiveChats() < operator.getNumberOfActiveChats()) && (op.isOnline())) {
						operator = op;
					}
				}
				else {
					operator = op;
				}
			}
		}
		return operator;
	}	
}
