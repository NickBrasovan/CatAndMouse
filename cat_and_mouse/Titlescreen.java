package cat_and_mouse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Titlescreen extends JPanel {
		
	//Titlescreen Text Fields
	private JTextField playerNameField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	
	//Titlescreen Labels
	private JLabel welcomeLbl;
	private static JLabel errorLabel;
	private JLabel playerNameLabel;
	private JLabel passwordLabel;
	private JLabel messageLabel;
	private JLabel enterPasswordLabel;
	private JLabel instruction1;
	private JLabel instruction2;
	private JLabel instruction3;
	
	//Titlescreen Buttons
	private JButton btnNewAcct;
	private JButton btnLogin;
	
	//Getter for Player Name
	public String getPlayerName() {
		return playerNameField.getText();
	}
	
	// Getter for the text in the password field.
	public String getPassword(){
	    return new String(passwordField.getPassword());
	  }
	  
			
	//Construct Titlescreen		
		public Titlescreen(TitlescreenControl tc) {
			
			
			this.setBackground(Color.BLUE);
			//this.setPreferredSize(new Dimension(800, 900));
			this.setVisible(true);
			this.setLayout(null);
					
			
			JLabel welcomeLbl = new JLabel("         Welcome to CAT & MOUSE"); // welcome label
			welcomeLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
			welcomeLbl.setBounds(250, 11, 245, 41);
			welcomeLbl.setForeground(Color.WHITE);
			//welcomeLbl.setOpaque(true);
			//welcomeLbl.setBackground(Color.LIGHT_GRAY);
			add(welcomeLbl);
			
			instruction1 = new JLabel("    Login with Username and Password"); // instruction label
			instruction1.setFont(new Font("Tahoma", Font.BOLD, 12));
			instruction1.setForeground(Color.WHITE);
			instruction1.setBounds(240, 63, 267, 41);
			//instruction1.setOpaque(true);
			//instruction1.setBackground(Color.LIGHT_GRAY);
			add(instruction1);

			
			instruction2 = new JLabel("                           or "); // instruction label
			instruction2.setFont(new Font("Tahoma", Font.BOLD, 12));
			instruction2.setBounds(240, 113, 267, 35);
			instruction2.setForeground(Color.WHITE);
			//instruction2.setOpaque(true);
			//instruction2.setBackground(Color.LIGHT_GRAY);
			add(instruction2);
			
			instruction3 = new JLabel("              Create a New Account"); // instruction label
			instruction3.setFont(new Font("Tahoma", Font.BOLD, 12));
			instruction3.setBounds(240, 153, 267, 35);
			instruction3.setForeground(Color.WHITE);
			//instruction2.setOpaque(true);
			//instruction2.setBackground(Color.LIGHT_GRAY);
			add(instruction3);
			
			
			// Create a panel for the labels at the top of the GUI.
		    JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		    errorLabel = new JLabel("", JLabel.CENTER);
		    errorLabel.setForeground(Color.RED);
		    JLabel instructionLabel = new JLabel("Enter your username and password to log in.", JLabel.CENTER);
		    labelPanel.add(errorLabel);
		    //labelPanel.add(instructionLabel);
			
			
			// Main Panel Buttons
			
			playerNameLabel = new JLabel("  Player Name:");
			playerNameLabel.setBounds(200, 500, 75, 25);
			playerNameLabel.setOpaque(true);
			playerNameField.setBounds(275, 500, 200, 25);
			
			passwordLabel = new JLabel("  Password:");
			passwordLabel.setOpaque(true);
			passwordLabel.setBounds(200, 530, 75, 25);
			passwordField.setBounds(275, 530, 200, 25);
			
			btnLogin = new JButton("Login");
			btnLogin.setBounds(500, 625, 122, 33);
			this.add(btnLogin);
			btnLogin.addActionListener(tc);

			btnNewAcct = new JButton("New Account");
			btnNewAcct.setBounds(150, 625, 122, 33);
			add(btnNewAcct);
			btnNewAcct.addActionListener(tc);
			
			
		
			//this.add(login_screen);
			
			//loginButton.setBounds(125, 200, 100, 25);
			//loginButton.addActionListener(this);
			
			//resetButton.setBounds(225, 200, 100, 25);
			//resetButton.addActionListener(this);
			
			
			this.add(playerNameField);
			this.add(passwordField);
			this.add(playerNameLabel);
			this.add(passwordLabel);
			
			
		}//end Panel Constructor
	
		
		
		 // Setter for the error text.
		  public static void setError(String error)
		  {
		    errorLabel.setText(error);
		  }	
				
				
				
		
		
}