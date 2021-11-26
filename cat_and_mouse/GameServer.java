package cat_and_mouse;

import java.awt.*;
import javax.swing.*;

import com.mysql.fabric.Server;

import java.io.IOException;

import java.util.ArrayList;

import java.util.*;


import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

import java.net.ServerSocket;
import java.net.Socket;


public class GameServer extends AbstractServer {
	 
	// Data fields for this chat server.
	  private JTextArea log;
	  private JLabel status;
	  private boolean running = false;
	  private Database database;
	  private ArrayList<String> playersLoggedIn;
	  private ArrayList<ConnectionToClient> connectedPlayers;
	  
	  
	  

	  // Constructor for initializing the server with default settings.
	  public GameServer()
	  {
	    super(8300);
	    this.setTimeout(500);
	    playersLoggedIn = new ArrayList<String>();
	    connectedPlayers = new ArrayList<ConnectionToClient>();

	    
	  }
	 
	  
	  /*SETTER FOR DATABASE*/
	  public void setDatabase(Database database)
	  {
		  this.database = database;
	  }

	  // Getter that returns whether the server is currently running.
	  public boolean isRunning()
	  {
	    return running;
	  }
	  
	  // Setters for the data fields corresponding to the GUI elements.
	  public void setLog(JTextArea log)
	  {
	    this.log = log;
	  }
	  
	  public void setStatus(JLabel status)
	  {
	    this.status = status;
	  }

	  // When the server starts, update the GUI.
	  public void serverStarted()
	  {
	    running = true;
	    status.setText("Listening");
	    status.setForeground(java.awt.Color.green);
	    log.append("Server started\n");
	  }
	  
	  // When the server stops listening, update the GUI.
	   public void serverStopped()
	   {
	     status.setText("Stopped");
	   //  status.setForeground(Color.RED);
	     log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
	   }
	 
	  // When the server closes completely, update the GUI.
	  public void serverClosed()
	  {
	    running = false;
	    status.setText("Close");
	    //status.setForeground(Color.RED);
	    log.append("Server and all current clients are closed - press Listen to restart\n");
	  }

	  // When a client connects or disconnects, display a message in the log.
	  public void clientConnected(ConnectionToClient client)
	  {
	    log.append("Client " + client.getId() + " connected\n");
	    connectedPlayers.add(client);

	  }
	
	  // Method that handles listening exceptions by displaying exception information.
	  public void listeningException(Throwable exception) 
	  {
	    running = false;
	    status.setText("Exception occurred while listening");
	   // status.setForeground(Color.RED);
	    log.append("Listening exception: " + exception.getMessage() + "\n");
	    log.append("Press Listen to restart server\n");
	  }

	@Override
	/*HANDLE MESSAGE FROM CLIENT*/
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		
		//HANDLE LOGINDATA
	    if (arg0 instanceof LoginData)
	    {
	      // Check the username and password with the database.
	      LoginData data = (LoginData)arg0;
	      Object result;
	      
	      /*IF Username and Password are Valid, then Display Successful Login Message in Serer Log*/
	      if (database.verifyAccount(data))
	      {
	        result = "LoginSuccessful";
	        log.append("Client " + arg1.getId() + " successfully logged in as " + data.getPlayerName() + "\n");
	        playersLoggedIn.add(data.getPlayerName());

	      }
	      else
	      {
	        result = new Error("The username and password are incorrect.", "Login");
	        log.append("Client " + arg1.getId() + " failed to log in\n");
	      }
	      
	      // Send the result to the client.
	      try
	      {
	        arg1.sendToClient(result);
	        arg1.sendToClient(playersLoggedIn);

	      }
	      catch (IOException e)
	      {
	        return;
	      }//end logindata

	    }
		
	    //HANDLE CREATEACCOUNTDATA
	    if (arg0 instanceof CreateAccountData)
	    {
	      // Check the username and password with the database.
	    	CreateAccountData data = (CreateAccountData)arg0;
	      Object result;
	      if (database.CreateAccount(data))
	      {
	        result = "CreateAccountSuccessful";
	        log.append("Client " + arg1.getId() + " created a new account called " + data.getPlayerName() + "\n");
	      }
	      else
	      {
	        result = new Error("The username or password has already been selected.", "CreateAccount");
	        log.append("Client " + arg1.getId() + " failed to log in:\nUsername or password has already been selected");
	      }
	      
	      // Send the result to the client.
	      try
	      {
	        arg1.sendToClient(result);
	      }
	      catch (IOException e)
	      {
	        return;
	      }
	    }//end createaccountdata
	    
	    //HANDLE GAMEROOMDATA
	    if(arg0 instanceof GameroomData) {
			GameroomData data = (GameroomData)arg0;
		    Object result;
		    
		    if(playersLoggedIn.contains(data.getOpponent()))
		    {			
		    	result = "PlayerInviteSuccessful";
		    	log.append("Client " + data.getPlayerName() + " invited " + data.getOpponent() + " to a game.\n");
		    }
		    else {
		    	result = new Error("The opponent is not logged in.", "Gameroom");
		    	log.append("Client " + data.getPlayerName() + " failed to invite " + data.getOpponent() + ".\n");
		    }
		    
		}    
	   
	    if (arg0 instanceof String)
	    {
	      // Get the text of the message.
	      String message = (String)arg0;
	      
	      //Handle PlayGame
	      if (message.equals("PlayGame"))
	      {
	    	  Object result = "PlayGame";
		      log.append("Client " + arg1.getId() + " pressed play game " + "\n");
		      // Send the result to the client.
		      try
		      {
		        arg1.sendToClient(result);
		      }
		      catch (IOException e)
		      {
		    	log.append("Client " + arg1.getId() + " server ioexception in sending result " + "\n");
		        return;
		      }
	      }
	      
	     /* //Handle PlayGame
	      if (message.equals("PlayGame"))
	      {
	    	  
	    	  GamescreenControl gsc = new GamescreenControl();
	    	  
		      // Send the result to the client.
		      try
		      {
		        arg1.sendToClient(gsc);
		      }
		      catch (IOException e)
		      {
		    	log.append("Client " + arg1.getId() + " server ioexception in sending result " + "\n");
		        return;
		      }
	      
	      }*/
	    }
		
	    
	    //HANDLE GAMESCREEN DATA
	    if (arg0 instanceof GamescreenData)
	    {
	   
	      try
	      {
	        arg1.sendToClient(arg0);
	      }
	      catch (IOException e)
	      {
	    	log.append("Client " + arg1.getId() + " server ioexception in sending result " + "\n");
	        return;
	      }
	    }
  
	    
	}
	
}
