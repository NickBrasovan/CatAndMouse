package cat_and_mouse;

import java.util.*;

import ocsf.client.AbstractClient;

public class PlayerClient extends AbstractClient {

	// Private data fields for storing the GUI controllers.
	  private TitlescreenControl titleScreenControl;
	  private CreateAccountControl createAccountControl;
	  private GameroomControl gameroomControl;

	  public void setTitlescreenControl(TitlescreenControl titleScreenControl) {
			this.titleScreenControl = titleScreenControl;
		}
		
		public void setCreateAccountControl(CreateAccountControl createAccountControl) {
			this.createAccountControl = createAccountControl;
		}
		
		public void setGameroomControl(GameroomControl gameroomControl) {
			this.gameroomControl = gameroomControl;
		}
		
	  //Constructor calls Constructor of AbstractClient
	  public PlayerClient() {
		  super("localhost", 8300);
		}
	
	/*HANDLE MESSAGE FROM SERVER*/  
	@Override
	protected void handleMessageFromServer(Object arg0)
	{
		// If we received a String, figure out what this event is. //The Following implementation is adopted from ChatClient by Mark Smith for Lab5out
	    if (arg0 instanceof String)
	    {
	      // Get the text of the message.
	      String message = (String)arg0;
	      
	      
	      // If we successfully logged in, tell the login controller.
	      if (message.equals("LoginSuccessful"))
	      {
	        titleScreenControl.loginSuccess();
	      }
	      // If we successfully created an account, tell the create account controller.
	      else if (message.equals("CreateAccountSuccessful"))
	      {
	        createAccountControl.createAccountSuccess();
	      }
	    }
	    
	    //If receive gamescreen, then gameroom.create account success.  and the latter shows screen
	    if (arg0 instanceof Gamescreen)
	    {
	    	Gamescreen gs = (Gamescreen)arg0;
	    	gameroomControl.playGameSuccess(gs);
	    }
	    
	    
	    
	   //If we received an Error, figure out where to display it.
	    else if (arg0 instanceof Error)
	    {
	      // Get the Error object.
	      Error error = (Error)arg0;
	      
	      // Display login errors using the login controller.
	      if (error.getType().equals("Login"))
	      {
	        titleScreenControl.displayError(error.getMessage());
	      }
	      
	      else if (error.getType().equals("CreateAccount")) {
	    	  createAccountControl.displayError(error.getMessage());
	      }
	      // Display account creation errors using the create account controller.
	      //else if (error.getType().equals("CreateAccount"))
	      //{
	      //  createAccountControl.displayError(error.getMessage());
	      //}
	    } 
	    
	    else if (arg0 instanceof ArrayList<?>) {
	    	ArrayList<String> playersLoggedIn = (ArrayList<String>)arg0;
	    	
	    	gameroomControl.setLog(playersLoggedIn);
	    }
	    
	}
}
