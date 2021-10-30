package cat_and_mouse;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

//import cat_and_mouse_3.CLayout;

public class PlayerGUI extends JFrame{
	String title;
	static CardLayout cl; //declare cardLayout;
	
	//static IDandPasswords id_map;
	
	static BasePanel bp;
	static Titlescreen ts;
	static Gameroom gr;
	static CreateAccount ca;
	static TitlescreenControl tc;
	
	
	
	public PlayerGUI(){
		title = "Cat and Mouse";
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 900);
		//this.getContentPane().add(new BufferPanel());
		//this.getContentPane().add(bp);
		//this.getContentPane().add(ls);
		//this.pack();
		this.setVisible(true);
		this.setResizable(false);
		
		
		cl = new CardLayout(); //instantiate new CardLayout Object
		
		bp = new BasePanel(cl);
		tc = new TitlescreenControl(bp);
		ts = new Titlescreen(tc);
		gr = new Gameroom();
	
		//id_map = new IDandPasswords();
		//hm = id_map.getLoginInfo();
		ca = new CreateAccount();
		
		
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
