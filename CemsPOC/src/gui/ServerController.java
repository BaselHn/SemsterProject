package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Server.EchoServer;
import Server.ServerUI;

public class ServerController implements Initializable {
	
	@FXML 
	private Label ipLabel;

	@FXML 
	private Label hostLabel;

	@FXML 
	private Label stsLabel;

	@FXML 
    private TextField ipTxt;

	@FXML 
	private TextField hostTxt;

	@FXML
    private TextArea stsTxt;
	
	@FXML
	private Button updtBtn;

	@FXML
	void UpdateData(ActionEvent event) {
		    if(EchoServer.clientIsConnected)
		    {
		    	ipTxt.setText(EchoServer.IpStr);
		    	hostTxt.setText(EchoServer.HostName);
		    	stsTxt.setText("Connected");
		    }
	 }
	    
	    
     public  void setStatus(String message)
	 {
		System.out.println("Got here" + message);
		
		String timeStamp = new SimpleDateFormat("[hh:mm:ss]").format(Calendar.getInstance().getTime());
    	String fullMessage = timeStamp + " " + message + System.lineSeparator();
		
		Platform.runLater(new Runnable()
    	{
    		@Override
    		public void run()
    		{
    			stsTxt.appendText(fullMessage);
    		}
    	});
	}
     
     public  void clientInformation(String status)
     {
    	 setStatus(status);
     }

	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Server.fxml"));
				
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("/gui/Server.css").toExternalForm());
		
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void getExitBtn(ActionEvent event) throws Exception {
		System.out.println("exit Academic Tool");
		System.exit(0);			
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ipTxt.setText("0.0.0.0");
		hostTxt.setText("    ");
		stsTxt.setText("Not connected");
		ServerUI.runServer("5555");
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems_db?serverTimezone=IST","root","basel@sql");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ipTxt.setText(EchoServer.IpStr);
	}
	
	
	public static Connection  getConnection() throws SQLException
	{
	  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cems_db?serverTimezone=IST","root","basel@sql");
	  System.out.println("SQL connection succeed");
	  return conn;
	}

}