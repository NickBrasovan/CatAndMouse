package cat_and_mouse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.mysql.fabric.Server;

import java.io.IOException;
import java.util.*;
import javax.swing.Timer;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;



public class GameServer extends AbstractServer {
	 
	// Data fields for this chat server.
	  private JTextArea log;
	  private JLabel status;
	  private boolean running = false;
	  private Database database;
	  private boolean player1RTP = false;
	  private boolean player2RTP = false;
	  private String player1;
	  private String player2;
	  private String player1Character;
	  private String player2Character;
	  public int seconds = 30;
	  private ArrayList<String> playersLoggedIn;
	  private ArrayList<ConnectionToClient> connectedPlayers;
	  private Timer gameTimer;

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
		//log.append("LoginData object received from Client");
		
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
	        //arg1.sendToClient(playersLoggedIn);
	      }	      
	      catch (IOException e)
	      {
	        return;
	      }
	    }
		
	    /*Handle CreateAccountData Object*/
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
	    }
		
	    /*Handle GameroomData Object*/
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
		
		
		/*Handle String Messages*/
		if(arg0 instanceof String) {
			String[] message = ((String) arg0).split(",");			
			
			//handle logout
			if(message[0].equals("Log Out")) {
				playersLoggedIn.remove(message[1]);
				log.append("Client " + message[1] + " has logged out.\n");
				
				//send arraylist of playersLoggedIn to client
				try {
					arg1.sendToClient(playersLoggedIn);
				} catch (IOException e) {
				}
			}
			//handle refresh
			if(message[0].equals("Refresh")) {
				try {
					arg1.sendToClient(playersLoggedIn);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			//Handle PlayGame
		    if (message[0].equals("PlayGame"))
		    {
		    	log.append("Client " + arg1.getId() + " pressed play game " + "\n");
		    	
		    	if(player1RTP == false) {
		    		player1 = message[1];
		    		player1RTP = true;
		    		player1Character = randomAssign();
		    	}
		    	else if(player2RTP == false) {
		    		player2 = message[1];
		    		player2RTP = true;
		    		if(player1Character.equals("Mouse"))
		    			player2Character = "Cat";
		    		else if(player1Character.equals("Cat"))
		    			player2Character = "Mouse";
		    	}
		    	
			    while(!player2RTP) {
			    	try {
			    		log.append("Waiting for player2\n");
						wait(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
		    	
		    	if(player1RTP && player2RTP) {
		    		if(player1.equals(message[1]))
						try {
							arg1.sendToClient(player1Character);
							this.gameTimer = new Timer(1000, this.timePassed);
							gameTimer.start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		else if(player2.equals(message[1]))
						try {
							arg1.sendToClient(player2Character);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    		//startClock();
		    	}
		      }
		    if(message[0].equals("CatWin")) {
		    	player1 = "";
		    	player2 = "";
		    	player1Character = "";
		    	player2Character = "";
		    	player1RTP = false;
		    	player2RTP = false;
		    	seconds = 30;
		    	gameTimer.stop();
		    	sendToAllClients("CatWin");
		    }
		    
		    if(message[0].equals("MouseWin")) {
		    	player1 = "";
		    	player2 = "";
		    	player1Character = "";
		    	player2Character = "";
		    	player1RTP = false;
		    	player2RTP = false;
		    	seconds = 30;
		    	gameTimer.stop();
		    	sendToAllClients("MouseWin");
		    }
		    }
			
		    //HANDLE GAMESCREEN DATA
		    if (arg0 instanceof GamescreenData)
		    {
		    		sendToAllClients(arg0);
			//send to all clients
		    }
		   
	}
	
	public String randomAssign() {
		
		int random = (int)(Math.random()*2);
		String character = "";
		
		if(random==0)
			character = "Mouse";
		else if(random==1)
			character = "Cat";
		
		return character;
	}
	
	private ActionListener timePassed = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  seconds--;
			  sendToAllClients(seconds);
		  }
	  };
	
}
