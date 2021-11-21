package cat_and_mouse;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class PlayerGUI extends JFrame{

	//Constructor for Player GUI
	public PlayerGUI(){
		
		PlayerClient player = new PlayerClient();  /*NEW PLAYER*/
		
		player.setHost("localhost");
		player.setPort(8300);
		
		try {
			player.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setTitle("Cat and Mouse");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CardLayout cL = new CardLayout(); //instantiate new CardLayout Object
		JPanel container = new JPanel(cL);
		
		//instantiate controllers
		TitlescreenControl tc = new TitlescreenControl (container, player);
		CreateAccountControl cac = new CreateAccountControl(container, player);
		GameroomControl gc = new GameroomControl(container, player);
		GamescreenControl gsc = new GamescreenControl(container, player);

		//set controlers
		player.setTitlescreenControl(tc);
		player.setCreateAccountControl(cac);
		player.setGameroomControl(gc);
		player.setGamescreenControl(gsc);
		
		//JPanel bp = new BasePanel(cL);
		JPanel ts = new Titlescreen(tc);
		JPanel gr = new Gameroom(gc);
		JPanel ca = new CreateAccount(cac);
		JPanel gs = new Gamescreen(gsc);

		
		//add panel layers to card layout
		container.add(ts, "1");
		container.add(gr, "2");
		container.add(ca, "3");
		container.add(gs, "4");
		
		//show title screen on top of base panel
		cL.show(container, "1");
		
		//add base panel and cardlayout to JFrame
		this.add(container);
		this.pack();
		
		this.setSize(800, 900);
		this.setVisible(true);
		this.setResizable(false);
	}//end constructor
	
	/**Main Driver of PlayerClient Side**/
	public static void main(String[] args) {
		new PlayerGUI();		
	}
	
}
