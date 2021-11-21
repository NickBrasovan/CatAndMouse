package cat_and_mouse;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

public class GameroomControl implements ActionListener{

	private JPanel container;
	private PlayerClient player;
	
	public GameroomControl(JPanel container, PlayerClient player) {
		this.container = container;
		this.player = player;
	}
	
	public void playGameSuccess() {
		CardLayout cardLayout = (CardLayout)container.getLayout();
	    cardLayout.show(container, "4"); // 
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String command = arg0.getActionCommand();
		
		if(command.equals("Invite a Friend")) {
			Titlescreen user = (Titlescreen)container.getComponent(0);
			String username = user.getPlayerName();
			
			Gameroom opponent = (Gameroom)container.getComponent(1);
			String opponentUsername = opponent.getOpponent();
			
			GameroomData data = new GameroomData(username, opponentUsername);
			
			try {
				player.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if (command.equals("Log Out")) {
			CardLayout cardLayout = (CardLayout)container.getLayout();
			Titlescreen titleScreen = (Titlescreen)container.getComponent(0);
			String user = titleScreen.getPlayerName();
			titleScreen.replaceUsername();
			titleScreen.replacePassword();
			
			try {
				player.sendToServer("Log Out" + "," + user);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        cardLayout.show(container, "1");
		}
<<<<<<< HEAD
		
		if(command.equals("Play Game")) {
		
			//TESTER
			GamescreenData gsdata = new GamescreenData();
			
			
			try {
				player.sendToServer(gsdata);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
=======
		else if (command.equals("Refresh")) {
			try {
				player.sendToServer("Refresh");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void setLog(ArrayList<String> players) {
		Gameroom gameroom = (Gameroom)container.getComponent(2);
		
		gameroom.setArea(players);
		
	}
>>>>>>> refs/remotes/origin/master

		/*Tester for Play Game tied to playGameBtn on Gameroom Panel
		else if (command.equals("Play Game")) {
			CardLayout cardLayout = (CardLayout)container.getLayout();
			Gamescreen gamescreen = (Gamescreen)container.getComponent(3);
			
			try {
				player.sendToServer("Play Game");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        cardLayout.show(container, "4");
		}*/
	}
	}
}
