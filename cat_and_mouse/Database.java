package cat_and_mouse;

import java.util.*;
import java.io.*;
import java.sql.*;
//import com.mysql.jdbc.Connection;


@SuppressWarnings("unused")


public class Database {
	
	private java.sql.Connection con;  //connection object comes from driver manager class
	//connection can be shared among multiple classes
	 
	private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    //private ResultSetMetaData rmd;
    
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
			
	    	conn = DriverManager.getConnection(url,user,pass);
			//Create a statement, enables SQL coding, statement object used for all sql, any sql command from SQL prompts executed by Statement.
			stmt=conn.createStatement();  
			
			
		} catch (SQLException e) {
			// TODO CA: Username already in use and LI: Incorrect Username or Password
			System.out.print("SQL Error");
			e.printStackTrace();
		}
	  }
	
	public ArrayList<String> query(String query){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			
			if(rs == null) {
				return null;
			}
			
			while(rs.next()) {
				list.add(rs.getString(1) + "," + rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<String> queryWL(String query) {
		ArrayList<String> number = new ArrayList<String>();
		
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(query);
			
			if(rs == null) {
				return null;
			}
			
			while(rs.next()) {
				number.add(rs.getString(1) + "," + rs.getString(2) +","+rs.getString(3)+","+rs.getString(4));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return number;
	}
	
	public void executeDML(String dml) throws SQLException{
		
		try {
			stmt = conn.createStatement();
		
			stmt.execute(dml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean verifyAccount(LoginData data) {	    
		ArrayList<String> list = query("SELECT username, aes_decrypt(password, 'key') from catandmouse");
	    
		if(list.contains(data.getPlayerName() + "," + data.getPassword())) {
			return true;
		}
		return false;
	}
	
	public boolean verifyCreateInfo(String username, String password) {
		ArrayList<String> list = query("SELECT * FROM catandmouse");
		ArrayList<String> user = new ArrayList<String>();
		ArrayList<String> pass = new ArrayList<String>();
		
		for(int i = 0; i < list.size(); i++) {
			user.add(list.get(i).split(",")[0]);
			pass.add(list.get(i).split(",")[1]);
		}
		
		
		if(user.contains(username) || pass.contains(password)) {
			return true;
		}
		
		return false;
	}
		
	    //Handle CreateAccountData
	public boolean CreateAccount(CreateAccountData data) {
		if(verifyCreateInfo(data.getPlayerName(), data.getPassword()) == false) {
		    try {
				stmt = conn.createStatement();
				
				stmt.execute("insert into catandmouse values ('" + data.getPlayerName() + "',aes_encrypt('" + data.getPassword() + "','key'), 0, 0, 0, 0);");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return true;
	    }
		return false;
		
	}
	
	public void updateCatWins(String player) {
		ArrayList<String> lines = queryWL("SELECT catwins, catlosses, mousewins, mouselosses from catandmouse where username = '" + player + "';");
		int numberOfCatWins = 0;
		for(int i = 0; i<lines.size();i++) {
			numberOfCatWins = Integer.parseInt(lines.get(i).split(",")[0]);
		}
		
		numberOfCatWins = numberOfCatWins + 1;
		try {
			executeDML("UPDATE catandmouse set catwins = " + numberOfCatWins + " where username = '" + player + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateCatLosses(String player) {
		ArrayList<String> lines = queryWL("SELECT catwins, catlosses, mousewins, mouselosses from catandmouse where username = '" + player + "';");
		int numberOfCatLosses = 0;
		for(int i = 0; i<lines.size();i++) {
			numberOfCatLosses = Integer.parseInt(lines.get(i).split(",")[1]);
		}
		
		numberOfCatLosses = numberOfCatLosses + 1;
		try {
			executeDML("UPDATE catandmouse set catlosses = " + numberOfCatLosses + " where username = '" + player + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateMouseWins(String player) {
		ArrayList<String> lines = queryWL("SELECT catwins, catlosses, mousewins, mouselosses from catandmouse where username = '" + player + "';");
		int numberOfMouseWins = 0;
		for(int i = 0; i<lines.size();i++) {
			numberOfMouseWins = Integer.parseInt(lines.get(i).split(",")[2]);
		}
		
		numberOfMouseWins = numberOfMouseWins + 1;
		try {
			executeDML("UPDATE catandmouse set mousewins = " + numberOfMouseWins + " where username = '" + player + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateMouseLosses(String player) {
		ArrayList<String> lines = queryWL("SELECT catwins, catlosses, mousewins, mouselosses from catandmouse where username = '" + player + "';");;
		int numberOfMouseLosses = 0;
		for(int i = 0; i<lines.size();i++) {
			numberOfMouseLosses = Integer.parseInt(lines.get(i).split(",")[3]);
		}
		
		numberOfMouseLosses = numberOfMouseLosses + 1;
		try {
			executeDML("UPDATE catandmouse set mouselosses = " + numberOfMouseLosses + " where username = '" + player + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}