package presentation;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import business.*;
import business.MenuItem;
import data.RestaurantSerializator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
public class AdministratorGUI extends JFrame{

	JFrame frame;
	private JLabel welAdmin = new JLabel("Welcome Administrator!");
	private DefaultTableModel menuModel;
	private JTable menuTable;
	private JButton addBPBtn = new JButton("Add Product");
	private JButton plusBtn = new JButton("+");
	private JButton addCPBtn =new JButton("Add Composite Product");
	private JButton editBtn = new JButton("Edit");
	private JButton delBTn=  new JButton("Delete selected:");
	private JButton backBtn = new JButton("Back");
	private JTextField itemTF = new JTextField();
	private JTextField ingrTF = new JTextField();
	private JTextField priceTF = new JTextField (); 
	
	public JComboBox<String> bpItems;
	private String[] itemList = {"Choose Base Product"};
	private JLabel itemLB = new JLabel("Item");
	private JLabel ingrLB = new JLabel("Ingredients");
	private JLabel priceLB = new JLabel("Price");
	RestaurantProcessing restProc;
	private MainView mv;
	private StringBuilder sb = new StringBuilder();
	public  ArrayList<String> menuCP = new ArrayList<String>();

	public AdministratorGUI(RestaurantProcessing restProc) {
		this.restProc = restProc;
		initialize();
		showMenu();
		populateComboBox();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setForeground(new Color(107, 142, 35));
		frame.setBounds(100, 100, 700, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JPanel top= new JPanel();
		top.setLayout(new FlowLayout());
		welAdmin.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		top.add(welAdmin);
		top.add(backBtn);
		frame.getContentPane().add(top, BorderLayout.PAGE_START);
	
		String[] menuCols = {"Item", "Ingredients", "Price"};
		Object[][] menuData = {};
		menuModel = new DefaultTableModel(menuData, menuCols);
		menuTable = new JTable(menuModel);
		menuTable.setPreferredScrollableViewportSize(new Dimension(700,300));
		JScrollPane jp = new JScrollPane(menuTable);
		
		frame.getContentPane().add(jp, BorderLayout.PAGE_END);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 1));
		p1.setSize(new Dimension(700, 350));
		JPanel p11 = new JPanel();
		p11.setLayout(new GridLayout(1, 3));
		p11.setPreferredSize(new Dimension(100, 50));
		addBPBtn.setBackground(new Color(135, 206, 250));
		p11.add(addBPBtn);
		editBtn.setBackground(new Color(107, 142, 35));
		p11.add(editBtn);
		p11.add(delBTn);
		p1.add(p11);
		JPanel p12= new JPanel();
		JPanel p121 = new JPanel();
		p121.setLayout(new GridLayout(1, 4));
		JPanel p122 = new JPanel();
		p122.setLayout(new GridLayout(1, 4));
		p12.setLayout(new GridLayout(2, 4));
		p121.add(itemLB);
		p121.add(ingrLB);
		
		p121.add(priceLB);
		p12.add(p121);
		p122.add(itemTF);
		p122.add(ingrTF);
		p122.add(priceTF);
		p12.add(p122);
		p1.add(p12);		
		
		JPanel p13 = new JPanel();
		p13.setLayout(new GridLayout(1,3));
		p13.add(addCPBtn);
		bpItems = new JComboBox<String>(itemList);
		p13.add(bpItems);
		p13.add(plusBtn);
		p1.add(p13);
		
		frame.getContentPane().add(p1, BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocationRelativeTo(null);

	}
	
	

	public String[][] getAllMenuItems(){
		if(restProc.getRestaurantMenu() != null) {
			ArrayList<MenuItem> menu = restProc.getRestaurantMenu();
			String[][] list = new String[menu.size()][3];
			int row = 0;
			for(MenuItem item : menu) {
					list[row][0] = item.getName().toString();
//					System.out.println(item.getName());
					list[row][1] = item.getDescription().toString();
//					System.out.println(item.getDescription());
					list[row][2] = item.getPrice().toString();
//					System.out.println(item.getPrice());
					row++;
			}
		return list;
		}else {
			System.out.println("empty menu");
		}
		return null;
	}
	
	public String getItemTF() {
		return itemTF.getText();
	}

	public void setItemTF(JTextField itemTF) {
		this.itemTF = itemTF;
	}

	public String getIngrTF() {
		return ingrTF.getText();
	}

	public void setIngrTF(JTextField ingrTF) {
		this.ingrTF = ingrTF;
	}

	public Integer getPriceTF() {
		return Integer.parseInt(priceTF.getText());
	}

	public void setPriceTF(JTextField priceTF) {
		this.priceTF = priceTF;
	}
	
	public void addMain(MainView mainview) {
		this.mv  = mainview;
	}
	

	public void showMenu() {
		
		if(getAllMenuItems() != null) {
			String[][] menu = getAllMenuItems();
			for(int i =0; i < menu.length ; i++)
				menuModel.addRow(menu[i]);
		}
	}
	
	public void removeMenuTable() {
		menuModel.setRowCount(0);
		menuTable.revalidate();
	}
	
	public void clearTFs() {
		itemTF.setText("");;
		ingrTF.setText("");
		priceTF.setText("");
	}
	
	public void addProductToBox(String name) {
		bpItems.addItem(name);
	}
	
	public void addNewMenuItemListener(ActionListener e) {
		this.addBPBtn.addActionListener(e);			
	}
	
	
	public void addDeleteMenuItemListener(ActionListener e) {
		this.delBTn.addActionListener(e) ;
	}
	
	public void addEditMenuListener(ActionListener e) {
		this.editBtn.addActionListener(e);			
	}
	
	
	
	public void enableBackBtnListener(ActionListener e) {
		this.backBtn.addActionListener(e);
	}
	
	public int addToComposition(String s) {
		this.menuCP.add(s);
		return 1;
	}
	
	public String getCompositeDescription() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ;i < menuCP.size(); i++) {
			sb.append(menuCP.get(i));
			if( i != (menuCP.size() - 1)) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	public void emptyCP() {
		this.menuCP.clear();
	}

	 public void enablePlusBtn(ActionListener e) {
		 this.plusBtn.addActionListener(e);
	 }

	 public ArrayList<MenuItem> getListofBP(String str) {
		 System.out.println("STRING :"+ str);
		 String[] baseItems = str.trim().split(", ");
		 ArrayList<MenuItem> newListBP = new ArrayList<MenuItem>();
		 ArrayList<MenuItem>  menu = restProc.getRestaurantMenu();
		 	 
		 for(int j = 0; j < menu.size(); j++){
			 for(int i = 0; i < baseItems.length; i++) {
				 if(menu.get(j).getName().equals(baseItems[i])) {
					 System.out.println("ADMIN: "+ menu.get(j).getName());
					 newListBP.add(menu.get(j));
				 }
			 }
		 }
		 
		 return newListBP;
	 }

	 public String[] getBaseNames() {
		 if(getAllMenuItems() != null) {
			 String[][] bps = getAllMenuItems();
			 String[] names = new String[bps.length];
			 for(int i = 0; i < bps.length; i++) {
				 names[i] = bps[i][0];
		 }
		 return names;
	}
		 return null;
 }
	 
	 public void populateComboBox() {
		 if(getBaseNames() != null) {
		 String[] names = getBaseNames();
		 for(int i = 0; i < names.length; i++) {
			 addProductToBox(names[i]);
		 	}
		 }else {
			 System.out.println("empty menu");
		 }
	 }
	 
	 public String getDescComposite(String[] s) {
		 StringBuilder desc = new StringBuilder();
		 for(int i = 0; i< s.length; i++) {
			 desc.append(s[i]);
			 if(i != s.length - 1) {
				 desc.append(",");
			 }
		 }
		return desc.toString();		 
	 }
	
	 public void enableCompositeListener(ActionListener e) {
		this.addCPBtn.addActionListener(e) ;
	}
	
	
}