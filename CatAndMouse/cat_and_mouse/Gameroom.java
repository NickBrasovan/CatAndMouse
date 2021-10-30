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
	private JLabel titleLabel;
	private JButton inviteBtn;
	private JButton acceptBtn;
	private JLabel instructLbl;
	
	//JTextField username_field;
	
	public Gameroom () {
	
		this.setBackground(Color.BLACK);
		setLayout(null);
		this.setVisible(true);
		
		//Screen Title
		titleLabel = new JLabel();
		titleLabel.setForeground(Color.green);
		titleLabel.setBackground(Color.GRAY);
		titleLabel.setText("Welcome to the Gameroom!");
		titleLabel.setBounds(250, 50, 500, 28);
		add(titleLabel);
		titleLabel.setVisible(true);
		
		//Screen Instructions
		instructLbl = new JLabel();
		instructLbl.setForeground(Color.green);
		instructLbl.setBackground(Color.GRAY);
		instructLbl.setText("Invite a Friend or Accept an Invitation to Play");
		instructLbl.setBounds(200, 80, 500, 28);
		add(instructLbl);
		instructLbl.setVisible(true);
		
		
		//Gameroom Panel Buttons
		inviteBtn = new JButton("Invite a Friend");
		inviteBtn.setBounds(500, 625, 122, 33);
		this.add(inviteBtn);
		
		acceptBtn = new JButton("Accept an Invitation");
		inviteBtn.setBounds(300, 625, 152, 33);
		this.add(inviteBtn);
		//btnLogin.addActionListener(new loginListener());

		/*seeScoresBtn = new JButton("See Scores");
		acceptBtn.setBounds(150, 625, 122, 33);
		add(acceptBtn); */
	
	}

}