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
    	
    	//Data integrity check
    	//System.out.print("Player Name in LoginData Object is " + data.getPlayerName());
    	//System.out.print("\nPassword in LoginData Object is " + data.getPassword());
    	
    	//Show Gameroom JPanel
    	CardLayout cardLayout = (CardLayout)container.getLayout();
        cardLayout.show(container, "2"); //container 2 is Gameroom.
        }
    
    //NewAccount Button Action Handler
    if (command.equals("New Account")){
    	CardLayout cardLayout = (CardLayout)container.getLayout();
        cardLayout.show(container, "3");
        }
    }
}