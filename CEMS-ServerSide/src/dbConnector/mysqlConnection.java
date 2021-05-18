package dbConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * this is the mysqlConnection class
 * here we perform the design pattern of the singleton pattern to create
 * only one connection to the mySql. and we use this same object (instance) 
 * over and over again.
 * other then the connection itself, we are also a here 3 general functions
 * that handle every mySQL query that we needed
 * 1. select from table (handle select queries without parameters)
 * 2. select with parameters (handle select queries with parameters)
 * 3. update table (handle queries with a PreparedStatement)
 */

public class mysqlConnection 
{
	private static Connection con = null;
	public static boolean awaitResponse2 = true;
	
	private static String dbLink = "";
	
	private static Connection getConnection(String link, String userName, String pswd)
	{
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Driver definition succeed");
        }
		catch (Exception ex)
		{
        	 System.out.println("Driver definition failed");
		}
        
        try 
        {
        	//con = DriverManager.getConnection("jdbc:mysql://localhost/cems_db?serverTimezone=IST","root","basel@sql");
        	con = DriverManager.getConnection(link, userName, pswd);
        	
            System.out.println("SQL connection succeed");
            return con;
     	}
        catch (SQLException ex)
        {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return null;
   	}
	
	public static Connection getInstance(String link, String userName, String pswd) 
    { 
        if (con == null) 
        {
        	con = getConnection(link, userName, pswd);
        }
        return con; 
    }
	
	
	
	/**
	 * selectFromTable : this function is going threw the process of
	 * a regular select query
	 * @param query = a specific query that we sent
	 * (example :"select * form employees")
	 * @return ResultSet (as usual)
	 */

	public static ResultSet selectFromTable(String query)
	{
		ResultSet rs = null;
		
		if(con != null)
		{
			try 
			{
			  rs = con.createStatement().executeQuery(query);
					
			} 
			catch (SQLException e) 
			{
				System.err.println("Error : there is a problem with selectFromTable function");
				e.printStackTrace();
			}
		}
		
		return rs;
	}
	
	/**
	 * updateTable : this function is going threw the process of
	 * update query with a prepareStatement
	 * @param query = a specific query that we sent
	 * (example :"update employees set workerID = ? where workerID = ?")
	 * @param ArrayList that contains all the parameters for the ? of the query
	 */
	
	public static void updateTable(String query, ArrayList<String> parameters)
	{
		
		if(con != null)
		{
		    try
		    {
		    	PreparedStatement ps = con.prepareStatement(query);
		    	
		    	for(int i = 1; i <= parameters.size(); i++)
		    	{
		    		ps.setString(i, parameters.get(i-1));
		    	}
			    
		    	
			    ps.executeUpdate();
			    
			    
			}
		    catch (SQLException e)
		    {
		    	System.err.println("Error : there is a problem with updateTable function");
				e.printStackTrace();
			}
		    awaitResponse2=false;
		}
		else {
			System.err.println("No Connection to DB");
		}
	}
	
	/**
	 * SelectWithParameters : this function is going threw the process of
	 * a select query with parameters
	 * @param query = a specific query that we sent
	 * (example :"select * from employees where workerID = ?")
	 * @param ArrayList that contains all the parameters for the ? of the query
	 */
	
	public static ResultSet SelectWithParameters(String query, ArrayList<String> parameters)
	{
		ResultSet rs = null;
		
		if(con != null)
		{
		    try
		    {
		    	PreparedStatement ps = con.prepareStatement(query);
		    	
		    	for(int i = 1; i <= parameters.size(); i++)
		    	{
		    		ps.setString(i, parameters.get(i-1));
		    	}
			    
		    	
			    rs = ps.executeQuery();
			   
			}
		    catch (SQLException e)
		    {
		    	System.err.println("Error : there is a problem with updateTable function");
				e.printStackTrace();
			}
		}
	    
	    return rs;
	}
}


