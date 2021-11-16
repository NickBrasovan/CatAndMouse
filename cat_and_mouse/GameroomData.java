package cat_and_mouse;

import java.io.*;

public class GameroomData implements Serializable {
	private String playerName;
	private String opponentName;
	
	public String getPlayerName() {
		return playerName;
	}
	
	public String getOpponent() {
		return opponentName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public void setOpponentName(String opponentName) {
		this.opponentName = opponentName;
	}
	
	public GameroomData(String pN, String oN) {
		setPlayerName(pN);
		setOpponentName(oN);
	}
}	