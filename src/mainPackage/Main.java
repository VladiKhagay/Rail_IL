/**
 * 
 * @author Omri Hadad & Vladi Khagay
 * 
 */
package mainPackage;

import app.RailApp;


public class Main {

	public static void main(String[] args) {
		RailApp instance = RailApp.getInstance();
		instance.start();
	}

}
