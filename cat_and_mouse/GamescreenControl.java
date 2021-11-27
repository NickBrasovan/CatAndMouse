package cat_and_mouse;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JPanel;


//import pacman.GameScreen.TAdapter;

@SuppressWarnings("serial")
public class GamescreenControl extends KeyAdapter implements Serializable{
	 
	/*INIT VARIABLES for GamescreenControl*/
	private int mouse_x, mouse_y, moused_x, moused_y; //mouse player coordinates. 
    private int req_dx, req_dy;	                     //required for moving player
    private final int N_BLOCKS = 15;                // determines size of gamescreen
    private final int BLOCK_SIZE = 24;             //determines size of gamescreen component blocks or tiles
    
    private final int mouse_SPEED = 6;
    boolean inGame = true;            //Boolean inGame is true while game is playing  
    
    private JPanel container;             //JPanel object for receiving Gamescreen object in constructor.
    private PlayerClient player;         //Player object for receiveing player object in constructor.
    
    GamescreenData gsd;  //GAMESCREENDATA OBJECT
    
    /*CONSTRUCTOR for GamescreenControl. container is Gamescreen object, passed fr. PlayerGUI*/
    
    public GamescreenControl(JPanel container, PlayerClient player){
    	this.container = container;   //set this container to Gamescreen
    	this.player = player;  //set player
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
	
	public void setGSD(GamescreenData gsd){
	/*	this.gsd = gsd;
		this.setmouseY(gsd.getmouseY());
		this.setmouseX(gsd.getmouseX());
		this.setreq_dx(gsd.getreq_dx());
		this.setreq_dy(gsd.getreq_dy());*/

	}
    
	
	
	
    /*MOVEMOUSE METHOD. INPUT ARRAY OF SCREENDATA*/
	public void movemouse(short[] screenData) {
	        int pos;
	        short ch;
	        

	        //position of mouse is determined
	        if (mouse_x % BLOCK_SIZE == 0 && mouse_y % BLOCK_SIZE == 0) {
	            pos = mouse_x / BLOCK_SIZE + N_BLOCKS * (int) (mouse_y / BLOCK_SIZE);
	            ch = screenData[pos];
	            
	            //mouse eats pellets 
	            if ((ch & 16) != 0) {
	                screenData[pos] = (short) (ch & 15);
	            }

	            //req_dx and req_dy move mouse
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
	
	/*MousePlayer Cursor Controller
	 * Note that GamescreenControl extends KeyAdapter for this function*/
	 public void keyPressed(KeyEvent e) {
	        int key = e.getKeyCode();

	        //req_dx and req_dy are used to control the change in x and y
	        //dy -1 is up; dy 1 is down; dx -1 is left; dx 1 is right; set the value to 0 when inactive
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
	            //set data and send to server
	            //GamescreenData gsd = new GamescreenData();
	            GamescreenData gsd = new GamescreenData();
	            
	            gsd.setreq_dx(req_dx);
	            gsd.setreq_dy(req_dy);
	            gsd.setmouseX(mouse_x);
	            gsd.setmouseY(mouse_y);
	            
	            try {
					player.sendToServer(gsd);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    }
		/*code adapted from gaspar coding "Pacman in Java" TAdapter class.  
		See https://www.youtube.com/watch?v=ATz7bIqOjiA @ 12:41*/
	
}
		



