package business;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem implements Serializable {
	
	private ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();
	private String nameP;
	private Integer price;
	private String description;
	public CompositeProduct(String name, String desc, ArrayList<MenuItem> ls) {
		this.nameP = name;
		this.menuList = ls;
		this.setPrice(computePrice());
		this.setDescription(desc);
	}

	@Override
	public Integer computePrice() {
		Integer p = 0 ;
		for(MenuItem it : menuList) {
			p = it.getPrice() + p;
		}
		return p;
	}
	/*public String getListDesc() {
		StringBuilder d = new StringBuilder() ;
		for(int i = 0; i < menuList.size(); i++) {
			d.append(menuList.get(i));
			if(i != (menuList.size() - 1)) {
				d.append(", ");
			}
		}
		return d.toString();
	}*/
	
	@Override
	public void add(MenuItem menuItem) {
		menuList.add(menuItem);
	}

	@Override
	public void remove(MenuItem menuItem) {
		// TODO Auto-generated method stub
		super.remove(menuItem);
	}

	@Override
	public MenuItem getChild(int i) {
		return this.menuList.get(i);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.nameP;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		super.print();
	}



	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
