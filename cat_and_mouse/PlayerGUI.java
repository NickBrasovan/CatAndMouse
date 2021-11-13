package cat_and_mouse;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PlayerGUI extends JFrame{
	String title;
	static CardLayout cl; //declare cardLayout;
	
	/*INSTANTIATE PLAYER OBJECT*/
	PlayerClient player = new PlayerClient();
	
	//Declare Panels To Build GUIs
	private BasePanel bp; //extends JPanel
	private Titlescreen ts; //extends JPanel
	private Gameroom gr; //extends JPanel
	private CreateAccount ca; //extends JPanel
	
	private TitlescreenControl tc; //extends actionlistener
	private CreateAccountControl cac; //extends actionlistener
	
	//Constructor for Player GUI sets card layout
	public PlayerGUI(){
		//set JFrame Parameters
		title = "Cat and Mouse";
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(800, 900);
		this.setVisible(true);
		this.setResizable(false);
		
		//instantiate panel objects
		cl = new CardLayout(); //instantiate new CardLayout Object
		bp = new BasePanel(cl);

		tc = new TitlescreenControl(bp, player);
		ts = new Titlescreen(tc);
		
		gr = new Gameroom();
		
		cac = new CreateAccountControl(bp);
		ca = new CreateAccount(cac);

		//add panel layers to card layout
		bp.add(ts, "1");
		bp.add(gr, "2");
		bp.add(ca, "3");
		
		//show title screen on top of base panel
		cl.show(bp, "1");
		
		//add base panel and cardlayout to JFrame
		this.add(bp);
		this.pack();
		
	}//end constructor
	
	/**Main Driver of PlayerClient Side**/
	public static void main(String[] args) {
	
	new PlayerGUI();
			
	}
}
