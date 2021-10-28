package cat_and_mouse_3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Titlescreen extends JPanel {
		
	
	private JTextField userIDField = new JTextField();
	private JPasswordField userPasswordField = new JPasswordField();
	
	
	
	
	
	
	JLabel userIDLabel;
	JLabel userPasswordLabel;
	JLabel messageLabel = new JLabel();
	JLabel enterPasswordLabel;
	
	//Titlescreen Buttons
	JButton btnNewAcct;
	JButton btnLogin;
	JLabel instruction1;
	JLabel instruction2;
	JLabel instruction3;
	
	//IDandPasswords id_password_map;	
	
	
	//HashMap<String,String> logininfo;	
			
	//Construct Titlescreen		
		public Titlescreen(TitlescreenControl tc) {
			
			//logininfo = id_password_map.getLoginInfo();
			
			
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
			
			
			// Main Panel Buttons
			
			userIDLabel = new JLabel("  userID:");
			userIDLabel.setBounds(200, 500, 75, 25);
			userIDLabel.setOpaque(true);
			userIDField.setBounds(275, 500, 200, 25);
			
			userPasswordLabel = new JLabel("  password:");
			userPasswordLabel.setOpaque(true);
			userPasswordLabel.setBounds(200, 530, 75, 25);
			userPasswordField.setBounds(275, 530, 200, 25);
			
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
			
			
			this.add(userIDField);
			this.add(userPasswordField);
			this.add(userIDLabel);
			this.add(userPasswordLabel);
			
			
		}//end Panel Constructor
	
		
		
			
				
				
				
		
		
}
		
		//private class newAccountListener implements ActionListener {
				//	public void actionPerformed(ActionEvent e) {
				//	}
				
		//}

