/*code adapted from gaspar coding "Pacman in Java" TAdapter class.  
See https://www.youtube.com/watch?v=ATz7bIqOjiA @ 12:41*/
/* Gamescreen and GamescreenControl Classes are adopted and adapted from 'Pacman in Java' by gaspar coding. Instructional video found at 
 * https://www.youtube.com/watch?v=ATz7bIqOjiA&t=904sl; source code for 'Pacman in Java' https://github.com/Gaspared/Pacman/*/

package cat_and_mouse;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JPanel;


//import pacman.GameScreen.TAdapter;

@SuppressWarnings("serial")
public class GamescreenControl extends KeyAdapter implements Serializable, ActionListener{
	 
	/*INIT VARIABLES for GamescreenControl*/
	private String character;
	private int mouse_x, mouse_y, moused_x, moused_y; //mouse player coordinates.
	private int cat_x, cat_y, catd_x, catd_y;
    private int c_req_dx, c_req_dy, m_req_dy, m_req_dx;	 
    private int seconds;
    private final int N_BLOCKS = 15;                // determines size of gamescreen
    private final int BLOCK_SIZE = 24;             //determines size of gamescreen component blocks or tiles
    
    private final int mouse_SPEED = 6;
    private final int cat_SPEED = 6;
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
    public void setcatX(int cx) {this.cat_x = cx;}
    public void setcatY(int cy) {this.cat_y = cy;}
    public void setcatdX(int cdx) {this.catd_x = cdx;}
    public void setcatdY(int cdy) {this.catd_y = cdy;}
    public void setc_req_dx(int rdx) {this.c_req_dx = rdx;}
    public void setc_req_dy(int rdy) {this.c_req_dy = rdy;}
    public void setm_req_dy(int rdy) {this.m_req_dy = rdy;}
    public void setm_req_dx(int rdx) {this.m_req_dx = rdx;}
    public void setCharacter(String character) {this.character = character;}
    public int getmouseX() {return this.mouse_x;}
	public int getmouseY() {return this.mouse_y;}
	public int getmousedX() {return this.moused_x;}
	public int getmousedY() {return this.moused_y;}
	public int getcatX() {return this.cat_x;}
	public int getcatY() {return this.cat_y;}
	public int getcatdX() {return this.catd_x;}
	public int getcatdY() {return this.catd_y;}
	public int getm_req_dx() {return this.m_req_dx;}
	public int getm_req_dy() {return this.m_req_dy;}
	public int getc_req_dx() {return this.c_req_dx;}
	public int getc_req_dy() {return this.c_req_dy;}
	public String getCharacter() {return this.character;} 
	public void setSeconds(int secs) {this.seconds = secs;};
	public int getSeconds() {return this.seconds;}
	
	
	public void setMouseGSD(GamescreenData gsd){
		this.gsd = gsd;
		
		this.setmouseY(gsd.getmouseY());
		this.setmouseX(gsd.getmouseX());
		this.setm_req_dx(gsd.getmousereq_dx());
		this.setm_req_dy(gsd.getmousereq_dy());

	}
	
	public void setCatGSD(GamescreenData gsd) {
		this.setcatY(gsd.getcatY());
		this.setcatX(gsd.getcatX());
		this.setc_req_dx(gsd.getcatreq_dx());
		this.setc_req_dy(gsd.getcatreq_dy());
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
	            if (m_req_dx != 0 || m_req_dy != 0) {
	                if (!((m_req_dx == -1 && m_req_dy == 0 && (ch & 1) != 0)
	                        || (m_req_dx == 1 && m_req_dy == 0 && (ch & 4) != 0)
	                        || (m_req_dx == 0 && m_req_dy == -1 && (ch & 2) != 0)
	                        || (m_req_dx == 0 && m_req_dy == 1 && (ch & 8) != 0))) {
	                    moused_x = m_req_dx;
	                    moused_y = m_req_dy;
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
	
	public void movecat(short[] screenData) {
        int pos;
        short ch;
        //position of mouse is determined
        if (cat_x % BLOCK_SIZE == 0 && cat_y % BLOCK_SIZE == 0) {
            pos = cat_x / BLOCK_SIZE + N_BLOCKS * (int) (cat_y / BLOCK_SIZE);
            ch = screenData[pos];             
			
            //req_dx and req_dy move mouse
            if (c_req_dx != 0 || c_req_dy != 0) {
                if (!((c_req_dx == -1 && c_req_dy == 0 && (ch & 1) != 0)
                        || (c_req_dx == 1 && c_req_dy == 0 && (ch & 4) != 0)
                        || (c_req_dx == 0 && c_req_dy == -1 && (ch & 2) != 0)
                        || (c_req_dx == 0 && c_req_dy == 1 && (ch & 8) != 0))) {
                    catd_x = c_req_dx;
                    catd_y = c_req_dy;
                }
            }
            
            // Check for standstill
           if ((catd_x == -1 && catd_y == 0 && (ch & 1) != 0)
                    || (catd_x == 1 && catd_y == 0 && (ch & 4) != 0)
                    || (catd_x == 0 && catd_y == -1 && (ch & 2) != 0)
                    || (catd_x == 0 && catd_y == 1 && (ch & 8) != 0)) {
                catd_x = 0;
                catd_y = 0;
            }
            
        } 
        cat_x = cat_x + cat_SPEED * catd_x;
        cat_y = cat_y + cat_SPEED * catd_y;  
        
        if (mouse_x > (cat_x- 12) && mouse_x < (cat_x + 12)
                && mouse_y > (cat_y - 12) && mouse_y < (cat_y + 12)
                && inGame) {

        	Boolean killmouse = true;
        	CardLayout cL = (CardLayout)container.getLayout();
        	Gamescreen gs = (Gamescreen)container.getComponent(3);
        	gs.setDying(killmouse);
        }
    }
	
	/*MousePlayer Cursor Controller
	 * Note that GamescreenControl extends KeyAdapter for this function*/
	 public void keyPressed(KeyEvent e) {
	        int key = e.getKeyCode();

	        //req_dx and req_dy are used to control the change in x and y
	        //dy -1 is up; dy 1 is down; dx -1 is left; dx 1 is right; set the value to 0 when inactive
	        if (inGame) {
	        	if(character.equals("Cat")) {
		            if (key == KeyEvent.VK_LEFT) {
		                c_req_dx = -1;
		                c_req_dy = 0;
		            } else if (key == KeyEvent.VK_RIGHT) {
		                c_req_dx = 1;
		                c_req_dy = 0;
		            } else if (key == KeyEvent.VK_UP) {
		                c_req_dx = 0;
		                c_req_dy = -1;
		            } else if (key == KeyEvent.VK_DOWN) {
		                c_req_dx = 0;
		                c_req_dy = 1;               
	            
		            } 
	        	}
	        	else if (character.equals("Mouse")) {
	        		if (key == KeyEvent.VK_LEFT) {
		                m_req_dx = -1;
		                m_req_dy = 0;
		            } else if (key == KeyEvent.VK_RIGHT) {
		                m_req_dx = 1;
		                m_req_dy = 0;
		            } else if (key == KeyEvent.VK_UP) {
		                m_req_dx = 0;
		                m_req_dy = -1;
		            } else if (key == KeyEvent.VK_DOWN) {
		                m_req_dx = 0;
		                m_req_dy = 1;               
	            
		            } 
	        	}
	            //set data and send to server
	            //GamescreenData gsd = new GamescreenData();
	            GamescreenData gsd = new GamescreenData();
	            
	            if(character.equals("Mouse")) {
		            gsd.setmousereq_dx(m_req_dx);
		            gsd.setmousereq_dy(m_req_dy);
		            gsd.setmouseX(mouse_x);
		            gsd.setmouseY(mouse_y);
		            gsd.setmoused_x(moused_x);
		            gsd.setmoused_y(moused_y);
		            gsd.setCharacter(character);
	            }
	            else if(character.equals("Cat")) {
	            	gsd.setcatreq_dx(c_req_dx);
		            gsd.setcatreq_dy(c_req_dy);
		            gsd.setcatX(cat_x);
		            gsd.setcatY(cat_y);
		            gsd.setcatd_x(catd_x);
		            gsd.setcatd_y(catd_y);
		            gsd.setCharacter(character);
	            }
	            
	            
	            try {
					player.sendToServer(gsd);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    }
	 
	 public void sendMouseWin() {
		 try {
			player.sendToServer("MouseWin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public void sendCatWin() {
		 try {
			player.sendToServer("CatWin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public void setInGame() {
		 CardLayout cL = (CardLayout)container.getLayout();
		 Gamescreen gs = (Gamescreen)container.getComponent(3);
		 gs.setInGame();
	 }
	 
	 public void actionPerformed(ActionEvent arg0) {
		 
		 CardLayout cL = (CardLayout)container.getLayout();
		 container.remove(3);
		 cL.show(container, "2");
	 }
		/*code adapted from gaspar coding "Pacman in Java" TAdapter class.  
		See https://www.youtube.com/watch?v=ATz7bIqOjiA @ 12:41*/
	
}
		



