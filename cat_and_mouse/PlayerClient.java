package cat_and_mouse;

import java.util.*;

import ocsf.client.AbstractClient;

public class PlayerClient extends AbstractClient {

	// Private data fields for storing the GUI controllers.
	  private TitlescreenControl titleScreenControl;
	  private CreateAccountControl createAccountControl;
	  private GameroomControl gameroomControl;
	  private GamescreenControl gamescreenControl;

	  public void setTitlescreenControl(TitlescreenControl titleScreenControl) {
			this.titleScreenControl = titleScreenControl;
		}
		
		public void setCreateAccountControl(CreateAccountControl createAccountControl) {
			this.createAccountControl = createAccountControl;
		}
		
		public void setGameroomControl(GameroomControl gameroomControl) {
			this.gameroomControl = gameroomControl;
		}
		
		public void setGamescreenControl(GamescreenControl gamescreenControl) {
			this.gamescreenControl = gamescreenControl;
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
	      
	      else if (message.equals("Cat"))
	      {
	    	  gameroomControl.playGameSuccess();
	    	  gamescreenControl.setCharacter("Cat");
	      }
	      
	      else if (message.equals("Mouse")) {
	    	  gameroomControl.playGameSuccess();
	    	  gamescreenControl.setCharacter("Mouse");
	      }
	      else if (message.equals("CatWin")) {
	    	  gamescreenControl.setInGame();
	      }
	    }
	    
	    /*Handle Updated Coordinates*/
		if (arg0 instanceof GamescreenData)
		{
			if(((GamescreenData) arg0).getCharacter().equals("Mouse"))
		/*
		We'll need to update player coordinates with gamescreen data.*/
				gamescreenControl.setMouseGSD((GamescreenData) arg0);
			else if(((GamescreenData) arg0).getCharacter().equals("Cat"))
				gamescreenControl.setCatGSD((GamescreenData) arg0);
		}
	    
	    
	    if(arg0 instanceof Integer) {
	    	gamescreenControl.setSeconds((int) arg0);
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
	    } 
	    
	    else if (arg0 instanceof ArrayList<?>) {
	    	ArrayList<String> playersLoggedIn = (ArrayList<String>)arg0;
	    	
	    	gameroomControl.setLog(playersLoggedIn);
	    }
	    
	}
}
