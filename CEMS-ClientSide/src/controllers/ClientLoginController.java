package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.cj.protocol.Message;

import client.ClientUI;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class ClientLoginController implements Initializable{

	@FXML
	private Label signInLbl;
	
	@FXML
	private TextField usrnameTxtFld;

    @FXML
    private Label usrnameLbl;

    @FXML
    private Label pswLbl;

    @FXML
    private PasswordField pswrdFld;

    @FXML
    private Button loginStdBtn;

    @FXML
    private Button loginTchBtn;

    @FXML
    void handleLoginStudentButton(ActionEvent event) {
    	System.out.println("Sending to server side");
    	
    	String usrname = usrnameTxtFld.getText();
    	
    	String pswrd   = pswrdFld.getText();
    	
    	ClientUI.chat.accept(usrname + "/" + pswrd);
    	
    	while(!(ClientUI.chat.IsRespone()));    	
    	System.out.println("You got message" + ClientUI.chat.getMsg());
    	
    	try
		{
			((Node) event.getSource()).getScene().getWindow().hide(); 
	    	Pane root = FXMLLoader.load(getClass().getResource("gui/StudentMainViewFx.fxml"));//build the gui
	    	Scene scene = new Scene(root);
	   		Stage stage = new Stage();
	   		stage.setScene(scene);
			stage.show();
	 	}
	    catch(Exception e)
		  {
	       	e.printStackTrace();
		  }		
    	
    	//while()

    }

    @FXML
    void handleLoginTeacherBtn(ActionEvent event) {
    	
    	String usrname = usrnameTxtFld.getText();
    	String pswrd   = pswrdFld.getText();
    	
    	ClientUI.chat.accept(usrname + "/" + pswrd);
    	while(!(ClientUI.chat.IsRespone()));    	
    	System.out.println("You got message" + ClientUI.chat.getMsg());
    	
    	try
		{
			((Node) event.getSource()).getScene().getWindow().hide(); 
	    	Pane root = FXMLLoader.load(getClass().getResource("gui/TeacherMainViewFx.fxml"));//build the gui
	    	Scene scene = new Scene(root);
	   		Stage stage = new Stage();
	   		stage.setScene(scene);
			stage.show();
	 	}
	    catch(Exception e)
		  {
	       	e.printStackTrace();
		  }		
    	
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
	public void start(Stage  primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("/controllers/gui/ClientLoginFx.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("CEMS-System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
