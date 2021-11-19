package cat_and_mouse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class Gameroom extends JPanel {
	
	
	//Widget Declarations
	private JLabel titleLabel;
	private JButton inviteBtn;      
	//private JButton acceptBtn;      //TODO the accept button should only appear if player has active invitation
	private JLabel instructLbl1;   //invite friend label
	private JLabel instructLbl2;  //friends text area label
	private GameroomControl gc;
	
	private JTextArea friends_area; //displays list of player_users currently logged into Gameroom. 
	
	public String getOpponent() {
		return friends_area.getSelectedText();
	}
	
	public void setArea(ArrayList<String> players) {
		for(int i = 0; i < players.size(); i++) {
			friends_area.append(players.get(i) + "\n");
		}
		
	}
	
	public Gameroom (GameroomControl gc) {
	
		this.gc = gc;
		this.setBackground(Color.BLACK);
		setLayout(null);
		this.setVisible(true);
		
		//Screen Title
		titleLabel = new JLabel();
		titleLabel.setForeground(Color.green);
		titleLabel.setBackground(Color.GRAY);
		titleLabel.setText("WELCOME TO THE GAMEROOM!");
		titleLabel.setBounds(300, 50, 400, 36);
		this.add(titleLabel);
		titleLabel.setVisible(true);
		
		//Screen Instructions Label
		instructLbl1 = new JLabel();
		instructLbl1.setForeground(Color.green);
		instructLbl1.setBackground(Color.GRAY);
		instructLbl1.setText("Invite a Friend or Accept an Invitation to Play");
		instructLbl1.setBounds(250, 80, 500, 36);
		this.add(instructLbl1);
		instructLbl1.setVisible(true);
		
		//Friends Text Area Label
		instructLbl2 = new JLabel();
		instructLbl2.setForeground(Color.green);
		instructLbl2.setBackground(Color.GRAY);
		instructLbl2.setText("Friends Online");
		instructLbl2.setBounds(200, 160, 500, 36);
		this.add(instructLbl2);
		instructLbl2.setVisible(true);
		
		//Invite a Friend TextArea + scrollPane
		friends_area = new JTextArea(10,30);
		JScrollPane scrollPane = new JScrollPane(friends_area);
		scrollPane.setBounds(200,200,400,500);
		this.add(scrollPane);
		scrollPane.setVisible(true);
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.setBounds(500, 710, 100, 20);
		this.add(refreshButton);
		refreshButton.addActionListener(gc);
		
		//InviteFriend Button
		JButton logoutBtn = new JButton("Log Out");
		logoutBtn.setBounds(470, 825, 300, 33);
		this.add(logoutBtn);
		logoutBtn.addActionListener(gc);
		
		//Accept Button: TODO a more robust gui for accept/decline invitation
		inviteBtn = new JButton("Invite A Friend");
		inviteBtn.setBounds(30, 825, 300, 33);
		this.add(inviteBtn);
		inviteBtn.addActionListener(gc);
		//btnLogin.addActionListener(new loginListener());

		/*seeScoresBtn = new JButton("See Scores");
		acceptBtn.setBounds(150, 625, 122, 33);
		add(acceptBtn); */
	
	}

}