package controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import client.ClientUI;
import entities.ExamResult;
import entities.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SolveExamController implements Initializable{

    @FXML
    private Button sbExmBtn;

    @FXML
    private Label ques1Lbl;
    
    @FXML
    private Label ques2Lbl;

    @FXML
    private Label ques1Txt;
    
    private static String exm;
    
    @FXML
    private RadioButton q1A1RB;
    
    @FXML
    private RadioButton q1A2RB;

    @FXML
    private RadioButton q1A3RB;

    @FXML
    private RadioButton q1A4RB;

    @FXML
    private Label ques2Txt;

    @FXML
    private RadioButton q2A1RB;

    @FXML
    private RadioButton q2A2RB;

    @FXML
    private RadioButton q2A3RB;

    @FXML
    private RadioButton q2A4RB;
    
    @FXML
    private Label instLbl;

    @FXML
    private Label InstTxt;
    
    @FXML
    private Label q1InstrLbl;

    @FXML
    private Label q2InstrLbl;

    @FXML
    private Label q1IntTxt;

    @FXML
    private Label q2IntTxt;

    private Question    quesOne;
    
    private Question    quesTwo;

    @FXML
    void handleSubmitExamButtonClick(ActionEvent event) {
    	
    	System.out.println(q1A1RB.isSelected());
    	
    	String q1ans = "";
    	String q2ans = "";
    	
    	Integer scoreInteger = 0;
    	
    	if(q1A1RB.isSelected())
    	 {
    		q1ans = "1";
    	 }
    	
    	if(q1A2RB.isSelected())
	   	 {
	   		q1ans = "2";
	   	 }
    	
    	if(q1A3RB.isSelected())
	   	 {
	   		q1ans = "3";
	   	 }
    	
    	 if(q1A4RB.isSelected())
	   	 {
	   		q1ans = "4";
	   	 }
    	 
    	 if(quesOne.getRightAnsw().equals(q1ans))
    	 {
    		 System.out.println("your answer is right");
    	 }
    	 
    	 
    	 if(q2A1RB.isSelected())
    	 {
    		q2ans = "1";
    	 }
    	
    	if(q2A2RB.isSelected())
	   	 {
	   		q2ans = "2";
	   	 }
    	
    	if(q2A3RB.isSelected())
	   	 {
	   		q2ans = "3";
	   	 }
    	
    	 if(q2A4RB.isSelected())
	   	 {
	   		q2ans = "4";
	   	 }
    	 
    	 if(quesOne.getRightAnsw().equals(q1ans))
    	 {
    		 System.out.println("your answer is right");
    	 }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		List<String> items = Arrays.asList(SolveExamController.exm.split("\\s*,\\s*"));
		
		String questions = items.get(1);
		
		InstTxt.setText(items.get(3));
		
		System.out.println("The question are" + questions);
		
		List<String> qs = Arrays.asList(questions.split("\\s*-\\s*"));
		
		System.out.println(qs);
		
		System.out.println(qs.size());
		
		for(int i = 0; i < qs.size(); i++)
		{
			List<String> q = Arrays.asList(qs.get(i).split("\\s*@\\s*"));
			
			String msgToSend = "GetQuestion," + q.get(0);
			
			ClientUI.chat.accept(msgToSend);
			
			while(!(ClientUI.chat.IsRespone()));    
			
			String msgRcvd = (String)ClientUI.chat.getMsg();
			
	    	System.out.println("You got message" + msgRcvd);
	    	
	    	
			if( i == 0)
			{
				List<String> qItms = Arrays.asList(msgRcvd.split("\\s*,\\s*"));
				
				quesOne = new Question(qItms.get(0), 
									   qItms.get(1), 
									   qItms.get(2), 
									   qItms.get(3), 
									   qItms.get(4), 
									   qItms.get(5), 
									   qItms.get(6), 
									   qItms.get(7), 
									   qItms.get(8));
				
				ques1Txt.setText(quesOne.getQuestion());
				q1A1RB.setText(quesOne.getAnsw_1());
				q1A2RB.setText(quesOne.getAnsw_2());
				q1A3RB.setText(quesOne.getAnsw_3());
				q1A4RB.setText(quesOne.getAnsw_4());
				q1IntTxt.setText(quesOne.getInstruction());
			}
			
			if( i == 1)
			{
				List<String> qItms = Arrays.asList(msgRcvd.split("\\s*,\\s*"));
				
				quesTwo = new Question(qItms.get(0), 
									   qItms.get(1), 
									   qItms.get(2), 
									   qItms.get(3), 
									   qItms.get(4), 
									   qItms.get(5), 
									   qItms.get(6), 
									   qItms.get(7), 
									   qItms.get(8));
				
				ques2Txt.setText(quesTwo.getQuestion());
				q2A1RB.setText(quesTwo.getAnsw_1());
				q2A2RB.setText(quesTwo.getAnsw_2());
				q2A3RB.setText(quesTwo.getAnsw_3());
				q2A4RB.setText(quesTwo.getAnsw_4());
				q2IntTxt.setText(quesTwo.getInstruction());
			}
			
		}
		
		
		
	}
	
	public void openStartSolvingForm(String exam)
	{
		try {
			System.out.println(exam);
			SolveExamController.exm = exam;
			Pane root = FXMLLoader.load(getClass().getResource("gui/solveExamFx.fxml"));
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
