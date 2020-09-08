package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainView {

	private JFrame frame;
	private JLabel welcomeLB =new JLabel("Yokoso! Choose your character");
//	private ChefGUI chef;
	private AdministratorGUI admin;
	private WaiterGUI waiter;
	private JButton adminBtn = new JButton("Administrator");
	private JButton servantBtn = new JButton("Layman");
	
	public MainView () {
	
	}
	public MainView(AdministratorGUI adminGui,  WaiterGUI waiterGui) {
		
		initialize();
		this.admin = adminGui;
		//this.chef = chefGui;
		this.waiter = waiterGui;
	
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Yokoso! Choose your character!");
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 19));
		panel.add(lblNewLabel);
		adminBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
		adminBtn.setVerticalAlignment(SwingConstants.TOP);
		adminBtn.setBackground(Color.LIGHT_GRAY);
		adminBtn.setFont(new Font("Arial", Font.BOLD, 14));
		adminBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		frame.getContentPane().add(adminBtn);
		
		servantBtn.setFont(new Font("Arial", Font.BOLD, 14));
		frame.getContentPane().add(servantBtn);
		
	}
	
	
	public void addAdminPanelListener( ActionListener e) {
		this.adminBtn.addActionListener(e);
	}
	
	public void addServantListener(ActionListener e) {
		this.servantBtn.addActionListener(e);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
}
