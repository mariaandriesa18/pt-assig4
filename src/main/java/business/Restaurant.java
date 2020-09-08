package business;

import java.io.File;
/**
 * The menu and the orders should have the correct format
 * 
 *  @pre wellFormed() == true
 */
import java.util.*;

import data.RestaurantSerializator;
public class Restaurant extends Observable implements RestaurantProcessing, java.io.Serializable {

	private Map<Order, ArrayList<MenuItem>> orders = new HashMap<Order, ArrayList<MenuItem>>();
	private ArrayList<MenuItem> restaurantMenu = new ArrayList<MenuItem>();
	transient private ArrayList<Observer> observers;
	transient private RestaurantSerializator ser = new RestaurantSerializator();
	
	public Restaurant() {observers = new ArrayList<Observer>();	}
	
	public Restaurant(ArrayList<MenuItem> restaurantMenu,  Map<Order, ArrayList<MenuItem>> orders) {
		
		this.orders = orders;
		this.restaurantMenu= restaurantMenu;
	}
	
	@Override
	public void addObserver(Observer o) {
		this.observers.add(o);
	}
	/**
	 * Insert new menu item
	 * @pre menuItem.getPrice() > 0
	 * @pre menuItem.getName() != null
	 *
	 * @post restaurantMenu.size() = restaurantMenu@pre.size() + 1
	 */
	public void insertMenuItem(MenuItem menuItem) {
		this.restaurantMenu.add(menuItem);
		
	}

	public Map<Order, ArrayList<MenuItem>> getOrders() {
		return this.orders;
	}

	public void setOrders(Map<Order, ArrayList<MenuItem>> orders) {
		this.orders = orders;
	}
	
	public void setRestaurantMenu(ArrayList<MenuItem> restaurantMenu) 
	{
		this.restaurantMenu = restaurantMenu;
	}
	public ArrayList<MenuItem> getRestaurantMenu() {
		return restaurantMenu;
	}
	
	public void deleteMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		
	}
	public MenuItem editMenuItem(MenuItem menuItem) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Add order in list of orders. Notify observers
	 * @post list.size() = list@pre.size() + 1
	 * @post list.contains(o)
	 */
	public void addOrder(Order o, ArrayList<MenuItem> list) {
		this.orders.put(o, list);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @pre list != null
	 * @post total > 0
	 */
	public Integer computePrice(ArrayList<MenuItem> list) {
		Integer total = 0; 
		for(MenuItem it : list) {
			assert it.getPrice() > 0 ;
			total += it.getPrice(); 
		}
		return total;
	}

	public boolean wellFormed() {
		for(MenuItem it : restaurantMenu)
		{
			if(it.getPrice() < 0) {
				return false;
			}
		}
		
		for(Order o: orders.keySet()) {
			if(o.getTableNb() < 0  || !(o.getTableNb() >= 1 && o.getTableNb() <= 55)) {
				return false;
			}
		}
		return true;
	}

}
