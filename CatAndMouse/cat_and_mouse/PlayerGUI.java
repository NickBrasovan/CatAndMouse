package cat_and_mouse;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

//import cat_and_mouse_3.CLayout;

public class PlayerGUI extends JFrame{
	String title;
	static CardLayout cl; //declare cardLayout;
	
	
	//Declare Panels To Build GUIs
	static BasePanel bp; //extends JPanel
	static Titlescreen ts; //extends JPanel
	static Gameroom gr; //extends JPanel
	static CreateAccount ca; //extends JPanel
	static TitlescreenControl tc; //extends actionlistener
	static CreateAccountControl cac; //extends actionlistener
	
	
	public PlayerGUI(){
		
		//set JFrame Parameters
		title = "Cat and Mouse";
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 900);
		this.setVisible(true);
		this.setResizable(false);
		
		
		cl = new CardLayout(); //instantiate new CardLayout Object
		
		bp = new BasePanel(cl);
		tc = new TitlescreenControl(bp);
		ts = new Titlescreen(tc);
		gr = new Gameroom();
		cac = new CreateAccountControl(bp);
		ca = new CreateAccount(cac);
		//id_map = new IDandPasswords();
		//hm = id_map.getLoginInfo();
		
		
		
		bp.add(ts, "1");
		bp.add(gr, "2");
		bp.add(ca, "3");
		
		cl.show(bp, "1");
		
		
		this.add(bp);
		this.pack();
		
	}
	
	/*Main Driver of PlayerClient Side*/
	public static void main(String[] args) {
	
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			new PlayerGUI();
		}
	});
}
}
