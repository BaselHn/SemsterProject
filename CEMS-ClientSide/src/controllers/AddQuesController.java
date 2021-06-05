package controllers;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddQuesController {

	  @FXML
	    private TextField quesTxtFld;

	    @FXML
	    private TextField answ1TxtFld;

	    @FXML
	    private TextField answ2TxtFld;

	    @FXML
	    private TextField answ3TxtFld;

	    @FXML
	    private Button submtButn;

	    @FXML
	    private TextField instrctTxtFld;

	    @FXML
	    private TextField answ4TxtFld;

	    @FXML
	    private TextField rtAnsTxtFld;

	    @FXML
	    private TextField tchTxtFld;
	    
	    @FXML
	    private TextField quesIdTxtFld;
	    
    @FXML
    void handleSubmitButtonClick(ActionEvent event) {
          
    	String msgToSend = "AddQuestion,";
    	
    	msgToSend += quesIdTxtFld.getText()  + "/";
    	msgToSend += quesTxtFld.getText()    + "/";
    	msgToSend += instrctTxtFld.getText() + "/";
    	msgToSend += answ1TxtFld.getText() 	 + "/";
    	msgToSend += answ2TxtFld.getText()   + "/";
    	msgToSend += answ3TxtFld.getText()   + "/";
    	msgToSend += answ4TxtFld.getText()   + "/";
    	msgToSend += rtAnsTxtFld.getText()   + "/";
    	msgToSend += tchTxtFld.getText();
    	
    	ClientUI.chat.accept(msgToSend);
    	while(!(ClientUI.chat.IsRespone()));    
    	
    	String msgRcvd = (String)ClientUI.chat.getMsg();
    	System.out.println("You got message" + msgRcvd);
    	
    	
    }
    
    public void openAddQuestionForm()
    {
    	try {
			Pane root = FXMLLoader.load(getClass().getResource("gui/addQuesFx.fxml"));
			Scene scene = new Scene(root);
	   		Stage stage = new Stage();
	   		stage.setScene(scene);
			stage.show();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    
}
