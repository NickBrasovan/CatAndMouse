//Server
package cat_and_mouse;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class GameServerGUI extends JFrame {
	 // Data fields.
	  private JLabel status;
	  private String[] labels = {"Port #", "Timeout"};
	  private JTextField[] textFields = new JTextField[labels.length];
	  private JTextArea log;
	  private JButton listen;
	  private JButton close;
	  private JButton stop;
	  private JButton quit;
	  private GameServer server;	/*SERVER OBJECT*/
	  private Database database;   /*DATABASE OBJECT*/

	  	/*Constructor for the server GUI.*/
		public GameServerGUI()
		{	
		//Set the title and default close operation.
	    this.setTitle("Game Server");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    /*INSTANTIATE SERVER*/
	    server = new GameServer();
	    
	    /*INSTANTIATE DATABASE*/
	    try {
			database = new Database();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Can't instantiate database \n StackTrace:");
			e.printStackTrace();
		}
	    /*PASS DATABASE OBJECT TO SERVER VIA SERVER SETDATABASE METHOD*/
	    server.setDatabase(database);  
		
	    /*PANEL CONSTRUCTION*/
	    JPanel north = new JPanel();
	    JPanel center = new JPanel(new BorderLayout());
	    JPanel south = new JPanel();
	    
	    //Status Label. Default status: "not connected"
	    JLabel statusText = new JLabel("Status:");
	    north.add(statusText);
	    status = new JLabel("Not Connected");
	    status.setForeground(Color.RED);
	    north.add(status);
	    server.setStatus(status);
	    
	    //Create the grid of text fields.
	    JPanel centerNorth = new JPanel(new GridLayout(labels.length, 2, 5, 5));
	    for (int i = 0; i < textFields.length; i++)
	    {
	      JLabel label = new JLabel(labels[i], JLabel.RIGHT);
	      centerNorth.add(label);
	      textFields[i] = new JTextField(10);
	      centerNorth.add(textFields[i]);
	    }

	    //Set default values for the server.
	    textFields[0].setText("8300");
	    textFields[1].setText("500");

	    //Buffer the grid of text fields and add it to the north part of the center.
	    JPanel centerNorthBuffer = new JPanel();
	    centerNorthBuffer.add(centerNorth);
	    center.add(centerNorthBuffer, BorderLayout.NORTH);
	    
	    /*SERVER LOG PANEL*/
	    JPanel serverLogPanel = new JPanel(new BorderLayout());
	    JLabel serverLabel = new JLabel("Server Log", JLabel.CENTER);
	    JPanel serverLabelBuffer = new JPanel();
	    serverLabelBuffer.add(serverLabel);
	    serverLogPanel.add(serverLabelBuffer, BorderLayout.NORTH);
	    log = new JTextArea(10, 35);
	    log.setEditable(false);
	    JScrollPane serverLogPane = new JScrollPane(log);
	    JPanel serverLogPaneBuffer = new JPanel();
	    serverLogPaneBuffer.add(serverLogPane);
	    serverLogPanel.add(serverLogPaneBuffer, BorderLayout.SOUTH);
	    server.setLog(log);
	    
	    //Add the server log panel to the south part of the center.
	    JPanel centerSouth = new JPanel();
	    centerSouth.add(serverLogPanel);
	    center.add(centerSouth, BorderLayout.SOUTH);

	    /*SERVER GUI BUTTONS*/
	    EventHandler handler = new EventHandler();
	    listen = new JButton("Listen");
	    listen.addActionListener(handler);
	    south.add(listen);
	    close = new JButton("Close");
	    close.addActionListener(handler);
	    south.add(close);
	    stop = new JButton("Stop");
	    stop.addActionListener(handler);
	    south.add(stop);
	    quit = new JButton("Quit");
	    quit.addActionListener(handler);
	    south.add(quit);
	    
	    // Add the north, center, and south JPanels to the JFrame.
	    this.add(north, BorderLayout.NORTH);
	    this.add(center, BorderLayout.CENTER);
	    this.add(south, BorderLayout.SOUTH);
	    
	    // Display the window.
	    this.setSize(450, 450);
	    this.setVisible(true);
		}//end constructor
		
		
		// Getters for the important components.
		public JTextField getTextFieldAt(int index)
		{
		  return textFields[index];
		}
		public JLabel getStatus()
	  {
		  return status;
	  }
		public JTextArea getLog()
	  {
		  return log;
	  }
		
		// Class for handling events.
		class EventHandler implements ActionListener
		{
		  // Event handler for ActionEvent.
	    public void actionPerformed(ActionEvent e)
	    {
	      // Determine which button was clicked.
	      Object buttonClicked = e.getSource();
	      
	      // Handle the Listen button.
	      if (buttonClicked == listen)
	      {
	        // Display an error if the port number or timeout was not entered.
	        if (textFields[0].getText().equals("") || textFields[1].getText().equals(""))
	        {
	          log.append("Port number or timeout not entered before pressing Listen\n");
	        }
	        
	        // Otherwise, tell the server to start listening with the user's settings.
	        else
	        {
	          server.setPort(Integer.parseInt(textFields[0].getText()));
	          server.setTimeout(Integer.parseInt(textFields[1].getText()));
	          try
	          {
	            server.listen();
	          }
	          catch (IOException e1)
	          {
	            log.append("An exception occurred: " + e1.getMessage() + "\n");
	          }
	        }
	      }
	      
	      // Handle the Close button.
	      else if (buttonClicked == close)
	      {
	        // Display an error if the server has not been started.
	        if (!server.isRunning())
	        {
	          log.append("Server not currently started\n");
	        }
	        
	        // Otherwise, close the server.
	        else
	        {
	          try
	          {
	            server.close();
	          }
	          catch (IOException e1)
	          {
	            log.append("An exception occurred: " + e1.getMessage() + "\n");
	          }
	        }
	      }
	      
	      // Handle the Stop button.
	      else if (buttonClicked == stop)
	      {
	        // Display an error if the server is not listening.
	        if (!server.isListening())
	        {
	          log.append("Server not currently listening\n");
	        }
	        
	        // Otherwise, stop listening.
	        else
	        {
	          server.stopListening();
	        }
	      }
	      
	      // For the Quit button, just stop this program.
	      else if (buttonClicked == quit)
	      {
	        System.exit(0);
	      }
	    }
		
	}
		

		//Main Driver of Server Side
		public static void main (String[] args) {

		new GameServerGUI();

	}
}
