package data;

import java.awt.Menu;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

import business.*;

public class RestaurantSerializator {

	private static String filename = "restaurant.ser";
	public RestaurantSerializator() {
		
	}
	
	public static Restaurant readFromFile()  {
		Restaurant res = null;
		FileInputStream file;
			try {
				file = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(file);
				res = (Restaurant) in.readObject();
				
				in.close();
				file.close();
				System.out.println("restaurant has been DESEARILIZED");
				return res;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(EOFException e) {
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	
	}
	
	
	public static void writeToFile(Restaurant r) {
		
		try {
			FileOutputStream fileOut= new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(r);
			
			out.close();
			fileOut.close();
			System.out.println("Restaurant has been SERIALIZED");
		} catch(EOFException e) {
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	

}
