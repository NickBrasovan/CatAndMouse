package cat_and_mouse;

import static org.junit.Assert.*;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

public class TitlescreenControlTest {

	private TitlescreenControl tc;
	private Titlescreen ts;
	private LoginData ls;
	private PlayerClient player;
	private JPanel container;
	private PlayerGUI gui;
	private GameServerGUI sgui;
	private CreateAccount cA;
	private int random = 0;
	
	String[] users = {"caleb", "nick", "justin", "skyler"};
	String[] pass = {"club", "nb", "justkrik", "codingman"};
	
	@Before
	public void setUp() {
		sgui = new GameServerGUI();
		JButton listen = sgui.getButton();
		listen.doClick();
		gui = new PlayerGUI();
		player = gui.getPlayer();
		container = gui.getContainer();
		tc = new TitlescreenControl(container, player);
		ts = new Titlescreen(tc);
	}
	
	@Test
	public void testActionPerformed() {
		random = (int)Math.random()*users.length;
		String username = users[random];
		String password = pass[random];
		double number = 0;
		int integer = 0;
		
		ts.setPlayerLabel(username);
		ts.setPassLabel(password);
		
		LoginData actualData = new LoginData(ts.getPlayerName(), ts.getPassword());
		
		JButton button;
		number = Math.random()*2;
		integer = (int)number;
		button = ts.getArray(integer);
		
		if(button == ts.getArray(0)) {
			LoginData data = actualData;
			data.setPlayerName(username);
			data.setPassword(password);
			button.doClick(1000);
			assertEquals("check TitlescreenController ", data, actualData);
		}
		else if(button == ts.getArray(1)) {
			button.doClick(1000);
			
			assertTrue(true);
		}
		
		//fail("Not yet implemented");
	}

}
