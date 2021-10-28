package cat_and_mouse_3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateAccount extends JPanel {

	JLabel welcomeLbl;
	JLabel instruction1;
	JLabel instruction2;
	JLabel instruction3;
	JLabel userIDLabel;
	JTextField userIDField;
	JLabel userPasswordLabel1;
	JTextField userPasswordField1;
	JLabel userPasswordLabel2;
	JTextField userPasswordField2;
	JButton btnSubmit;
	
	public CreateAccount() {
	
		this.setBackground(Color.BLACK);
		
		
		//this.setLayout(new BorderLayout());
		
		this.setPreferredSize(new Dimension(800, 800));
		setLayout(null);
		this.setVisible(true);
		
		//this.setVisible(true);
		
		//Screen Title
		welcomeLbl = new JLabel();
		welcomeLbl.setForeground(Color.green);
		welcomeLbl.setBackground(Color.GRAY);
		welcomeLbl.setText("CREATE ACCOUNT SCREEN - PROTOTYPE");
		welcomeLbl.setBounds(250, 50, 500, 28);
		add(welcomeLbl);
		welcomeLbl.setVisible(true);
		
		
		// Main Panel Buttons
	
		userIDLabel = new JLabel("  userID:");
		userIDLabel.setBounds(200, 500, 75, 25);
		userIDLabel.setOpaque(true);
		this.add(userIDLabel);
		
		userIDField = new JTextField();
		userIDField.setBounds(275, 500, 200, 25);
		this.add(userIDField);
	
	
		userPasswordLabel1 = new JLabel("  password:");
		userPasswordLabel1.setOpaque(true);
		userPasswordLabel1.setBounds(200, 530, 75, 25);
		this.add(userPasswordLabel1);
	
		userPasswordLabel2 = new JLabel("re-enter password:");
		userPasswordLabel2.setOpaque(true);
		userPasswordLabel2.setBounds(200, 560, 75, 25);
		this.add(userPasswordLabel2);
		
		userPasswordField1 = new JTextField();
		userPasswordField1.setBounds(275, 530, 200, 25);
		this.add(userPasswordField1);
		
		userPasswordField2 = new JTextField();
		userPasswordField2.setBounds(275, 560, 200, 25);
		this.add(userPasswordField2);
	
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(300, 625, 122, 33);
		this.add(btnSubmit);
		
	//btnLogin.addActionListener(new loginListener());


	//this.add(login_screen);
	
	//loginButton.setBounds(125, 200, 100, 25);
	//loginButton.addActionListener(this);
	
	//resetButton.setBounds(225, 200, 100, 25);
	//resetButton.addActionListener(this);
	
	/*
	this.add(userIDField);
	this.add(userPasswordField);
	this.add(userIDLabel);
	this.add(userPasswordLabel);*/
	
	
	}
	
}
