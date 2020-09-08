package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import business.Restaurant;
import business.RestaurantProcessing;

public class ChefGUI extends JFrame implements Observer {

	public ChefGUI()
	{
		this.setSize(400,200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public  void update(Observable obs, Object arg1) {
		JOptionPane.showMessageDialog(null, "Incoming Order!");	
	}

}
