package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.ExamResult;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class StudentMarksTableController implements Initializable{
	
	//public static StudentMarksTableController stCntrController = new StudentMarksTableController();

	@FXML
    private   TableView<ExamResult> marksTable;
	
	@FXML
	private   TableColumn<ExamResult, String> idHdLin;

	@FXML
	private   TableColumn<ExamResult, String> exmIdHdline;
	
	//@FXML
	//private   TableColumn<ExamResult, String> crsHdLine;
	
	@FXML
	private   TableColumn<ExamResult, String> mrkHdline;
	
	private  static ObservableList<ExamResult> list;
	 
	public  void showTable(ObservableList<ExamResult> ls)
	{
		System.out.println("Show the table");
		System.out.println(ls);
		
		try {
			StudentMarksTableController.list = ls;
			Pane root = FXMLLoader.load(getClass().getResource("gui/studentMarksTableFx.fxml"));
			Scene scene = new Scene(root);
	   		Stage stage = new Stage();
	   		stage.setScene(scene);
			stage.show();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		//this.setTable(ls);
	}
	
	public void setTable(ObservableList<ExamResult> ls)
	{
		
		idHdLin.setCellValueFactory(new PropertyValueFactory<ExamResult, String>("StudentId"));
		exmIdHdline.setCellValueFactory(new PropertyValueFactory<ExamResult, String>("ExamId"));
		//crsHdLine.setCellValueFactory(new PropertyValueFactory<ExamResult, String>("Course"));
		mrkHdline.setCellValueFactory(new PropertyValueFactory<ExamResult, String>("Score"));
		marksTable.setItems(ls);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("Your list is ");
		System.out.println(StudentMarksTableController.list);
		setTable(StudentMarksTableController.list);
		
	}
}
