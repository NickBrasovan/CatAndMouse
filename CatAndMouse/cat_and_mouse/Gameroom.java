package cat_and_mouse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Gameroom extends JPanel {
	
	
	//Widget Declarations
	JLabel messageLabel = new JLabel();
	JButton inviteBtn;
	JButton acceptBtn;
	//JTextField username_field;
	
	public Gameroom () {
	
		this.setBackground(Color.BLACK);
		
		this.setPreferredSize(new Dimension(800, 800));
		setLayout(null);
		this.setVisible(true);
		
		
		messageLabel.setForeground(Color.green);
		messageLabel.setBackground(Color.GRAY);
		messageLabel.setText("Login successful");
		messageLabel.setBounds(400, 400, 100, 28);
		add(messageLabel);
		messageLabel.setVisible(true);
		
		inviteBtn = new JButton("Invite a Friend");
		inviteBtn.setBounds(500, 625, 122, 33);
		this.add(inviteBtn);
		
		inviteBtn = new JButton("Accept an Invitation");
		inviteBtn.setBounds(300, 625, 152, 33);
		this.add(inviteBtn);
		//btnLogin.addActionListener(new loginListener());

		acceptBtn = new JButton("See Scores");
		acceptBtn.setBounds(150, 625, 122, 33);
		add(acceptBtn);
	
	}

}