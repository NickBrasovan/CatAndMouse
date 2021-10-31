package cat_and_mouse;


import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {
	 
	// Data fields for this chat server.
	  private JTextArea log;
	  private JLabel status;
	  private boolean running = false;
	  //private Database database = new Database();

	  // Constructor for initializing the server with default settings.
	  public GameServer( )
	  {
	    super(8300);
	    this.setTimeout(500);
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
	  }
	
	  	//Helper function to check type of Object Passed In
		public String CheckObject (Object arg0) {
			String return_type;
			return_type = (arg0.getClass().getSimpleName());
			return return_type;
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
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		// TODO Auto-generated method stub
		  //String object_type = CheckObject(arg0);
			//if (object_type == "LoginData")
				//System.out.println("LoginData object received from Client")
		log.append("LoginData object received from Client");
	}
	
}
