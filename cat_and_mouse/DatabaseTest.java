package cat_and_mouse;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {

	String[] players = {"caleb", "justin", "skyler", "nick"};
	String[] passwords = {"club", "justkrik", "codingman", "nb"};
	String[] catwins = {"0","0","0","0"};
	String[] catlosses = {"0","0","0","0"};
	String[] mousewins = {"0","0","0","0"};
	String[] mouselosses = {"0","0","0","0"};
	private Database db;
	private int random;
	
	@Before
	public void setUp() throws Exception{
		db = new Database();
		random = ((int)Math.random()*players.length);
	}
	
	@Test
	public void testQuery() {
		String username = players[random];
		String expected = passwords[random];
		
		ArrayList<String> list = db.query("select username, aes_decrypt(password, 'key') from catandmouse where username = '" + username + "'");
		
		String[] actualList = list.get(0).split(",");
		String actual = actualList[1];
		
		assertEquals(expected, actual);
	}

	@Test
	public void testQueryWL() {
		String username = players[random];
		String expected = mouselosses[random];
		
		ArrayList<String> list = db.queryWL("select catwins, catlosses, mousewins, mouselosses from catandmouse where username = '" + username + "'");
		String[] actualList = list.get(0).split(",");
		String actual = actualList[3];
		
		assertEquals(expected, actual);
	}

	@Test
	public void testExecuteDML() {
		try {
			db.executeDML("insert into catandmouse values('msmith', aes_encrypt('professor', 'key'), 0, 0, 0, 0);");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("There was an exception");
		}
	}

	@Test
	public void testVerifyAccount() {
		ArrayList<String> list = db.query("SELECT username, aes_decrypt(password, 'key') from catandmouse");
		assertNotNull(list);
	}

	@Test
	public void testVerifyCreateInfo() {
		String username = players[random];
		String password = passwords[random];
		ArrayList<String> list = db.query("SELECT * FROM catandmouse");
		ArrayList<String> user = new ArrayList<String>();
		ArrayList<String> pass = new ArrayList<String>();
		
		for(int i = 0; i < list.size(); i++) {
			user.add(list.get(i).split(",")[0]);
			pass.add(list.get(i).split(",")[1]);
		}
		assertTrue(user.contains(username) || pass.contains(password));
	}

	@Test
	public void testCreateAccount() {
		String username = "michael";
		String password = "hellothere";
		int catwins = 0;
		int catlosses = 0;
		int mousewins = 0;
		int mouselosses = 0;
		
		if(db.verifyCreateInfo(username, password)) {
			try {
				db.executeDML("insert into catandmouse values ('" + username + "',aes_encrypt('" + password + "','key')," + catwins + "," + catlosses + "," + mousewins + "," + mouselosses + ";)");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				fail("There was an exception");
			}
			assertTrue(true);
		}
	}

	@Test
	public void testUpdateCatWins() {
		String user = players[random];
		
		try {
			db.executeDML("UPDATE catandmouse set catwins = 1 where username = '" + user + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("There was an exception");
		}
	}

	@Test
	public void testUpdateCatLosses() {
		String user = players[random];
		
		try {
			db.executeDML("UPDATE catandmouse set catlosses = 1 where username = '" + user + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("There was an exception");
		};
	}

	@Test
	public void testUpdateMouseWins() {
		String user = players[random];
		
		try {
			db.executeDML("UPDATE catandmouse set mousewins = 1 where username = '" + user + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("There was an exception");
		};
	}

	@Test
	public void testUpdateMouseLosses() {
		String user = players[random];
		
		try {
			db.executeDML("UPDATE catandmouse set mouselosses = 1 where username = '" + user + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail("There was an exception");
		};
	}

}
