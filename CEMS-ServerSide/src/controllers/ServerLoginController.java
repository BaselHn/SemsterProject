package controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.ServerUI;

import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import dbConnector.mysqlConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.PasswordField;

public class ServerLoginController  implements Initializable{
	

	  @FXML
	    private AnchorPane usrNameLabel;

	    @FXML
	    private Label hostLabel;

	    @FXML
	    private Label passwordLabel;

	    @FXML
	    private Label schemeLebal;

	    @FXML
	    private Label portLabel;

	    @FXML
	    private Label srvrConLabel;

	    @FXML 
	    private Label dbConLbl;

	    @FXML
	    private Button connectButton;

	    @FXML
	    private Button disconnectButton;

	    @FXML
	    private TextArea srvrTxtArea;

	    @FXML
	    private Label srvrTxtAreaLbl;

	    @FXML
	    private TextField hostTxtfld;

	    @FXML
	    private TextField usrnameTxtFld;

	    @FXML
	    private TextField schmTxtFld;

	    @FXML
	    private TextField srvrPortTxtFld;

	    @FXML
	    private PasswordField passWrdFld;

	    @FXML
	    void handleConnectButtonClick(ActionEvent event) {
	    	
	    	String hostName    = hostTxtfld.getText();
	    	String usrName     = usrnameTxtFld.getText();
	    	String psWrdString = passWrdFld.getText();
	    	String dbScheme    = schmTxtFld.getText();
	    	String srvrPort    = srvrPortTxtFld.getText();
	    	
	    	String dbLink = "";
	    	
	    	boolean Error = false;
	    	
	    	/* Check all parameters validity*/
	    	if (hostName.trim().isEmpty())
	    	{
	    		sendMessageToLog("Please enter host name");
	    		Error |= true;
	    	}
	    	if(usrName.trim().isEmpty())
	    	{
	    		sendMessageToLog("Please Enter username");
	    		Error |= true;
	    	}
	    	if(psWrdString.trim().isEmpty())
	    	{
	    		sendMessageToLog("Please enter your password");
	    		Error |= true;
	    	}
	    	if(dbScheme.trim().isEmpty())
	    	{
	    		sendMessageToLog("Please enter data-base scheme");
	    		Error |= true;
	    	}
	    	if(srvrPort.trim().isEmpty())
	    	{
	    		sendMessageToLog("Please set server port");
	    		Error |= true;
	    	}
	    	
	    	
	    	// If all parameters are ok start server connection 
	    	if(!Error)
	    	{
	    		
	    		
	    		//Connet to server 
	    		try 
	    		{
					ServerUI.runServer(srvrPort);
				} catch (Exception e) 
	    		{
					sendMessageToLog("Error : port is taken or unavalable.\nserver did not connect");
				}
	    		
	    		//Connect to data base 
	    		try {
					Connection conn = null;
					
					dbLink = "jdbc:mysql://" + hostName +"/" +dbScheme + "?serverTimezone=IST";
					
					conn = mysqlConnection.getInstance(dbLink, usrName, psWrdString);
					
					if(conn != null)
					{
						sendMessageToLog("server connected to mySql");
					}
					
				} catch (Exception e) 
	    		{
						sendMessageToLog("Error : serverLoginC : con = null");
				}
	    		
			}
	    
	    }

	    @FXML
	    void handleDisconnetButtonClick(ActionEvent event) throws InterruptedException {
	    	
	    	sendMessageToLog("Server disconnected");
	    	System.exit(0);
	    }


	
	public void start(Stage  primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("/controllers/gui/ServerLoginView.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Server Configure");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
    public void initialize(URL location, ResourceBundle resources)
    {
		 //hostTxtfld.setPromptText("Enter Host name");
		 hostTxtfld.setText("localhost");
		 //usrnameTxtFld.setPromptText("Enter Username");
		 usrnameTxtFld.setText("root");
		 passWrdFld.setPromptText("Enter password");
		 //schmTxtFld.setPromptText("Enter DB scheme");
		 schmTxtFld.setText("cems_db");
		 //srvrPortTxtFld.setPromptText("Set server port");
		 srvrPortTxtFld.setText("5555");
    }
	
	 public void sendMessageToLog(String message)
	    {
	    	String timeStamp = new SimpleDateFormat("[hh:mm:ss]").format(Calendar.getInstance().getTime());
	    	String fullMessage = timeStamp + " " + message + System.lineSeparator();
	    	
	    	Platform.runLater(new Runnable()
	    	{
	    		@Override
	    		public void run()
	    		{
	    			srvrTxtArea.appendText(fullMessage);	
	    		}
	    	});
	    }
	 
	
}
