package client;
import javafx.application.Application;

import javafx.stage.Stage;
import gui.ClientController;

public class ClientUI extends Application {
	public static ClientConnection chat; //only one instance

	public static void main( String args[] ) throws Exception
	   { 
		    launch(args);  
	   } // end main
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		 chat= new ClientConnection("localhost", 5555);
						  		
		 ClientController aFrame = new ClientController(); // create client frame
		 
		aFrame.start(primaryStage);
	}
	
	
}
