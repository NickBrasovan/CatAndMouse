package cat_and_mouse;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;


public class GameScreenControl extends KeyAdapter{
	 
	/*INIT VARIABLES for GamescreenControl*/
	private int mouse_x, mouse_y, moused_x, moused_y; //mouse player coordinates. 
    private int req_dx, req_dy;	                     //required for moving player
    private final int N_BLOCKS = 15;                // determines size of gamescreen
    private final int BLOCK_SIZE = 24;             //determines size of gamescreen component blocks or tiles
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE; //define gamescreen size
    private short[] screenData;            //array of elements to create maze with create maze algorithm.
    private JPanel container;             //JPanel object for receiving Gamescreen object in constructor.
    private PlayerClient player;         //Player object for receiveing player object in constructor.
    int score;                          //TODO: ADD SCORE IMPLEMENTATION LATER
    private final int mouse_SPEED = 6;
    boolean inGame = true;            //Boolean inGame is true while game is playing
    
   // GamescreenData gsd;
    
    /*CONSTRUCTOR for GamescreenControl. container is Gamescreen object, passed fr. PlayerGUI*/
    public GameScreenControl(){
    	
    	
    	
    	//gsd = new GamescreenData();
    	
    }
    
    /*Setters & Getters for Mouse Coordinates (getters invoked by Gamescreen)*/
    public void setmouseY(int my) {this.mouse_y = my;}
    public void setmouseX(int mx) {this.mouse_x = mx;}
    public void setmousedX(int mdx) {this.moused_x = mdx;}
    public void setmousedY(int mdy) {this.moused_x = mdy;}
    public void setreq_dx(int rdx) {this.req_dx = rdx;}
    public void setreq_dy(int rdy) {this.req_dy = rdy;}
    public int getmouseX() {return this.mouse_x;}
	public int getmouseY() {return this.mouse_y;}
	public int getmousedX() {return this.moused_x;}
	public int getmousedY() {return this.moused_y;}
	public int getreq_dx() {return this.req_dx;}
	public int getreq_dy() {return this.req_dy;}
    
    
    /*MOVEMOUSE METHOD. INPUT ARRAY OF SCREENDATA*/
	public void movemouse(short[] screenData) {
			this.screenData = screenData;
	        int pos;
	        short ch;

	        if (mouse_x % BLOCK_SIZE == 0 && mouse_y % BLOCK_SIZE == 0) {
	            pos = mouse_x / BLOCK_SIZE + N_BLOCKS * (int) (mouse_y / BLOCK_SIZE);
	            ch = screenData[pos];

	            if ((ch & 16) != 0) {
	                screenData[pos] = (short) (ch & 15);
	                score++;
	            }

	            if (req_dx != 0 || req_dy != 0) {
	                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
	                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
	                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
	                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
	                    moused_x = req_dx;
	                    moused_y = req_dy;
	                }
	            }

	            // Check for standstill
	            if ((moused_x == -1 && moused_y == 0 && (ch & 1) != 0)
	                    || (moused_x == 1 && moused_y == 0 && (ch & 4) != 0)
	                    || (moused_x == 0 && moused_y == -1 && (ch & 2) != 0)
	                    || (moused_x == 0 && moused_y == 1 && (ch & 8) != 0)) {
	                moused_x = 0;
	                moused_y = 0;
	            }
	        } 
	        mouse_x = mouse_x + mouse_SPEED * moused_x;
	        mouse_y = mouse_y + mouse_SPEED * moused_y;
	    }
	
	 
	 public void keyPressed(KeyEvent e) {

	        int key = e.getKeyCode();

	        if (inGame) {
	            if (key == KeyEvent.VK_LEFT) {
	                req_dx = -1;
	                req_dy = 0;
	            } else if (key == KeyEvent.VK_RIGHT) {
	                req_dx = 1;
	                req_dy = 0;
	            } else if (key == KeyEvent.VK_UP) {
	                req_dx = 0;
	                req_dy = -1;
	            } else if (key == KeyEvent.VK_DOWN) {
	                req_dx = 0;
	                req_dy = 1;
	            } 
	        }
	    }

	
}
		



