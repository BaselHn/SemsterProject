// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package Server;

import java.io.*;
import java.net.InetAddress;
import java.util.Vector;

import gui.ServerController;
import javafx.scene.Scene;
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
	 int flag=0;
	    System.out.println("Message received: " + msg + " from " + client);
	    String name =  client.getName();
	    InetAddress ipAddr = client.getInetAddress();
	  
	    System.out.println("Client Connected");
	    System.out.println("Client  : " + name);
	    System.out.println("Ip : " + ipAddr.toString());
	  
	  
	    IpStr = ipAddr.toString();
	    HostName = name;
	    clientIsConnected = true;
	  
  }
   
  @Override
 /* protected void clientConnected(ocsf.server.ConnectionToClient client)
  {
	  String name =  client.getName();
	  InetAddress ipAddr = client.getInetAddress();
	  
	  System.out.println("Client Connected");
	  System.out.println("Client  : " + name);
	  System.out.println("Ip : " + ipAddr.toString());
	  
	  
	  IpStr = ipAddr.toString();
	  HostName = name;
	  clientIsConnected = true;
	  //ServerController.setStatus("Hello Server");
	  
	 // ServerController.getController().setStatus(ipAddr.toString());
	  
  }*/
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
}
//End of EchoServer class
