package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import business.MenuItem;
import business.Order;

public class BillGenerator {
	
	public static void generate(Order o, ArrayList<MenuItem> items) {
		StringBuilder sb = new StringBuilder();
		sb.append("******* BILL ********\n");
		sb.append("Order Id : " +o.getOid() + "\n");
		sb.append("********************\n");
		sb.append("Products: \n");
		Integer total = 0;
		for(MenuItem i : items)
		{
			sb.append(i.getName() +":\t\t " + i.getPrice());
			total += i.getPrice();
			sb.append("\n");
		}
		sb.append("********** TOTAL : " + total);
		FileWriter fstream;
		try {
			fstream = new FileWriter("bill.txt", false);
			BufferedWriter info = new BufferedWriter(fstream);
			
			info.write(sb.toString());
			info.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
