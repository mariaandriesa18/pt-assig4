package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import business.BaseProduct;
import business.CompositeProduct;
import business.MenuItem;
import business.Order;
import business.Restaurant;
import business.RestaurantProcessing;
import data.BillGenerator;
import data.RestaurantSerializator;
import presentation.Controller.OrderListener;
import presentation.Controller.PlusOrderListener;
import presentation.Controller.ServantListener;

public class Controller {

	private AdministratorGUI admin;
	private WaiterGUI waiter;

	private RestaurantProcessing restProc ;
	private MainView mv;
	
	private ChefGUI chef;
	
	public Controller(AdministratorGUI admin, WaiterGUI waiter,RestaurantProcessing rest, MainView mv) {
		this.admin = admin;
		this.waiter = waiter;
		
		this.restProc = rest;
		this.mv = mv;
		admin.addNewMenuItemListener(new AddBPListener());
		admin.addDeleteMenuItemListener(new DeleteMenuItem());
		admin.addEditMenuListener(new EditMenuItem());
		admin.enableBackBtnListener(new AdminBackListener());
		admin.enableCompositeListener(new CompositeProductListener());
		admin.enablePlusBtn(new PlusListener());
		waiter.addBackListener( new WaiterBackListener());
		waiter.addNewOrderListener(new OrderListener());
		waiter.addPlusListener(new PlusOrderListener());
		mv.addAdminPanelListener(new AdminListener());
		mv.addServantListener(new ServantListener());
	
	}
	
	
	
	public class AddBPListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
				String prodName = admin.getItemTF();
				String desc = admin.getIngrTF();
				Integer price = admin.getPriceTF();
				MenuItem item = new BaseProduct(prodName, desc, price);
				admin.addProductToBox(prodName);
				waiter.addProductToBox(prodName);
				restProc.insertMenuItem(item);
				RestaurantSerializator.writeToFile((Restaurant) restProc);	
			
			admin.removeMenuTable();
			admin.showMenu();
			admin.clearTFs();
			}
		};
		
	
	public class DeleteMenuItem implements ActionListener {

			public void actionPerformed(ActionEvent e) {
					MenuItem menuItem = new BaseProduct();
					restProc.deleteMenuItem(menuItem);
					RestaurantSerializator.writeToFile((Restaurant) restProc);
				}
		};
	

	public class EditMenuItem implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				String prodName = admin.getItemTF();
				String desc = admin.getIngrTF();
				Integer price = admin.getPriceTF();
				restProc.editMenuItem(new BaseProduct(prodName, desc, price));
				RestaurantSerializator.writeToFile((Restaurant) restProc);
			}

		}
	

	public class AdminBackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				mv.getFrame().setVisible(true);
				admin.frame.setVisible(false);
		}

	}

	public class PlusListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String item = (String) admin.bpItems.getSelectedItem();
				if(admin.addToComposition(item) == 1) {
					JOptionPane.showMessageDialog(null, "Added succesfully");
				}
				
			}

	}
	
	public class CompositeProductListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!admin.getItemTF().contentEquals("") && !admin.getIngrTF().contentEquals("")) {		
				String cpName = admin.getItemTF();
				admin.addProductToBox(cpName);
				waiter.addProductToBox(cpName);
				String comp = admin.getCompositeDescription();
				System.out.println("DESCRIPTION : "+ comp);
				//String desc = getDescComposite(getBaseNames());
				ArrayList<MenuItem> ls = admin.getListofBP(comp);
				for(MenuItem m : ls) {
					System.out.println("ITEM : "+ m.getName() + " " +m.getPrice());
				}
				restProc.insertMenuItem(new CompositeProduct(cpName,comp, ls));
				RestaurantSerializator.writeToFile((Restaurant)restProc);
				
			}else {
				JOptionPane.showMessageDialog(null, "Don't insert ingredients for Composite products");	
				}
			admin.emptyCP();
			admin.removeMenuTable();
			admin.showMenu();
			admin.clearTFs();
		}
	}



	public class AdminListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			mv.getFrame().setVisible(false);
			admin.frame.setVisible(true);
		}

	}
	
	public class ServantListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			mv.getFrame().setVisible(false);
			waiter.frame.setVisible(true);
		}
	}
	

	public class WaiterBackListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			mv.getFrame().setVisible(true);
			waiter.frame.setVisible(false);
		}

	}
	
	public class PlusOrderListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String item = (String) waiter.getOrderBox().getSelectedItem();
			if(waiter.addToOrder(item) == 1) {
				JOptionPane.showMessageDialog(null, "Added succesfully");
			}			
		}
	}


	public class OrderListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(waiter.getTableNb().contentEquals("")) {
				JOptionPane.showMessageDialog(null, "Please insert table number");
			}else 
			{
				Integer table =Integer.parseInt(waiter.getTableNb());
				Order o = new Order(table);
				ArrayList<MenuItem> list = waiter.getListOfOrderedItems();
				restProc.addOrder(o, list);
				BillGenerator.generate(o, list);
				RestaurantSerializator.writeToFile((Restaurant)restProc);
				waiter.removeOrderTable();
				waiter.showOrders();	
			}
			waiter.clearTF();
			waiter.clearOrderList();
			chef = new ChefGUI();
			chef.update(null, null);
		}
	}

	
}
