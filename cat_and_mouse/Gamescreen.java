package cat_and_mouse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Gamescreen extends JPanel implements ActionListener {
	
	private Dimension d; //height and width of gameplay area
   
    private boolean inGame = false;  //boolean is true when game is in play
    

    private final int N_BLOCKS = 15;                         // determines size of gamescreen row and column
    private final int BLOCK_SIZE = 24;                      //determines size of gamescreen component blocks or tiles
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE; //define gamescreen size

    private Image up, down, left, right; //images for mouse animations

    private int mouse_x, mouse_y, moused_x, moused_y; //mouse_x and mouse_y store coordinates of sprite; moused_x and moused_store coordinates of mouse changes
    private int req_dx, req_dy;  //determined in TAdapter extends KeyAdapter{}, variables determined by cursor keys

    private GamescreenControl gsc;
    
    //private GamescreenData gsd = new GamescreenData();
    //private final short levelData[] = gsd.getLevelData();
    
    /*Level Data for Constructing Maze: Input into DrawMaze Method
     * This array stores data for drawing the maze. 
     * It is input into the draw maze function.*/
   private final short levelData[] = {
    	19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
        17, 16, 16, 16, 16, 24, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        25, 24, 24, 24, 28, 0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
        0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
        19, 18, 18, 18, 18, 18, 16, 16, 16, 16, 24, 24, 24, 24, 20,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
        17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
        17, 16, 16, 16, 24, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
        17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 18, 18, 18, 18, 20,
        17, 24, 24, 28, 0, 25, 24, 24, 16, 16, 16, 16, 16, 16, 20,
        21, 0,  0,  0,  0,  0,  0,   0, 17, 16, 16, 16, 16, 16, 20,
        17, 18, 18, 22, 0, 19, 18, 18, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
        25, 24, 24, 24, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
    };
    /*"The data set has 225 numbers.  This represents 225 possible positions in the game. 225 = 15 rows x 15 rows.
     * Each number represents a specific element to be displayed. Here is the map:
     * 	0 = blue obstacle . Every 0 is a blue obstacle.
     *  1 = left border.
     *  2 = top border.
     *  4 = right border
     *  8 = bottom border
     *  16 = white dots
     *  
     *  The kind of element that fills the block space is determined by the sum of the attribute keys.
     *  Elements that only have 16 have a cheeze.
     *  All of the blue blocks, 0s, must be surrounded by borders, (1 or 2 or 3 or 4 or 6 or 8) + 16.
     *  " (gaspar coding ) */

    
    private final int maxSpeed = 6;

    private int currentSpeed = 3;
    private short[] screenData; //takes all data from level data to redraw the game
    private Timer timer;


    /*CONSTRUCTOR for GAMESCREEN*/
    public Gamescreen(GamescreenControl gsc) {
        this.gsc =gsc;
        loadImages();
        initVariables();
        
        this.addKeyListener(gsc); //KeyListener is needed to move mouse
        setFocusable(true);
        inGame = true;
        initGame();
    }
    
    private void initVariables() {
    	screenData = new short[N_BLOCKS * N_BLOCKS]; //ArrayContainer for LevelData
    	d = new Dimension(400, 400);                //dimension of gamescreen
        timer = new Timer(40, this);               //used to redraw/repaint
        timer.start();
    	/* 40 is milliseconds; gamescreen is redrawn every 40 ms
    	 * timer animates b/c the timer determines frequency of redraw
    	 * lower number increases speed of games; higher number decreases speed of game*/
    }
    
    /*Load Sprites*/
    private void loadImages() {
    	down = new ImageIcon("cat_and_mouse/images/mouseF.gif").getImage();
    	up = new ImageIcon("cat_and_mouse//images/mouseB.gif").getImage();
    	left = new ImageIcon("cat_and_mouse//images/mouseL.gif").getImage();
    	right = new ImageIcon("cat_and_mouse//images/mouseR.gif").getImage();
    }
       
    /*playGame function is a collection of other functions Called in display of graphics*/
    private void playGame(Graphics2D g2d) {
           gsc.movemouse(screenData);
           drawmouse(g2d);
           checkMaze();
        }
    
    //TODO. ANNOTATE CHECKMAZE
    private void checkMaze() {
        int i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i]) != 0) {
                finished = false;
            }
            i++;
         }
        if (finished) {
            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }
            initLevel();
        }
    }

    private void drawmouse(Graphics2D g2d) {
    	this.mouse_x = gsc.getmouseX();  //syncs graphic with mouse object's coordinates in gamscreenconroller
    	this.mouse_y = gsc.getmouseY();
    	this.moused_x = gsc.getmousedX();
    	this.moused_y = gsc.getmousedY();
    	this.req_dx = gsc.getreq_dx();
    	this.req_dy = gsc.getreq_dy();
    	
        if (req_dx == -1) {
        	g2d.drawImage(left, mouse_x-9, mouse_y - 14, this);
        } else if (req_dx == 1) {
        	g2d.drawImage(right, mouse_x-9, mouse_y - 14, this);
        } else if (req_dy == -1) {
        	g2d.drawImage(up, mouse_x-9, mouse_y - 14, this);
        } else {
        	g2d.drawImage(down, mouse_x-9, mouse_y - 14, this);
        }
    }

    /*DRAW MAZE ALGORITHM FROM GASPAR CODING "PACMAN" IN JAVA*/
    private void drawMaze(Graphics2D g2d) {
        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(new Color(0,72,251));
                g2d.setStroke(new BasicStroke(5));
                
                if ((levelData[i] == 0)) { 
                	g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                 }

                if ((screenData[i] & 1) != 0) { 
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) { 
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) { 
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) { 
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) { 
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
               }
                i++;
            }
        }
    }

    //TODO ANNOTATE
    void initGame() {
        initLevel();
        currentSpeed = 3;
    }

    //TODO ANNOTATE
    private void initLevel() {

        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }
        continueLevel();
    }

    //TODO ANNOTATE
    private void continueLevel() {

        mouse_x = 7 * BLOCK_SIZE;  //start position of mouse
        mouse_y = 11 * BLOCK_SIZE;
        moused_x = 0;	//reset direction move
        moused_y = 0;
        req_dx = 0;		// reset direction controls, controlled with cursor keys
        req_dy = 0;
        gsc.setmouseX(mouse_x);
        gsc.setmouseY(mouse_y);
        gsc.setmousedX(moused_x);
        gsc.setmousedY(moused_y);
        gsc.setreq_dx(req_dx);
        gsc.setreq_dy(req_dy);
    }

 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //constructor from parent class

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);  //background color
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);

        if (inGame) {
            playGame(g2d);
        } 

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
		
}