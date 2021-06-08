package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


import client.ChatClient;
import client.ClientUI;
import entities.Question;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    
    private static boolean flag = false;;
    
    private static Timeline fiveSecondsWonder;
    
    private static Timeline oneSecondWonder; //Used to measure the time of solving
    
    private static PauseTransition delay;
    
    private static Integer solvingTime = 0;
    
    private static Integer  examTime = 0;
    
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

    private static Question    quesOne;
    
    private static Question    quesTwo;
    
    private static Integer countr = 1;
    
    private static Stage stage;

    @FXML
    void handleSubmitExamButtonClick(ActionEvent event) {
    	
System.out.println(q1A1RB.isSelected());
    	
    	String q1ans = "";
    	String q2ans = "";
    	
    	Integer score = 0;
    	
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
    		 
    		 score += Integer.parseInt(quesOne.getScore());
    	 }
    	 
    	 if(quesTwo.getRightAnsw().equals(q2ans))
    	 {
    		 score += Integer.parseInt(quesTwo.getScore());
    	 }
    	 
    	 String msgToSend ="SubmitExam,";
    	 
    	 msgToSend +=ClientLoginController.loggedUser.personId + "/";
    	 List<String> items = Arrays.asList(SolveExamController.exm.split("\\s*,\\s*"));
    	 
    	 msgToSend += items.get(0) + "/";
    	 
    	 msgToSend += quesOne.getID() + "@" + q1ans + "-";
    	 msgToSend += quesTwo.getID() + "@" + q2ans + "/";
    	 
    	
    	 msgToSend += items.get(6) + "/";
    	 msgToSend += items.get(7) + "/";
    	 msgToSend += score + "/";
    	 msgToSend += "False" + "/";
    	 msgToSend += "None" + "/";
    	 msgToSend += SolveExamController.solvingTime;
    	 
    	 ClientUI.chat.accept(msgToSend);
			
		while(!(ClientUI.chat.IsRespone()));    
			
		String msgRcvd = (String)ClientUI.chat.getMsg();
			
	    System.out.println("You got message" + msgRcvd);
	    
	    SolveExamController.fiveSecondsWonder.stop();
		SolveExamController.oneSecondWonder.stop();
		SolveExamController.delay.stop();
	    stage.close();
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
			
			String score = q.get(1);
			
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
									   qItms.get(8),
									   score);
				
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
									   qItms.get(8),
									   score);
				
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
			List<String> items = Arrays.asList(SolveExamController.exm.split("\\s*,\\s*"));
			
			SolveExamController.examTime = Integer.parseInt(items.get(2));
			
			String  exmID = items.get(0);
			
			
			Pane root = FXMLLoader.load(getClass().getResource("gui/solveExamFx.fxml"));
			Scene scene = new Scene(root);
	   		stage = new Stage();
	   		
	   		SolveExamController.fiveSecondsWonder = new Timeline(
	                 new KeyFrame(Duration.seconds(5), 
	                 new EventHandler<ActionEvent>() {

			         @Override
			    	public void handle(ActionEvent event) {
			        //System.out.println("this is called every 5 seconds on UI thread");
			       
			        
			        ClientUI.chat.accept("IsExamLocked," + exmID);
					
					while(!(ClientUI.chat.IsRespone()));
			        
			        String msgRcvd = (String)ClientUI.chat.getMsg();
			        
			       
			        if (msgRcvd.equals("ExamLocked"))
			        {
			        	System.out.println("Bingo");
			        	stage.close();
			        	SolveExamController.flag = true;
			        	StopTimeLine();
					   
			        }
			        
			         }
	                 }));
	   		
	   		SolveExamController.oneSecondWonder = new Timeline(
	   										new KeyFrame(Duration.seconds(1), 
	   										new EventHandler<ActionEvent>() {
	   											
	   											@Override
	   											public void handle(ActionEvent event) {
	   												
		   											SolveExamController.solvingTime++;
		   											
		   											ClientUI.chat.accept("IsTimeUpdated," + exmID);
		   											
		   											while(!(ClientUI.chat.IsRespone()));
		   									        
		   									        String msgRcvd = (String)ClientUI.chat.getMsg();
		   									        System.out.println("You got message" + msgRcvd);
		   									        List<String> Itms = Arrays.asList(msgRcvd.split("\\s*,\\s*"));
		   									        if (Itms.get(0).equals("TimeUpdated"))
		   									        {
		   									        	Integer t = Integer.parseInt(Itms.get(1)) - SolveExamController.solvingTime;
		   									        	updateTime(t);   
		   									        }
	   												
	   											}
	   										}));
	   				
	   		
	   		SolveExamController.fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
	   		SolveExamController.fiveSecondsWonder.play();
	   		
	   		SolveExamController.oneSecondWonder.setCycleCount(Timeline.INDEFINITE);
	   		SolveExamController.oneSecondWonder.play();
	   		
	   		
	   		SolveExamController.delay = new PauseTransition(Duration.seconds(examTime));
	   		
	   		SolveExamController.delay.setOnFinished( event -> {StopTimeLine();});
	   		SolveExamController.delay.play();
	   		
	   		
	   		
	   		stage.setScene(scene);
			stage.show();
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void StopTimeLine()
	{
		String msgToSend ="SubmitExam,";
    	 
    	 msgToSend +=ClientLoginController.loggedUser.personId + "/";
    	 List<String> items = Arrays.asList(SolveExamController.exm.split("\\s*,\\s*"));
    	 
    	 msgToSend += items.get(0) + "/";
    	 
    	 msgToSend += SolveExamController.quesOne.getID() + "@" + "None" + "-";
    	 msgToSend += SolveExamController.quesTwo.getID() + "@" + "None" + "/";
    	 
    	
    	 msgToSend += items.get(6) + "/";
    	 msgToSend += items.get(7) + "/";
    	 
    	 if(SolveExamController.flag == true)
    	 {
    		 msgToSend += "ExamLocked" + "/";
    	 }
    	 else {
    		  msgToSend += "TimePassed" + "/";
		}
       
    	 msgToSend += "False" + "/";
    	 msgToSend += "None" + "/";
    	 msgToSend += SolveExamController.solvingTime;
    	 
    	 ClientUI.chat.accept(msgToSend);
			
		while(!(ClientUI.chat.IsRespone()));    
			
		String msgRcvd = (String)ClientUI.chat.getMsg();
			
	    System.out.println("You got message" + msgRcvd);
		SolveExamController.fiveSecondsWonder.stop();
		SolveExamController.oneSecondWonder.stop();
		stage.close();
	
	}
	
	private void updateTime(Integer newTime)
	{
		SolveExamController.delay.stop();
		SolveExamController.delay = new PauseTransition(Duration.seconds(newTime));
    	SolveExamController.delay.setOnFinished(event -> {StopTimeLine();});
   		SolveExamController.delay.play();
	}

}
