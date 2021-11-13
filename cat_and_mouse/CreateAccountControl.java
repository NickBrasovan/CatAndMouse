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
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent ae){
		String command = ae.getActionCommand();
	    //get data from titlescreen object
		if(command == "Submit") {
			CreateAccount create_account = (CreateAccount)container.getComponent(2);  //Component 2 must refer to the 3rd element in the CardLayout array of Japanels
			String username = create_account.getName();						// Comment to see change 
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
			//CreateAccountData data = new CreateAccountData(create_account.getPlayerName(), create_account.getPassword(), create_account.getPasswordMatch()); 
		}
		else if (command == "Exit") {
			CardLayout cL = (CardLayout)container.getLayout();
			cL.show(container, "0");
		}
		/*//Data integrity check
	    System.out.print("Player Name in CreateAccountData is " + data.getPlayerName());
    	System.out.print("\nPassword in CreateAccountData is " + data.getPassword());
    	System.out.print("\nPassword Match in CreateAccountData is " + data.getPasswordMatch());*/
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
