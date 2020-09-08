package business;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observer;

public interface RestaurantProcessing {
	
	public void addObserver(Observer o);
	public void setRestaurantMenu(ArrayList<MenuItem> restaurantMenu);
	public void setOrders(Map<Order, ArrayList<MenuItem>> orders);
	public void insertMenuItem(MenuItem menuItem);
	public void deleteMenuItem(MenuItem menuItem);
	public ArrayList<MenuItem> getRestaurantMenu();
	public Map<Order, ArrayList<MenuItem>> getOrders();
	public MenuItem editMenuItem(MenuItem menuItem);
	public void addOrder(Order o, ArrayList<MenuItem> list) ;
	public Integer computePrice(ArrayList<MenuItem> list);

}	
