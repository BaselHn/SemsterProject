package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import client.ClientUI;
import entities.Question;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ManualExamController {
	
	 private static String exm;
	
	
	private  static Question quesOne;
	    
	private  static Question quesTwo;
	
	private static Timeline fiveSecondsWonder;
	    
	private static Timeline oneSecondWonder; //Used to measure the time of solving
	
    private static PauseTransition delay;
	    
    private static Integer solvingTime = 0;
	    
	private static Integer  examTime = 0;
	
	private static boolean flag = false;
	
	@FXML
    private Button dwnLdBtn;

    @FXML
    private Button upldBtn;

    @FXML
    private TextField tmLftTxtFld;

    @FXML
    private Label tmLbl;

    @FXML
    private Label frmLbl;

    @FXML
    void handleDownloadExamButtonClick(ActionEvent event) {
       
    	FileChooser fileChooser = new FileChooser();
    	
    	String msgToSend = "GetExam,";
    	
    	msgToSend += StudentMainViewController.exmCode;
    	
    	ClientUI.chat.accept(msgToSend);
    	
    	while(!(ClientUI.chat.IsRespone())); 
    	
    	String rcv = (String)ClientUI.chat.getMsg();
    	
    	System.out.println("You got message" + rcv);
    	
    	List<String> items = Arrays.asList(rcv.split("\\s*,\\s*"));
		
		System.out.println(items.get(0));
		System.out.println(items.get(1));
		System.out.println(items.get(2));
		System.out.println(items.get(3));
		System.out.println(items.get(4));
		System.out.println(items.get(5));
		System.out.println(items.get(6));
		System.out.println(items.get(7));
		
		exm = rcv;
		
		String  exmID = items.get(0);
		
		String questions = items.get(1);
		
		ManualExamController.examTime = Integer.parseInt(items.get(2));
		
		List<String> qs = Arrays.asList(questions.split("\\s*-\\s*"));
		
    	
    	fileChooser.getExtensionFilters().addAll(
    		     new FileChooser.ExtensionFilter("Text Files", "*.txt")
    		    ,new FileChooser.ExtensionFilter("Word Files", "*.docx")
    		);
    	
    	File selectedFile = fileChooser.showSaveDialog(null);
    	
    	try {
			selectedFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
		try {
			FileWriter tFileWriter;
			
			tFileWriter = new FileWriter(selectedFile);
			//BufferedWriter tFileWriter = new BufferedWriter(new FileWriter(selectedFile, true));
			
			tFileWriter.write("************************Be aware to the time in case time passed your mark will be zero ******************************");
	    	tFileWriter.write("\r\n");
	    	tFileWriter.write("************************Upload your exam in the following format  ******************************");
	    	tFileWriter.write("\r\n");
	    	tFileWriter.write(" Question1: Your answer number");
	    	tFileWriter.write("\r\n");
	    	tFileWriter.write(" Question2: Your answer number");
	    	tFileWriter.write("\r\n");
	    	tFileWriter.write(" example Question1: 5");
	    	tFileWriter.write("\r\n");
		
			for(int i = 0; i < qs.size(); i++)
			{
				List<String> q = Arrays.asList(qs.get(i).split("\\s*@\\s*"));
				
				String msg = "GetQuestion," + q.get(0);
				
				String score = q.get(1);
				
				ClientUI.chat.accept(msg);
				
				
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
				
				 tFileWriter.write(qItms.get(1)); 
				 tFileWriter.write("            Instructions:" + qItms.get(2));
				 tFileWriter.write("\r\n");
				 tFileWriter.write("1." + qItms.get(3) + "    2." + qItms.get(4) + "    3." + qItms.get(5) + "    4." + qItms.get(6));
				 tFileWriter.write("\r\n");
				 tFileWriter.write("\r\n");
				 
				 
			    }
		    	
		    	if( i == 1)
				{
		    		tFileWriter.write("************************Question 2******************************");
		    		tFileWriter.write("\r\n");
		    		
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
					
					 tFileWriter.write(qItms.get(1)); 
					 tFileWriter.write("            Instructions:" + qItms.get(2));
					 tFileWriter.write("\r\n");
					 tFileWriter.write("1. " + qItms.get(3) + "    2. " + qItms.get(4) + "    3. " + qItms.get(5) + "    4. " + qItms.get(6));
					 tFileWriter.write("\r\n");
					 tFileWriter.write("\r\n");
					 
					 tFileWriter.write("************************Answer Section******************************");
					 tFileWriter.write("\r\n");
					 tFileWriter.write("Answers:");
					 tFileWriter.write("\r\n");
					 tFileWriter.write("Question1:   ");
					 tFileWriter.write("\r\n");
					 tFileWriter.write("Question2:   ");
					 tFileWriter.write("\r\n");
					 
					 tFileWriter.write("************************End of Exam******************************");
				}
			}
			
			tFileWriter.close();
			
			
			//tFileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ManualExamController.fiveSecondsWonder = new Timeline(
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
		        	//stage.close();
		        	Stage thisStage = (Stage) tmLftTxtFld.getScene().getWindow();

		        	thisStage.close();
		        	
		        	ManualExamController.flag = true;
		        	StopTimeLine();
				   
		        }
		        
		         }
                }));
		
		
		ManualExamController.oneSecondWonder = new Timeline(
					new KeyFrame(Duration.seconds(1), 
					new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							
							ManualExamController.solvingTime++;
							
							tmLftTxtFld.setText(ManualExamController.solvingTime.toString());
							
							ClientUI.chat.accept("IsTimeUpdated," + exmID);
							
							while(!(ClientUI.chat.IsRespone()));
					        
					        String msgRcvd = (String)ClientUI.chat.getMsg();
					        System.out.println("You got message" + msgRcvd);
					        List<String> Itms = Arrays.asList(msgRcvd.split("\\s*,\\s*"));
					        if (Itms.get(0).equals("TimeUpdated"))
					        {
					        	Integer t = Integer.parseInt(Itms.get(1)) - ManualExamController.solvingTime;
					        	updateTime(t);   
					        }
							
						}
					}));


		ManualExamController.fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
		ManualExamController.fiveSecondsWonder.play();
				
		ManualExamController.oneSecondWonder.setCycleCount(Timeline.INDEFINITE);
		ManualExamController.oneSecondWonder.play();
				
		setTheDelay();
		
					
    	
    }
    
   private void setTheDelay() {
		// TODO Auto-generated method stub
    	
    	ManualExamController.delay = new PauseTransition(Duration.seconds(examTime));
		
		ManualExamController.delay.setOnFinished( event -> {StopTimeLine();});
		ManualExamController.delay.play();

	}

    @FXML
    void handleUploadExamButtonClick(ActionEvent event) {
    	
    	FileChooser fileChooser = new FileChooser();
    	
    	File selectedFile = fileChooser.showOpenDialog(null);
    	
    	try {
			FileInputStream fstream = new FileInputStream(selectedFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			
			String q1ans = "";
			String q2ans = "";
			String strLine;
			Integer score = 0;
			try {
				while ((strLine = br.readLine()) != null)   {
					  // Print the content on the console
					  //System.out.println (strLine);
					  
					  List<String> q = Arrays.asList(strLine.split("\\s*:\\s*"));
					  
					  if(q.get(0).equals("Question1"))
					  {
						  System.out.println ("Yapppa-1");
						  System.out.println (q);
						  q1ans = q.get(1);
					  }
					  
					  if(q.get(0).equals("Question2"))
					  {
						  System.out.println ("Yapppa-2");
						  q2ans = q.get(1);
					  }
					  
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	    	 
	    	 List<String> items = Arrays.asList(exm.split("\\s*,\\s*"));
	    	 
	    	 msgToSend += items.get(0) + "/";
	    	 
	    	 msgToSend += quesOne.getID() + "@" + q1ans + "-";
	    	 msgToSend += quesTwo.getID() + "@" + q2ans + "/";
	    	 
	    	
	    	 msgToSend += items.get(6) + "/";
	    	 msgToSend += items.get(7) + "/";
	    	 msgToSend += score + "/";
	    	 msgToSend += "False" + "/";
	    	 msgToSend += "None" + "/";
	    	 msgToSend += ManualExamController.solvingTime;
	    	 
	    	 ClientUI.chat.accept(msgToSend);
				
			while(!(ClientUI.chat.IsRespone()));    
				
			String msgRcvd = (String)ClientUI.chat.getMsg();
				
		    System.out.println("You got message" + msgRcvd);
		    
		    ManualExamController.fiveSecondsWonder.stop();
		    ManualExamController.oneSecondWonder.stop();
		    ManualExamController.delay.stop();
		    Stage thisStage = (Stage) tmLftTxtFld.getScene().getWindow();

	    	thisStage.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    
    private void StopTimeLine()
	{
		String msgToSend ="SubmitExam,";
    	 
    	 msgToSend +=ClientLoginController.loggedUser.personId + "/";
    	 List<String> items = Arrays.asList(ManualExamController.exm.split("\\s*,\\s*"));
    	 
    	 msgToSend += items.get(0) + "/";
    	 
    	 msgToSend += ManualExamController.quesOne.getID() + "@" + "None" + "-";
    	 msgToSend += ManualExamController.quesTwo.getID() + "@" + "None" + "/";
    	 
    	
    	 msgToSend += items.get(6) + "/";
    	 msgToSend += items.get(7) + "/";
    	 
    	 if(ManualExamController.flag == true)
    	 {
    		 msgToSend += "ExamLocked" + "/";
    	 }
    	 else {
    		  msgToSend += "TimePassed" + "/";
		}
       
    	 msgToSend += "False" + "/";
    	 msgToSend += "None" + "/";
    	 msgToSend += ManualExamController.solvingTime;
    	 
    	 ClientUI.chat.accept(msgToSend);
			
		while(!(ClientUI.chat.IsRespone()));    
			
		String msgRcvd = (String)ClientUI.chat.getMsg();
			
	    System.out.println("You got message" + msgRcvd);
	    ManualExamController.fiveSecondsWonder.stop();
	    ManualExamController.oneSecondWonder.stop();
		Stage thisStage = (Stage) tmLftTxtFld.getScene().getWindow();

    	thisStage.close();
	
	}
    
    
    private void updateTime(Integer newTime)
	{
    	ManualExamController.delay.stop();
    	ManualExamController.delay = new PauseTransition(Duration.seconds(newTime));
    	ManualExamController.delay.setOnFinished(event -> {StopTimeLine();});
    	ManualExamController.delay.play();
	}

}
