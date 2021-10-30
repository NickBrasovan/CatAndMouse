package cat_and_mouse;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreateAccount extends JPanel {

	private JLabel panelTitleLbl;
	private JLabel instructLbl;
	private JLabel playerNameLabel;
	private JTextField playerNameField;
	private JLabel passwordLabel;
	private JTextField passwordField;
	private JLabel passwordMatchLabel;
	private JTextField passwordMatchField;
	private JButton btnSubmit;

	//Getter for Player Name
	public String getPlayerName() {
		return playerNameField.getText();
		}
		
	// Getter for the text in the password field. 
	public String getPassword() {
		return new String(passwordField.getText()); //TODO fix "getText" to "getPassword"
	}
	
	// Getter for the text in the password field. 
	public String getPasswordMatch() {
		return new String(passwordMatchField.getText()); //TODO fix "getText" to "getPassword"
	}
	
	//CreateAccount Constructor. Constructor takes CreateAccountControl Object (cac) as an argument.  
	public CreateAccount(CreateAccountControl cac) {
	
		this.setBackground(Color.BLACK);
		setLayout(null);
		this.setVisible(true);
		
		//Screen Title
		panelTitleLbl = new JLabel();
		panelTitleLbl.setForeground(Color.green);
		panelTitleLbl.setBackground(Color.GRAY);
		panelTitleLbl.setText("CREATE ACCOUNT SCREEN - PROTOTYPE");
		panelTitleLbl.setBounds(250, 50, 500, 28);
		add(panelTitleLbl);
		panelTitleLbl.setVisible(true);
		
		//Screen Instructions
		instructLbl = new JLabel();
		instructLbl.setForeground(Color.green);
		instructLbl.setBackground(Color.GRAY);
		instructLbl.setText("Submit new name and password to create new account");
		instructLbl.setBounds(200, 80, 500, 28);
		add(instructLbl);
		instructLbl.setVisible(true);		
		
		//Create Account Panel Buttons
		playerNameLabel = new JLabel("//Player Name:");
		playerNameLabel.setBounds(200, 500, 75, 25);
		playerNameLabel.setOpaque(true);
		this.add(playerNameLabel);
		
		playerNameField = new JTextField();
		playerNameField.setBounds(275, 500, 200, 25);
		this.add(playerNameField);
	
		passwordLabel = new JLabel("  password:");
		passwordLabel.setOpaque(true);
		passwordLabel.setBounds(200, 530, 75, 25);
		this.add(passwordLabel);
	
		passwordMatchLabel = new JLabel("re-enter password:");
		passwordMatchLabel.setOpaque(true);
		passwordMatchLabel.setBounds(200, 560, 75, 25);
		this.add(passwordMatchLabel);
		
		passwordField = new JTextField();
		passwordField.setBounds(275, 530, 200, 25);
		this.add(passwordField);
		
		passwordMatchField = new JTextField();
		passwordMatchField.setBounds(275, 560, 200, 25);
		this.add(passwordMatchField);
	
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(300, 625, 122, 33);
		this.add(btnSubmit);
		btnSubmit.addActionListener(cac); //button action listener receives a CreateAccountControl Object.
	
	}
	
}
