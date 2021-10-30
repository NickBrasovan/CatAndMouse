package cat_and_mouse;

import java.util.HashMap;
import java.io.Serializable;
public class LoginData implements Serializable {

	 // Private data fields for the player name and password.
	  private String playername;
	  private String password;
	  
	  // Getters for the player name and password.
	  public String getPlayerName()
	  {
	    return playername;
	  }
	  public String getPassword()
	  {
	    return password;
	  }
	  
	  // Setters for the username and password.
	  public void setPlayerName(String username)
	  {
	    this.playername = username;
	  }
	  public void setPassword(String password)
	  {
	    this.password = password;
	  }
	  
	  // Constructor that initializes the username and password.
	
	
	public LoginData(String pn, String pw){
		setPlayerName(pn);
		setPassword(pw);
	}
	
	
}
