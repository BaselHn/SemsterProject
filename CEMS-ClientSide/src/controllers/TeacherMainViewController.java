package controllers;

import java.util.Arrays;
import java.util.List;

import client.ClientUI;
import entities.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TeacherMainViewController {

    @FXML
    private Label titleLbl;

    @FXML
    private Button addQuesBtn;

    @FXML
    private Button buildTstBtn;

    @FXML
    private ImageView imgViemTech;
    
    ObservableList<Question> oblist;

    @FXML
    void handleAddQuesBtnClick(ActionEvent event) {
    	
        AddQuesController ctrlAddQuesController = new AddQuesController();
        
        ctrlAddQuesController.openAddQuestionForm();
        
        
    }

    @FXML
    void handleBuildTstBtnClick(ActionEvent event) {
    	
    	BuildExamController ctrlBuildExamController = new BuildExamController();
    	
    	oblist = FXCollections.observableArrayList();
    	
    	String name = ClientLoginController.loggedUser.personName;
    	
    	ClientUI.chat.accept("getQuestions" + "," + name);
    	
    	while(!(ClientUI.chat.IsRespone())); 
    	
    	String rcv = (String)ClientUI.chat.getMsg();
    	System.out.println("You got message" + rcv);
    	
    	List<String> msgList = Arrays.asList(rcv.split("\\s*/\\s*"));
    	
    	for(int i = 0; i < msgList.size(); i++)
    	{
    		List<String> items = Arrays.asList(msgList.get(i).split("\\s*,\\s*"));
    		
    		Question ques = new Question(items.get(0), 
    									 items.get(1), 
    									 items.get(2), 
    									 items.get(3), 
    									 items.get(4), 
    									 items.get(5), 
    									 items.get(6), 
    									 items.get(7), 
    									 items.get(8));
    		
    		oblist.add(ques);	
    	}
        
    	ctrlBuildExamController.openBuildExamForm(oblist);
    }

}