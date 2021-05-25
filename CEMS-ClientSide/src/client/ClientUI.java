package client;
import javafx.application.Application;

import javafx.stage.Stage;

import controllers.ClientLoginController;


public class ClientUI extends Application {
	public static ClientConnection chat; //only one instance

	public static void main( String args[] ) throws Exception
	   { 
		    launch(args);  
	   } // end main
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		 chat= new ClientConnection("localhost", 5555);
						  		
		 ClientLoginController aFrame = new ClientLoginController(); // create client frame
		 
		aFrame.start(primaryStage);
		
		
	}
	
	//public static void SendDataToServer(String str)
	
	
	
	
}
