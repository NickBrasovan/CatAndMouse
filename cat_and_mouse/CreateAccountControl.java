package cat_and_mouse;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

public class CreateAccountControl implements ActionListener{
	  // Private data field for storing the container.
	  private JPanel container;
	  private PlayerClient player;
	 
	  // Constructor for the controller.
	  public CreateAccountControl(JPanel container, PlayerClient player)
	  {
	    this.container = container;
	    this.player = player;
	  }
	  
	// Handle button clicks.
	@Override
	public void actionPerformed(ActionEvent ae){
		String command = ae.getActionCommand();
	    //get data from titlescreen object
		if(command == "Submit") {
			CreateAccount create_account = (CreateAccount)container.getComponent(2);  //Component 2 must refer to the 3rd element in the CardLayout array of Japanels
			String username = create_account.getPlayerName();					// Comment to see change 
			String password = create_account.getPassword();
			String verifyPassword = create_account.getPasswordMatch();
			
			if(username.equals("") || password.equals("")) {
				displayError("Enter a username and password");
				return;
			}
			else if (!password.equals(verifyPassword)){
				displayError("Passwords do not match");
				return;
			}
			
			CreateAccountData data = new CreateAccountData(username, password, verifyPassword);
			
			try {
				player.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			CardLayout cL = (CardLayout)container.getLayout();
			Titlescreen titleScreen = (Titlescreen)container.getComponent(0);
			titleScreen.replaceUsername();
			titleScreen.replacePassword();
			cL.show(container, "1");
		}
		else if (command == "Exit") {
			CardLayout cL = (CardLayout)container.getLayout();
			Titlescreen titleScreen = (Titlescreen)container.getComponent(0);
			titleScreen.replaceUsername();
			titleScreen.replacePassword();
			cL.show(container, "1");
		}
	  }

	public void createAccountSuccess() {
		CardLayout cardLayout = (CardLayout)container.getLayout();
	    cardLayout.show(container, "0"); //System returns user 
		
	}
	
	public void displayError(String error) {
		CreateAccount createAccount = (CreateAccount)container.getComponent(2);
		createAccount.setError(error);
	}
}
