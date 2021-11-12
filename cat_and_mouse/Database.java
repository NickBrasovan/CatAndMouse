package cat_and_mouse;

import java.util.*;
import java.io.*;
import java.sql.*;
import com.mysql.jdbc.Connection;


@SuppressWarnings("unused")


public class Database {
	
	private java.sql.Connection con;  //connection object comes from driver manager class
	//connection can be shared among multiple classes
	 
    private Statement stmt;
    private ResultSet rs;
    private ResultSetMetaData rmd;
    
    private String url; 
    private String user; 
    private String pass;
	
	
	public Database() throws IOException
	  {
		//Properties Object to read and parse files (set up to be key-value pairs)
		Properties prop = new Properties();
		
		//Use a fileInputStream as input to the Properties object for reading the db.properties file
	    FileInputStream fis = new FileInputStream("db.properties");  //create file input stream object; drag file into package; b/c it's in the package, you have to specify package(project) name slaxh
	    prop.load(fis);
	    
	    /*db.properties files has the following content:
	      user=student
	      password=hello
	      url=jdbc\:mysql\://localhost\:3306/student_space*/
	    
	    //Set SQL Domain and Workspace with username, password, and url. Required for JDBC to connect to database.
	    url = prop.getProperty("url");         //define url
	    user = prop.getProperty("user");      //define user
	    pass = prop.getProperty("password"); //define pass
	    
	    //Peform connection and Set the con object; needed to execute an sql; used to create all sql constructs
	    try {
			
	    	con = DriverManager.getConnection(url,user,pass);
			//Create a statement, enables SQL coding, statement object used for all sql, any sql command from SQL prompts executed by Statement.
			stmt=con.createStatement();  
			
			
		} catch (SQLException e) {
			// TODO CA: Username already in use and LI: Incorrect Username or Password
			System.out.print("SQL Error");
			e.printStackTrace();
		}
	  }
	    public boolean verifyAccount(LoginData data)
	    {
	    boolean verification_value = false;	
	    
	    String sql = "SELECT * FROM user WHERE username='"+data.getPlayerName()+"'and password='"+data.getPassword()+"'";
	    //String sql = "SELECT * FROM user WHERE username='"+data.getUsername()+"'and password='cast(aes_decrypt("+data.getPassword()+"'";
	    //String sql = "SELECT * FROM user WHERE username=" + data.getUsername() + "and password=cast(aes_decrypt(" + data.getPassword() +", 'keyvalue')";
	    //String sql = "SELECT username, password, CAST(AES_DECRYPT('"+data.getPassword()+"', 'keyvalue') AS CHAR(16)) FROM user";
	    //String sql = "SELECT * FROM user WHERE username = ('" + data.getUsername() + "'and password= aes_decrypt('" + data.getPassword() + "', 'key')";
	    
	    try {
		rs = stmt.executeQuery(sql);
		if(rs.next())
		{
			return true;	
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return verification_value;
	    }
		
	    //Handle CreateAccountData
	    public boolean CreateAccount(CreateAccountData data)
	    {
	    boolean verify_newAcct_value = false;
	    try {
	    stmt.executeUpdate("INSERT INTO user (username, password) "
                + "VALUES ('" + data.getPlayerName() + "', '" + data.getPassword());
	    verify_newAcct_value = true;
	    return verify_newAcct_value;
	    
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Create Account Error");
			e.printStackTrace();
		}
	    
	    return verify_newAcct_value;
	    }
		
}