package controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import client.ClientUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ManagerMainViewController {
	

	private static Timeline oneSecondWonder; //Used to measure the time of solving
	
	private static Stage stage;
	
	private static String exmId;
	
	private static String newTime;
	 
    @FXML
    private TextField tChTxtFld ;

    @FXML
    private Button aprvChngBtn;

    @FXML
    private TextField arvpTxtFld;
    
    

    @FXML
    void handleAprroveButtonClick(ActionEvent event) {
    	
    	if(arvpTxtFld.getText().equals("Yes"))
    	{
	    	String msgToSend = "AprroveSetTime," + exmId + "/" + newTime;
	    	
	    	ClientUI.chat.accept(msgToSend);
			
			while(!(ClientUI.chat.IsRespone()));
    	}

    }
    
    
    public void openManagerForm() throws IOException
    {
    	
    	Pane root = FXMLLoader.load(getClass().getResource("gui/ManagerMainViewFx.fxml"));//build the gui
    	
    	Scene scene = new Scene(root);
    	
    	ManagerMainViewController.stage = new Stage();
    	ManagerMainViewController.stage.setScene(scene);
    	ManagerMainViewController.stage.show();
		
    	ManagerMainViewController.oneSecondWonder = new Timeline(
									new KeyFrame(Duration.seconds(10), 
									new EventHandler<ActionEvent>() {
						
									@Override
									public void handle(ActionEvent event) {
										
										ClientUI.chat.accept("NeedTimeAproved");
										
										while(!(ClientUI.chat.IsRespone()));
								        
								        String msgRcvd = (String)ClientUI.chat.getMsg();
								       
								        System.out.println(msgRcvd);
								       
								        if (!msgRcvd.equals("**"))
								        {
								        	System.out.println(msgRcvd);
								        	List <String> msgList = Arrays.asList(msgRcvd.split("\\s*,\\s*"));
								        	
								        	exmId = msgList.get(0);
								        	newTime = msgList.get(2);
								        	
								        	ClientUI.chat.accept("SetNeedTimeAproved," + exmId);
										        
										    while(!(ClientUI.chat.IsRespone()));
								        	 
								        	
								        	showMessage(msgList.get(1) + " The requested time is:" + msgList.get(2));  
								        }
										
									}
								}));
    	
    	ManagerMainViewController.oneSecondWonder.setCycleCount(Timeline.INDEFINITE);
    	ManagerMainViewController.oneSecondWonder.play();
    }
    
    
    private void showMessage(String message)
    {
    	System.out.println(message);
    	 TextField txtFld =	(TextField)stage.getScene().getRoot().lookup("#tChTxtFld");
    	 txtFld.setText(message);
    }

}

