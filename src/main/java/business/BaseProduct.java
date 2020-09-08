package business;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable{
	private String name;
	private String description;
	private Integer price;
	
	public BaseProduct() {
		
	}
	
	public BaseProduct(String name, String desc, Integer price) {
		this.description = desc;
		this.name = name;
		this.price = price;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		super.print();
	}


	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	/*public String[] show(BaseProduct prod) {
		String[] prod1 = new String[3];
		prod1[0] = this.name;
		prod1[1] = this.description;
		prod1[2] = this.price.toString();
		return prod1;	
	}*/
}
