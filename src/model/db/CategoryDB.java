package model.db;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class CategoryDB {
	private Hashtable<String, List<String>> categories;
	
	public CategoryDB() {
		super();
	}
	
	@PostConstruct
	private void init() {
		categories = new Hashtable<String, List<String>>();
		List<String> categoria1 = new ArrayList<String>();
		categoria1.add("SubCategoria1");
		categoria1.add("SubCategoria2");
		List<String> categoria2 = new ArrayList<String>();
		categoria2.add("SubCategoria3");
		categoria2.add("SubCategoria4");
		categories.put("Categoria 1", categoria1);
		categories.put("Categoria 2", categoria2);
	}
	
	public Set<String> getCategories(){
		return categories.keySet();
	}
	
	public List<String> getSubCategories(String category){
		return categories.get(category);
	}
	
	public String getFirstCategory() {
		return categories.entrySet().iterator().next().getKey();
	}
	
}
