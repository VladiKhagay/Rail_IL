/**
 * 
 * @author Omri Hadad & Vladi Khagay
 * 
 */
package mainPackage;

import java.io.FileNotFoundException;
import java.io.IOException;

import app.RailApp;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		RailApp instance = RailApp.getInstance();
		instance.workerStart();
	}

}
