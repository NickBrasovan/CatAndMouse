package cat_and_mouse;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class TitlescreenControl implements ActionListener
{
  // Private data field for storing the container.
  private JPanel container;
 
  // Constructor for the initial controller.
  public TitlescreenControl(JPanel container)
  {
    this.container = container;
   
  }
  
  // Handle button clicks.
  public void actionPerformed(ActionEvent ae){
    // Get the name of the button clicked.
    String command = ae.getActionCommand();
    
    //Login Button Action Handler
    if (command.equals("Login")){
    	//get data from titlescreen object
    	Titlescreen titlescreen = (Titlescreen)container.getComponent(0);
    	LoginData data = new LoginData(titlescreen.getPlayerName(), titlescreen.getPassword());
    	PlayerClient player = new PlayerClient();
    	
    	try {
			player.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	try {
			player.sendToServer(data);
			System.out.print("From Client: Object sent to server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//Data integrity check
    	//System.out.print("Player Name in LoginData Object is " + data.getPlayerName());
    	//System.out.print("\nPassword in LoginData Object is " + data.getPassword());
    	

        }
    
    //NewAccount Button Action Handler
    if (command.equals("New Account")){
    	CardLayout cardLayout = (CardLayout)container.getLayout();
        cardLayout.show(container, "3");
        }
    }

  //login success called by PlayerClient
  public void loginSuccess() {
  	
	//Successful Login results in Showing Gameroom JPanel
	CardLayout cardLayout = (CardLayout)container.getLayout();
	cardLayout.show(container, "2"); //container 2 is Gameroom.	
	
  }
}