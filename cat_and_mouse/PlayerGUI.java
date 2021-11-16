package cat_and_mouse;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class PlayerGUI extends JFrame{
	/*
	String title;
	static CardLayout cl; //declare cardLayout;
	
	
	//Declare Panels To Build GUIs
	static BasePanel bp; //extends JPanel
	static Titlescreen ts; //extends JPanel
	static Gameroom gr; //extends JPanel
	static CreateAccount ca; //extends JPanel
	static TitlescreenControl tc; //extends actionlistener
	static CreateAccountControl cac; //extends actionlistener
	*/
	//Constructor for Player GUI sets card layout
	public PlayerGUI(){
		//set JFrame Parameters
		PlayerClient player = new PlayerClient();
		player.setHost("localhost");
		player.setPort(8300);
		
		try {
			player.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//title = "Cat and Mouse";
		this.setTitle("Cat and Mouse");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CardLayout cL = new CardLayout(); //instantiate new CardLayout Object
		JPanel container = new JPanel(cL);
		
		//instantiate panel objects
		TitlescreenControl tc = new TitlescreenControl (container, player);
		CreateAccountControl cac = new CreateAccountControl(container, player);
		GameroomControl gc = new GameroomControl(container, player);
		
		//JPanel bp = new BasePanel(cL);
		JPanel ts = new Titlescreen(tc);
		JPanel gr = new Gameroom(gc);
		JPanel ca = new CreateAccount(cac);
		
		player.setTitlescreenControl(tc);
		player.setCreateAccountControl(cac);
		
		//add panel layers to card layout
		container.add(ts, "1");
		container.add(gr, "2");
		container.add(ca, "3");
		
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
