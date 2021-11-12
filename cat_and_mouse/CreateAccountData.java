package cat_and_mouse;

import java.io.Serializable;
@SuppressWarnings("serial")

public class CreateAccountData implements Serializable {
	
	 // Private data fields for the player name and password.
	  private String playername;
	  private String password;
	  private String password_match;
	
	 // Getters for the player name and password.
	  public String getPlayerName()
	  {
	    return playername;
	  }
	  public String getPassword()
	  {
	    return password;
	  }
	  public String getPasswordMatch()
	  {
		  return password_match;
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
	  public void setPasswordMatch(String passwordMatch)
	  {
	    this.password_match = passwordMatch;
	  }
	    
	
	public CreateAccountData(String pn, String pw, String pwm) {
		setPlayerName(pn);
		setPassword(pw);
		setPasswordMatch(pwm);
	}

	
}
