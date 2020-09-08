package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import business.MenuItem;
import business.Order;
import business.Restaurant;
import business.RestaurantProcessing;

import java.util.*;
public class WaiterGUI extends JFrame{

	 JFrame frame;
	private JLabel welAdmin = new JLabel("Welcome Servant!");
	private DefaultTableModel orderModel;
	private JTable orderTable;
	private JButton newOrderBtn = new JButton("New Order");
	//private JButton computeBillBtn = new JButton("Compute Bill");
	private JButton plusBtn = new JButton(" + ");
	private JButton backBtn = new JButton("Back");
	private JLabel tableLB = new JLabel("Table:");
	private JTextField tableTF = new JTextField();
	private MainView mainView;
	private ChefGUI chef ;
	private JComboBox<String> orderBox;
	private ArrayList<String> ordered = new ArrayList<String>();
	private String[] orderList = {"Choose food"}; 
	private RestaurantProcessing restProc;

	public WaiterGUI(RestaurantProcessing restProc) {
		this.restProc = restProc;
		initialize();
		populateComboBox();
		showOrders();
	}

	public void addChef(ChefGUI chef) {
		this.chef = chef;
	}
	public JFrame getWaiterFrame() {
		return this.frame;
	}
	private void initialize() {
		frame = new JFrame();
		frame.setForeground(new Color(107, 142, 35));
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel top= new JPanel();
		top.setLayout(new FlowLayout());
		welAdmin.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		top.add(welAdmin);
		top.add(backBtn);
		frame.getContentPane().add(top, BorderLayout.PAGE_START);
	
		String[] orderCols = {"Order ID", "Date", "Table", "Items", "Total"};
		Object[][] orderData = {};
		orderModel = new DefaultTableModel(orderData, orderCols);
		orderTable = new JTable(orderModel);
		orderTable.setPreferredScrollableViewportSize(new Dimension(700,300));
		JScrollPane jp = new JScrollPane(orderTable);
		
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(2, 1));
		JPanel p11 = new JPanel();
		p11.setLayout(new GridLayout(1,0));
		newOrderBtn.setBackground(new Color(0, 206, 209));
		newOrderBtn.setFont(new Font("Arial Black", Font.BOLD, 14));
		p11.add(newOrderBtn);
		p1.add(p11);
		JPanel p12= new JPanel();
		p12.setLayout(new GridLayout(3, 2));
		JPanel p121 = new JPanel();
		p121.setLayout(new GridLayout(1, 2));
		JPanel p122 = new JPanel();
		p122.setLayout(new GridLayout(1,1));
		
		JPanel p123 = new JPanel();
		p123.setLayout(new GridLayout(1, 2));
		p123.add(tableLB);
		p123.add(tableTF);
		
		orderBox = new JComboBox(orderList);
		p121.add(orderBox);
		p121.add(plusBtn);
		p12.add(p121);
		p12.add(p122);
		p12.add(p123);
		p1.add(p12);	

		frame.getContentPane().add(jp, BorderLayout.PAGE_END);
		frame.getContentPane().add(p1, BorderLayout.CENTER);
		frame.pack();
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public String getTableNb() {
		return tableTF.getText();
	}
	
	public void addMain(MainView mv) {
		this.mainView = mv;
	}
	public void addBackListener(ActionListener e) {
		this.backBtn.addActionListener(e) ;
	}
	
	public void addPlusListener(ActionListener e) {
		this.plusBtn.addActionListener(e);
	}
	
	public void addNewOrderListener(ActionListener e) {
		this.newOrderBtn.addActionListener(e);
	}
	
	public MenuItem getMenuItem(String item) {
		MenuItem m = null ;
		ArrayList<MenuItem> ls = restProc.getRestaurantMenu();
		
		for(MenuItem it : ls) {
			if(it.getName().contains(item)) {
				m = it;
			}
		}
		return m;
	}
	
	
	public void addProductToBox(String s) {
		this.orderBox.addItem(s);
	}
	
	public String getItemsInOrder(ArrayList<MenuItem> ls) {
		StringBuilder sb = new StringBuilder();
		for(int i= 0; i < ls.size(); i++) {
			sb.append(ls.get(i).getName());
			if(i != ls.size() - 1) {
				sb.append(", ");
			}
		}
		return null;
	}
	
	public String getDescOfOrder(ArrayList<MenuItem> list) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).getName());
			if(i != list.size() - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	public String[][] getAllOrderItems(){
		if(restProc.getOrders() != null) {
			Map<Order,ArrayList<MenuItem>> orders = restProc.getOrders();
			String[][] list = new String[orders.size()][5];
			int row = 0;
			for(Order key : orders.keySet()) {	
				list[row][0] = key.getOid().toString();
				list[row][1] = key.getDate().toString();
				list[row][2] = key.getTableNb().toString();
				list[row][3] = getDescOfOrder(orders.get(key));
				list[row][4] = restProc.computePrice(orders.get(key)).toString();
				row++;			
			}
		return list;
		}else {
			System.out.println("empty orders");
		}
		return null;
	}
	
	public void showOrders() {
		String[][] all = getAllOrderItems();
		for(int i =0; i < all.length ; i++) {
			orderModel.addRow(all[i]);
		}
	}
	
	public void removeOrderTable() {
		orderModel.setRowCount(0);
		orderTable.revalidate();
	}
	public void clearTF() {
		tableTF.setText("");
	}
	public void clearOrderList() {
		this.ordered.clear();
	}
	
	public String[] getBaseNames(ArrayList<MenuItem> menu) {
		String[] names = new String[menu.size()];
		for( int i = 0; i< menu.size(); i++ ) {
			names[i] = menu.get(i).getName();
		}
		return names;
	}
	 public void populateComboBox() {
		ArrayList<MenuItem> menu = restProc.getRestaurantMenu();
		if(getBaseNames(menu) != null) {
		 String[] names = getBaseNames(menu);
		 for(int i = 0; i < names.length; i++) {
			 addProductToBox(names[i]);
		 	}
		 }else {
			 System.out.println("empty menu");
		 }
	 }
	 
	 public JComboBox<String> getOrderBox() {
			return orderBox;
		}

		public void setOrderBox(JComboBox<String> orderBox) {
			this.orderBox = orderBox;
		}
		
		public int addToOrder(String item) {
			this.ordered.add(item);
			return 1;
		}

		public ArrayList<MenuItem> getListOfOrderedItems() {
			ArrayList<MenuItem> ord = new ArrayList<MenuItem>();
			ArrayList<MenuItem> rest = restProc.getRestaurantMenu();
			for(MenuItem it: rest) {
				System.out.println("ORDER IT: "+ it.getName());
			}
			
			for(int i = 0; i < rest.size(); i++) {
				for(int j = 0; j < this.ordered.size(); j++){
					if(rest.get(i).getName().contains(ordered.get(j)))
						
						ord.add(rest.get(i));
				}
			}
			for(MenuItem f : ord) {
				System.out.println("WAITER ADDED : " + f.getName());
			}
				
 			return ord;
		}
}