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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String command = arg0.getActionCommand();
		
		if(command.equals("Invite A Friend")) {
			Titlescreen user = (Titlescreen)container.getComponent(0);
			String username = user.getPlayerName();
			
			Gameroom opponent = (Gameroom)container.getComponent(1);
			String opponentUsername = opponent.getOpponent();
			
			GameroomData data = new GameroomData(username, opponentUsername);
			
			try {
				this.player.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if (command.equals("Log Out")) {
			CardLayout cardLayout = (CardLayout)container.getLayout();
			Titlescreen titleScreen = (Titlescreen)container.getComponent(0);
			String user = titleScreen.getPlayerName();
			Titlescreen.setError("");
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
		
		/*Refresh Button Handler*/
		else if (command.equals("Refresh")) {
			try {
				player.sendToServer("Refresh");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		// Play Game Tester
		if(command.equals("Play Game")) {
			
			//TESTER
			GamescreenData gsdata = new GamescreenData();
			
			
			try {
				player.sendToServer(gsdata);  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
		} 
	}
	
	public void setLog(ArrayList<String> players) {
		Gameroom gameroom = (Gameroom)container.getComponent(1);
		
		gameroom.setArea(players);
		
	}
	
	

}
