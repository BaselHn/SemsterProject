package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import client.ClientUI;
import entities.ExamResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StudentMainViewController {

	public static String exmCode;
    @FXML
    private Label stdntFrmTlt;
    
    @FXML
    private Button myMarksBtn;

    @FXML
    private TextField examCodeTxtFld;
    
    @FXML
    private TextField idTxtFld;

    @FXML
    private Button strtExnBtn;
    
    @FXML
    private Button StrtExmMnlBtn;
     
    
    ObservableList<ExamResult> oblist;
    
    @FXML
    void handleMyMarksBtnClick(ActionEvent event) {
    	
    	oblist = FXCollections.observableArrayList();
    	
    	String id = ClientLoginController.loggedUser.personId;
    	
    	ClientUI.chat.accept("getMarks" + "," + id);
    	
    	while(!(ClientUI.chat.IsRespone())); 
    	
    	//String msgRcvd = ClientUI.chat.getMsg();
    	String rcv = (String)ClientUI.chat.getMsg();
    	System.out.println("You got message" + rcv);
    	
    	List <String> msgList = Arrays.asList(rcv.split("\\s*/\\s*"));
    	
    	for(int i = 0; i < msgList.size(); i++)
    	{
    		List<String> items = Arrays.asList(msgList.get(i).split("\\s*,\\s*"));
    		
    		ExamResult exm = new ExamResult(items.get(0), items.get(1),  items.get(2));
    		
    		oblist.add(exm);	
    	}
    	
    	System.out.println(oblist);
    	
    	
		StudentMarksTableController ctrl = new StudentMarksTableController();
		ctrl.showTable(oblist);
    	
    	
    }

    @FXML
    void handleStartExmBtn(ActionEvent event) {
       
    	String msgToSend = "GetExam,";
    	
    	msgToSend += examCodeTxtFld.getText();
    	
    	ClientUI.chat.accept(msgToSend);
    	
    	while(!(ClientUI.chat.IsRespone())); 
    	
    	String rcv = (String)ClientUI.chat.getMsg();
    	System.out.println("You got message" + rcv);
    	
    	List <String> msgList = Arrays.asList(rcv.split("\\s*/\\s*"));
    	
    	for(int i = 0; i < msgList.size(); i++)
    	{
    		List<String> items = Arrays.asList(msgList.get(i).split("\\s*,\\s*"));
    		
    		System.out.println(items.get(0));
    		System.out.println(items.get(1));
    		System.out.println(items.get(2));
    		System.out.println(items.get(3));
    		System.out.println(items.get(4));
    		System.out.println(items.get(5));
    		System.out.println(items.get(6));
    		System.out.println(items.get(7));
    	}
    	
    	SolveExamController ctrl = new SolveExamController();
    	
    	ctrl.openStartSolvingForm(rcv);
    	
    }
    
    @FXML
    void handleStatExamManualClick(ActionEvent event) {
    	
    	//((Node) event.getSource()).getScene().getWindow().hide(); 
    	Pane root;
		try {
			exmCode = examCodeTxtFld.getText();
			root = FXMLLoader.load(getClass().getResource("gui/manualExamFx.fxml"));
			Scene scene = new Scene(root);
	   		Stage stage = new Stage();
	   		stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//build the gui
    	

    }
}