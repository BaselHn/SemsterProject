package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import client.ClientUI;
import entity.Exam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ClientController implements Initializable {
	
	@FXML
	private Button update;
	
	@FXML
	private TableView<Exam> dbTable;
	
	@FXML
	private TableColumn<Exam, Integer> IDheadline;
	
	@FXML
	private TableColumn<Exam, String> SubjectHeadline;
	
	@FXML
	private TableColumn<Exam, String> CourseHeadline;
	
	@FXML
	private TableColumn<Exam, Integer> slvtHeadline;
	
	@FXML
	private TableColumn<Exam, Integer> mrkquesHeadline;
	
	@FXML
	private TextField IdField;
	
	@FXML
	private TextField subjectFileld;
	
	@FXML
	private TextField courseField;
	
	@FXML
	private TextField slvTField;
	
	@FXML
	private TextField mrkQField;
	
	ObservableList<Exam> oblist;
	
	@FXML
	void UpdateTable(ActionEvent event) throws SQLException {
	   
		Connection conn = ServerController.getConnection();
		
		int editable = 0;
		
		int newTime = 0;
		
		String ID = IdField.getText();
		
		Integer id = Integer.parseInt(ID);
		
		ResultSet rs = conn.createStatement().executeQuery(" select ID from cems_db.test");
		
		System.out.println("Im Here");
		
		while(rs.next())
		{
			if (rs.getInt("ID") == id)
			{
				newTime = Integer.parseInt(slvTField.getText());
				editable = 1;
				
				break;
			}
		}
		
		if (editable == 1)
		{
			PreparedStatement updateSolvTimePreparedStatement = conn.prepareStatement("UPDATE test SET  SolveTimeInMin = ? WHERE ID = ?");
			updateSolvTimePreparedStatement.setInt(1, newTime);
			updateSolvTimePreparedStatement.setInt(2, id);
			
			System.out.println("The new time is: " + newTime + " id is : " + id);
			
			System.out.println("Im Here - 2");
			 // condition field
			updateSolvTimePreparedStatement.executeUpdate();
			refreshDbTable();
		}
		else {
			System.out.println("Error");
		}
	}
	
	@FXML
	void handleDataRowClick(MouseEvent event) {
		
		int index = dbTable.getSelectionModel().getSelectedIndex();
		
		if (index <= -1)
		{
			return;  //Handling Errors
		}
		
		IdField.setText(IDheadline.getCellData(index).toString());
		subjectFileld.setText(SubjectHeadline.getCellData(index).toString());
		courseField.setText(CourseHeadline.getCellData(index).toString());
		slvTField.setText(slvtHeadline.getCellData(index).toString());
		mrkQField.setText(mrkquesHeadline.getCellData(index).toString());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		//System.out.println("Init client gui");
		refreshDbTable();
			
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		ClientUI.chat.accept(" Hello server ");
		
		System.out.println("Got here");
		
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Client.fxml"));
				
		Scene scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("/gui/Client.css").toExternalForm());
		
		primaryStage.setTitle("Client side");
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
		//refreshDbTable();
	}
	
	public void refreshDbTable()
	{
		oblist = FXCollections.observableArrayList();
		
		try {
			
			Connection conn = ServerController.getConnection();
			
			ResultSet rSet = conn.createStatement().executeQuery("select * from cems_db.test");
			
			System.out.println("QuerySucceded");
			while(rSet.next())
			{
				oblist.add(new Exam(rSet.getInt("ID"), 
									rSet.getString("Subject"), 
									rSet.getString("Course"),
									rSet.getInt("SolveTimeInMin"),
									rSet.getInt("NumberOfMarksEachQues")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		IDheadline.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("ExamID"));
		SubjectHeadline.setCellValueFactory(new PropertyValueFactory<Exam, String>("Subject"));
		CourseHeadline.setCellValueFactory(new PropertyValueFactory<Exam, String>("ExamCourse"));
		slvtHeadline.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("ExamSolTime"));
		mrkquesHeadline.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("ExamMrkQues"));
		
		dbTable.setItems(oblist);
	}

	
}
