package cat_and_mouse;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;

public class TitlescreenControl implements ActionListener
{
  // Private data field for storing the container.
  private JPanel container;
  private PlayerClient player;
 
  // Constructor for the initial controller.
  public TitlescreenControl(JPanel container, PlayerClient player)
  {
    this.container = container;
    this.player = player;
  }
  
  // Handle button clicks.
  public void actionPerformed(ActionEvent ae){
    // Get the name of the button clicked.
    String command = ae.getActionCommand();
    
    //Login Button Action Handler
    if (command.equals("Login")){
    	//get data from titlescreen object
    	Titlescreen titlescreen = (Titlescreen)container.getComponent(0);
    	String username = titlescreen.getPlayerName();
    	String password = titlescreen.getPassword();
    	
    	if(username.equals("")||password.equals("")) {
    		displayError("Enter a username and password");
    	}
    	
    	LoginData data = new LoginData(username, password);
    	//Data integrity check
    	//System.out.print("Player Name in LoginData Object is " + data.getPlayerName());
    	//System.out.print("\nPassword in LoginData Object is " + data.getPassword());
    	
    	try {
    		player.sendToServer(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
    
    //NewAccount Button Action Handler
    if (command.equals("New Account")){
    	CardLayout cardLayout = (CardLayout)container.getLayout();
    	CreateAccount cA = (CreateAccount)container.getComponent(2);
    	cA.replaceUsername();
    	cA.replacePassword();
    	cA.replaceVerify();
        cardLayout.show(container, "3");
        }
    }

  //login success called by PlayerClient
  public void loginSuccess() {
  	
	//Successful Login results in Showing Gameroom JPanel
	//Titlescreen loginPanel = (Titlescreen)container.getComponent(1);
	CardLayout cardLayout = (CardLayout)container.getLayout();
	cardLayout.show(container, "2"); //container 2 is Gameroom.	
	
  }

	  // Method that displays a message in the error label.
	  public void displayError(String error)
	  {
	    //LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
	    Titlescreen.setError(error);
	  }
	
  }
