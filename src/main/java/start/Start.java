package start;


import business.BaseProduct;
import business.MenuItem;
import business.Restaurant;
import business.RestaurantProcessing;
import data.EmailSender;
import data.RestaurantSerializator;
import presentation.AdministratorGUI;
import presentation.ChefGUI;
import presentation.Controller;
import presentation.MainView;
import presentation.WaiterGUI;

public class Start {
	
	public static void main(String args[]) {
	
		RestaurantProcessing restProc = new Restaurant();
		restProc.insertMenuItem(new BaseProduct("sashimi", "salmon", 23));
		restProc.insertMenuItem(new BaseProduct("takoyaki", "squid flour balls", 12));
		RestaurantSerializator.writeToFile((Restaurant) restProc);
		Restaurant rest = RestaurantSerializator.readFromFile();
		AdministratorGUI admin = new AdministratorGUI(restProc);
		WaiterGUI servant = new WaiterGUI(restProc);
		MainView main = new MainView(admin, servant);
		servant.addMain(main);
		admin.addMain(main);
		ChefGUI chef = new ChefGUI();
		//restProc.addObserver(chef);
		Controller control = new Controller(admin, servant, restProc, main);
		new EmailSender("bill.txt");
		main.getFrame().setVisible(true);
		
		restProc.addObserver(chef);
		
	}
} 