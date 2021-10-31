package cat_and_mouse;

//This Database is modelled on Lab7in template.


import java.util.*;
import java.io.*;
import java.sql.*;
import com.mysql.jdbc.Connection;


public class Database {
	private java.sql.Connection con;
	  //Add any other data fields you like – at least a Connection object is mandatory
	 
    private Statement stmt;
    private ResultSet rs;
    private ResultSetMetaData rmd;
    
    private String url; 
    private String user; 
    private String pass;
	
	
	public Database() throws IOException, SQLException  //
	  {
		  
		//Create a properties object, Read properties file
		//Use a fileInputStream as input to the Properties object for reading the db.properties file
		//Get the username, password, and url
		//Set the conn object.
	    Properties prop = new Properties();
	    FileInputStream fis = new FileInputStream("mySQL/db.properties");
	    prop.load(fis);
	    String url = prop.getProperty("url");
	    String user = prop.getProperty("user");
	    String pass = prop.getProperty("password");    
	    con = DriverManager.getConnection(url,user,pass);
	  }
	
	  public ArrayList<String> query(String query) throws SQLException
	  {
		 //Using the Con object create a statement
		 //using the statment object executeQuery using the input query (return the result set)
		 //use a while loop to proce3ss the rows - create , delimed rcor from field
		 //add each, delimited record to Arraylist
		 //If no data found-> return null.
		  
		//Create a statement, enables SQL coding
		stmt=con.createStatement();  
		      
		//Execute a DML statement
		stmt.execute("INSERT into CatAndMouseDB VALUES('exampleUser','examplePassword')");
		    
		//Execute a query
		rs=stmt.executeQuery("select * from CatAndMouseDB");  
		      
		//Get metadata about the query
		rmd = rs.getMetaData();
		      
		//Get the # of columns
		int no_columns = rmd.getColumnCount();
		    
		//Get a column name
		String name = rmd.getColumnName(1);
		      
		//Fetch each row (use numeric numbering
		while(rs.next()) 
		{
		   System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		}
		      
		con.close();  
		    
		System.out.println("Success");  
		
	    return null;  
	  }
	  
	  public void executeDML(String dml) throws SQLException //The exception here pertains to SQL dadatabase constraints
	  {
	    //Add your code here
		  //Using the Con object create a statement
		  //run dml on the execute method statement
		  
	  }
	  

	
	
}
