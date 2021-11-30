package cat_and_mouse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Gamescreen extends JPanel implements Serializable, ActionListener {
	
	private Dimension d; //height and width of gameplay area
   
    private boolean inGame = false;  //boolean is true when game is in play
    private boolean dying = false; 
    private int seconds = 0;
    
    
    private final int N_BLOCKS = 15;                         // determines size of gamescreen row and column
    private final int BLOCK_SIZE = 24;                      //determines size of gamescreen component blocks or tiles
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE; //define gamescreen size

    private Image up, down, left, right, catdown, catup, catleft, catright; //images for mouse animations

    private int mouse_x, mouse_y, moused_x, moused_y; //mouse_x and mouse_y store coordinates of sprite; moused_x and moused_y store coordinates of mouse changes
    private int cat_x, cat_y, catd_x, catd_y;
    private int m_req_dx, m_req_dy, c_req_dy, c_req_dx;  //determined in TAdapter extends KeyAdapter{}, variables determined by cursor keys
    private String printTimer = "Time Left: ";
    
    private GamescreenControl gsc;
    private GamescreenData gsd;
    
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
    private int lives = 2;
    private int currentSpeed = 3;
    //private Timer gameTimer;
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
    
    public void setGSD(GamescreenData gsd)
    {
    	this.gsd = gsd;
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
    	catdown = new ImageIcon("cat_and_mouse/images/catBack.gif").getImage();
    	catup = new ImageIcon("cat_and_mouse//images/catForward.gif").getImage();
    	catleft = new ImageIcon("cat_and_mouse//images/catLeft.gif").getImage();
    	catright = new ImageIcon("cat_and_mouse//images/catRight.gif").getImage();
    }
       
    public void setDying(Boolean dead) {
    	this.dying = dead;
    }
    
    /*playGame function is a collection of other functions Called in display of graphics*/
    private void playGame(Graphics2D g2d) {
    
    	
    	if (dying) {

            death();

        }
    	
    	if(seconds < 0) {
    		mouseWin(g2d);
    	}
    	else {
           gsc.movemouse(screenData);
           gsc.movecat(screenData);
           seconds = gsc.getSeconds();
           Integer intSeconds = seconds;
           String printTimer = "Time Left: " + intSeconds.toString();
           g2d.setColor(Color.WHITE);
           g2d.drawString(printTimer, 10, 375);
           
           drawmouse(g2d);
           drawcat(g2d);
    	}
    }
    
    private void death() {

    	inGame = false;
    	gsc.sendCatWin();
    	JButton playAgain = new JButton("Play Again");
    	playAgain.setBounds(10, 450, 120, 30);
    	this.add(playAgain);
    	playAgain.addActionListener(gsc);
        continueLevel();
    }
    
    private void mouseWin(Graphics g2d) {
    	if(lives > 0) {
    		inGame = false;
    		gsc.sendMouseWin();
    	}
    	JButton playAgain = new JButton("Play Again");
    	playAgain.setBounds(10, 450, 120, 30);
    	this.add(playAgain);
    	playAgain.addActionListener(gsc);
    }
    
    private void drawmouse(Graphics2D g2d) {
    	
    	this.mouse_x = gsc.getmouseX();  //syncs graphic with mouse object's coordinates in gamscreenconroller
    	this.mouse_y = gsc.getmouseY();
    	this.moused_x = gsc.getmousedX();
    	this.moused_y = gsc.getmousedY();
    	this.m_req_dx = gsc.getm_req_dx();
    	this.m_req_dy = gsc.getm_req_dy();
    	
    	/*this.mouse_x = gsd.getmouseX();  //syncs graphic with mouse object's coordinates in gamscreenconroller
    	this.mouse_y = gsd.getmouseY();
    	this.moused_x = gsd.getmoused_x();
    	this.moused_y = gsd.getmoused_y();
    	this.req_dx = gsd.getreq_dx();
    	this.req_dy = gsd.getreq_dy();*/
    	
        if (m_req_dx == -1) {
        	g2d.drawImage(left, mouse_x-9, mouse_y - 14, this);
        } else if (m_req_dx == 1) {
        	g2d.drawImage(right, mouse_x-9, mouse_y - 14, this);
        } else if (m_req_dy == -1) {
        	g2d.drawImage(up, mouse_x-9, mouse_y - 14, this);
        } else {
        	g2d.drawImage(down, mouse_x-9, mouse_y - 14, this);
        }
    }
    
    private void drawcat(Graphics2D g2d) {
    	
    	this.cat_x = gsc.getcatX();  //syncs graphic with mouse object's coordinates in gamscreenconroller
    	this.cat_y = gsc.getcatY();
    	this.catd_x = gsc.getcatdX();
    	this.catd_y = gsc.getcatdY();
    	this.c_req_dx = gsc.getc_req_dx();
    	this.c_req_dy = gsc.getc_req_dy();
    	
    	/*this.mouse_x = gsd.getmouseX();  //syncs graphic with mouse object's coordinates in gamscreenconroller
    	this.mouse_y = gsd.getmouseY();
    	this.moused_x = gsd.getmoused_x();
    	this.moused_y = gsd.getmoused_y();
    	this.req_dx = gsd.getreq_dx();
    	this.req_dy = gsd.getreq_dy();*/
    	
        if (c_req_dx == -1) {
        	g2d.drawImage(catleft, cat_x-9, cat_y - 14, this);
        } else if (c_req_dx == 1) {
        	g2d.drawImage(catright, cat_x-9, cat_y - 14, this);
        } else if (c_req_dy == -1) {
        	g2d.drawImage(catup, cat_x-9, cat_y - 14, this);
        } else {
        	g2d.drawImage(catdown, cat_x-9, cat_y - 14, this);
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
        
        g2d.setColor(new Color(0,72,251));
        g2d.setStroke(new BasicStroke(5));
        g2d.fillRect(10, 450, 120, 30);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Play Again", 40, 470);
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
    public void continueLevel() {

        mouse_x = 1 * BLOCK_SIZE;//7 * BLOCK_SIZE;  //start position of mouse
        mouse_y = 1 * BLOCK_SIZE;//11 * BLOCK_SIZE;
        moused_x = 0;	//reset direction move
        moused_y = 0;
        m_req_dx = 0;		// reset direction controls, controlled with cursor keys
        m_req_dy = 0;
        c_req_dx = 0;
        c_req_dy = 0;
        cat_x = 7 * BLOCK_SIZE;
        cat_y = 11 * BLOCK_SIZE;
        catd_x = 0;
        catd_y = 0;
        
        //initial setting for mouse
        gsc.setmouseX(mouse_x);
        gsc.setmouseY(mouse_y);
        gsc.setmousedX(moused_x);
        gsc.setmousedY(moused_y);
        gsc.setm_req_dx(m_req_dx);
        gsc.setm_req_dy(m_req_dy);
        
        gsc.setcatX(cat_x);
        gsc.setcatY(cat_y);
        gsc.setcatdX(catd_x);
        gsc.setcatdY(catd_y);
        gsc.setc_req_dx(c_req_dx);
        gsc.setc_req_dy(c_req_dy);
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
        if(seconds < 0) {
        	g2d.setColor(Color.WHITE);
        	printTimer = "Mouse Wins";
        	g2d.drawString(printTimer, 10, 375);
        }
        
        if(dying == true) {
        	g2d.setColor(Color.WHITE);
        	printTimer = "Cat Wins";
        	g2d.drawString(printTimer, 10, 375);
        }
        
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }
    
   
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
		
}