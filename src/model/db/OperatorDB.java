package model.db;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import model.core.Operator;

@Singleton
@Startup
public class OperatorDB {
	private Hashtable<Long, Operator> operators;
	
	public OperatorDB() {
		operators = new Hashtable<Long, Operator>();
	}
	
	@PostConstruct
	private void init() {
		operators.put((long) 600, new Operator(600, "Juanma", "1234"));
		operators.put((long) 800, new Operator(800, "Jose", "1234"));
	}
	
	public Hashtable<Long, Operator> getOperators(){
		return operators;
	}
}
