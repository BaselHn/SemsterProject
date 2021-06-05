// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package server;


import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dbConnector.mysqlConnection;
import entities.ExamResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ocsf.server.*;


/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class EchoServer extends AbstractServer 
{
	
  public  static boolean clientIsConnected = false;
  
  public static String IpStr = " ";
  public static String HostName = " ";
  public static ResultSet rs;

   
   
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  //final public static int DEFAULT_PORT = 5555;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   * 
   */


  public EchoServer(int port) 
  {
    super(port);
  }

  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   * @param 
   */
  public void handleMessageFromClient (Object msg, ConnectionToClient client)
  {
	  
	    
	    String msgStr;
	    ArrayList<String> parameters=new ArrayList<String>();
	    
	    System.out.println("Message received: " + msg + " from " + client);
	    String name =  client.getName();
	    InetAddress ipAddr = client.getInetAddress();
	  
	    System.out.println("Client Connected");
	    System.out.println("Client  : " + name);
	    System.out.println("Ip : " + ipAddr.toString());
	    
	    msgStr = (String)msg;
	    
	    System.out.println("************************************************");
	    
	    List <String> msgRcvd = Arrays.asList(msgStr.split("\\s*,\\s*"));
	    
	    switch (msgRcvd.get(0)) {
	    
			case "login":
			{
				 List<String> items = Arrays.asList(msgRcvd.get(1).split("\\s*/\\s*"));
				    
				    System.out.println(items.get(0));
				    System.out.println(items.get(1));
				    System.out.println(items.get(2));
				    
				    IpStr = ipAddr.toString();
				    HostName = name;
				    clientIsConnected = true;
				    
				   
				    
				    parameters.add(items.get(0));
					parameters.add(items.get(1));
					
			        String query="SELECT * FROM users WHERE username = ? and password = ?";
				    
			        ResultSet resultSet = selectWithParameters(query, parameters);
			        
			       try {
						      
						      String usr = "";
						      String psw = "";
						      String type = "";
						      
							  System.out.println("************************************************"); 
							  while(resultSet.next())
							  {
							      usr = resultSet.getString("username");
							      
							      psw = resultSet.getString("password");
							      
							      type =  resultSet.getString("type");
							      
								  int id = resultSet.getInt("ID");
								  
								  System.out.println(usr);
								  System.out.println(psw);
								  System.out.println(type);
								  System.out.println(id);
							  }
							  System.out.println("************************************************");	
							  
							  if( usr.equals(items.get(0)) && psw.equals(items.get(1)) && type.equals(items.get(2)) )
						      {
								  this.sendToAllClients("LoginSucced");
						      }
							  else 
							  {
								  this.sendToAllClients("LoginError");
							  }		
								
						 // }
			       	} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
            
			case "getMarks":
			{
				 
				 System.out.println(msgRcvd.get(1));
				 
				 parameters.add(msgRcvd.get(1));
				 
				 String query="SELECT * FROM examsresults WHERE stdID = ? ";
				    
			     ResultSet resultSet = selectWithParameters(query, parameters);
			     String msgSnd = "";
			     try {
					while(resultSet.next())
						{
						    ExamResult exm = new ExamResult();
						    
						    
						     exm.studenId = resultSet.getString("stdID");
						     exm.examId = resultSet.getString("examId");
						     exm.score  = resultSet.getString("score");
							
						     msgSnd +=  exm.studenId + "," + exm.examId + "," +exm.score + "/";
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			    msgSnd = msgSnd.substring(0, msgSnd.length() - 1);
				this.sendToAllClients(msgSnd);
				
			}
			break;
			
			case "AddQuestion":
			{
				 List<String> items = Arrays.asList(msgRcvd.get(1).split("\\s*/\\s*"));
				 System.out.println(items.get(0));
				 System.out.println(items.get(1));
				 System.out.println(items.get(2));
				 System.out.println(items.get(3));
				 System.out.println(items.get(4));
				 System.out.println(items.get(5));
				 System.out.println(items.get(6));
				 System.out.println(items.get(7));
				 System.out.println(items.get(8));
				 
				 
				 String query = "INSERT INTO questions (ID, Question, Instructions, Answer_1, Answer_2, Answer_3, Answer_4, RightAns, Teacher) VALUES(?,?,?,?,?,?,?,?,?);";
				 
				 parameters.add(items.get(0));
				 parameters.add(items.get(1));
				 parameters.add(items.get(2));
				 parameters.add(items.get(3));
				 parameters.add(items.get(4));
				 parameters.add(items.get(5));
				 parameters.add(items.get(6));
				 parameters.add(items.get(7));
				 parameters.add(items.get(8));
				 
				 updateTable(query, parameters);
				 
				 this.sendToAllClients("Question");
			}
			break;
			
			case "getQuestions":
			{
				System.out.println("getQuestions");
				System.out.println(msgRcvd.get(1));
				
				parameters.add(msgRcvd.get(1));
				 
				 String query="SELECT * FROM questions WHERE Teacher = ? ";
				    
			     ResultSet resultSet = selectWithParameters(query, parameters);
			     
			     String msgSnd = "";
			     try {
					while(resultSet.next())
						{
						    
						     msgSnd +=  resultSet.getString("ID") + "," + resultSet.getString("Question") + "," + resultSet.getString("Instructions") + "," 
						    		 	+ resultSet.getString("Answer_1") + ","+ resultSet.getString("Answer_2") + "," + resultSet.getString("Answer_3")  
						    		 	+ "," +resultSet.getString("Answer_4") + "," + resultSet.getString("RightAns") + "," + resultSet.getString("Teacher") +"/";
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			    msgSnd = msgSnd.substring(0, msgSnd.length() - 1);
				this.sendToAllClients(msgSnd);
			}
			break;
			
			case "AddExam":
			{
				 List<String> items = Arrays.asList(msgRcvd.get(1).split("\\s*/\\s*"));
				 System.out.println(items.get(0));
				 System.out.println(items.get(1));
				 System.out.println(items.get(2));
				 System.out.println(items.get(3));
				 System.out.println(items.get(4));	
				 System.out.println(items.get(5));
				 
				 
				 String query = "INSERT INTO exams (ID, Questions, Time, IntrStd,IntrTch, ExecCode) VALUES(?,?,?,?,?,?);";
				 
				 parameters.add(items.get(0));
				 parameters.add(items.get(1));
				 parameters.add(items.get(2));
				 parameters.add(items.get(3));
				 parameters.add(items.get(4));
				 parameters.add(items.get(5));
				 
				 updateTable(query, parameters);
				 
				 this.sendToAllClients("Exam");
			}
			break;
			
			case "GetExam":
			{
				System.out.println(msgRcvd.get(1));
				
				parameters.add(msgRcvd.get(1));
				 
				 String query="SELECT * FROM exams WHERE ExecCode = ? ";
				    
			     ResultSet resultSet = selectWithParameters(query, parameters);
			     
			     String msgSnd = "";
			     try {
					while(resultSet.next())
						{
						    
						     msgSnd +=  resultSet.getString("ID") + "," + resultSet.getString("Questions") + "," + resultSet.getString("Time") + "," 
						    		 	+ resultSet.getString("IntrStd") + ","+ resultSet.getString("IntrTch") + "," + resultSet.getString("ExecCode")  +"/";
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			    msgSnd = msgSnd.substring(0, msgSnd.length() - 1);
				this.sendToAllClients(msgSnd);
			}
			break;
			
			case "GetQuestion":
			{
				System.out.println(msgRcvd.get(1));
				
				parameters.add(msgRcvd.get(1));
				 
				 String query="SELECT * FROM questions WHERE ID = ? ";
				    
			     ResultSet resultSet = selectWithParameters(query, parameters);
			     
			     String msgSnd = "";
			     try {
					while(resultSet.next())
						{
						    
						  msgSnd +=  resultSet.getString("ID") + "," + resultSet.getString("Question") + "," + resultSet.getString("Instructions") + "," 
					    		 	+ resultSet.getString("Answer_1") + ","+ resultSet.getString("Answer_2") + "," + resultSet.getString("Answer_3")  
					    		 	+ "," +resultSet.getString("Answer_4") + "," + resultSet.getString("RightAns") + "," + resultSet.getString("Teacher") +"/";
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			    msgSnd = msgSnd.substring(0, msgSnd.length() - 1);
				this.sendToAllClients(msgSnd);
			}
			
			default:
				break;
		}
	   
	  
  }
   
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println ("Server listening for connections on port " + getPort());
   
  }
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()  {
    System.out.println ("Server has stopped listening for connections.");
  } 
  
  
  public static ResultSet getTable(String query)
	{
	  	rs = mysqlConnection.selectFromTable(query);
			return rs;
	}
  
  // we added this
  public static void updateTable(String query, ArrayList<String> parameters)
  {
  		mysqlConnection.updateTable(query, parameters);
  }
  
  // we added this
  public static ResultSet selectWithParameters(String query, ArrayList<String> parameters)
  {
	  	rs = mysqlConnection.SelectWithParameters(query, parameters);
	  	
		return rs;
  }
}
//End of EchoServer class
