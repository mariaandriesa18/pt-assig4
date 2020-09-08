package business;

import java.io.Serializable;

public abstract class MenuItem implements Serializable { 
	
	public void add(MenuItem menuItem) {
		throw new UnsupportedOperationException();
	}
	
	public void remove(MenuItem menuItem) {
		throw new UnsupportedOperationException();
	}
	
	public MenuItem getChild(int i) {
		throw new UnsupportedOperationException();
	}
	
	public String getName() {
		throw new UnsupportedOperationException();
	}
	
	public String getDescription() {
		throw new UnsupportedOperationException();
	}
	public void print() {
		throw new UnsupportedOperationException();
	}
	
	public Integer getPrice() {
		throw new UnsupportedOperationException();
	}

	public Integer computePrice() {
		throw new UnsupportedOperationException();
	}
}
