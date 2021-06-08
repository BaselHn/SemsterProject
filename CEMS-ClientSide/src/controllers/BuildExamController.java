package controllers;
import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import entities.ExamResult;
import entities.Question;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BuildExamController implements Initializable{

	private  static ObservableList<Question> list;
	
    @FXML
    private TextField qScrTxtFld;

    @FXML
    private TextField qIdTxtFld;

    @FXML
    private Button addQuesButton;

    @FXML
    private TextField slvTmTxtFld;

    @FXML
    private TextField stdInstTxtFld;

    @FXML
    private TextField tchInstTxtFld;

    @FXML
    private TextField exeTxtFld;
    
    @FXML
    private TextField exmIdTxtFld;

    @FXML
    private Button subButton;
    
    @FXML
    private TableView<Question> quesTable;

    @FXML
    private TableColumn<Question, String> quesID;

    @FXML
    private TableColumn<Question, String> questionsCl;
    
    private String examQues = "";

    @FXML
    void handleAddQuesButtonClick(ActionEvent event) {
      
    	examQues += qIdTxtFld.getText() + "@" + qScrTxtFld.getText() + "-" ;
    }

    @FXML
    void handleSubmitButtonClick(ActionEvent event) {
    	
    	//Drop the last character
    	examQues = examQues.substring(0, examQues.length() - 1);
    	
    	String msgToSend ="AddExam,";
    	
    	msgToSend += exmIdTxtFld.getText() + "/";
    	msgToSend += examQues + "/";
    	msgToSend += slvTmTxtFld.getText() + "/";
    	msgToSend += stdInstTxtFld.getText() + "/";
    	msgToSend += tchInstTxtFld.getText()+ "/";
    	msgToSend += exeTxtFld.getText() + "/";
    	msgToSend += ClientLoginController.loggedUser.personName + "/";
    	msgToSend += ClientLoginController.loggedUser.personId + "/";
    	msgToSend += "False";
    	
    	ClientUI.chat.accept(msgToSend);
    	while(!(ClientUI.chat.IsRespone()));    
    	
    	String msgRcvd = (String)ClientUI.chat.getMsg();
    	System.out.println("You got message" + msgRcvd);

    }
    
    @FXML
    void handleDataRowClick(MouseEvent event) {
       
    	int index = quesTable.getSelectionModel().getSelectedIndex();
    	
    	qIdTxtFld.setText(quesID.getCellData(index).toString());
    	
    }
    
    
    public void openBuildExamForm(ObservableList<Question> oblist)
    {
    	
    	System.out.println("Show the table");
		System.out.println(oblist);
		
		try {
				BuildExamController.list = oblist;
				Pane root = FXMLLoader.load(getClass().getResource("gui/buildExamFx.fxml"));
				Scene scene = new Scene(root);
		   		Stage stage = new Stage();
		   		stage.setScene(scene);
				stage.show();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
    }
     
    private void showTable() {
		// TODO Auto-generated method stub
    	
    	quesID.setCellValueFactory(new PropertyValueFactory<Question, String>("ID"));
    	questionsCl.setCellValueFactory(new PropertyValueFactory<Question, String>("Question"));
		
		quesTable.setItems(BuildExamController.list);

	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showTable();
		
	}

}
