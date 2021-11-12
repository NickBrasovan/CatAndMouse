package cat_and_mouse;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class CreateAccountControl implements ActionListener{
	  // Private data field for storing the container.
	  private JPanel container;
	 
	  // Constructor for the controller.
	  public CreateAccountControl(JPanel container)
	  {
	    this.container = container; 
	  }
	  
	// Handle button clicks.
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent new_acct_ae){

	    //get data from titlescreen object
	    CreateAccount create_account = (CreateAccount)container.getComponent(2);  //Component 2 must refer to the 3rd element in the CardLayout array of Japanels
	    CreateAccountData data = new CreateAccountData(create_account.getPlayerName(), create_account.getPassword(), create_account.getPasswordMatch()); 
	    /*//Data integrity check
	    System.out.print("Player Name in CreateAccountData is " + data.getPlayerName());
    	System.out.print("\nPassword in CreateAccountData is " + data.getPassword());
    	System.out.print("\nPassword Match in CreateAccountData is " + data.getPasswordMatch());*/
	  }

	public void createAccountSuccess() {
		CardLayout cardLayout = (CardLayout)container.getLayout();
	    cardLayout.show(container, "0"); //System returns user 
		
	}
}
