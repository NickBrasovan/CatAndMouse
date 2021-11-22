package cat_and_mouse;

import java.io.Serializable;

public class GamescreenData implements Serializable {
	
	/*Initialize Level Data for Constructing Maze: Input into DrawMaze Method
     * This array stores data for drawing the maze. 
     * It is input into the draw maze function.*/
	/*private final short levelData[] = {
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
	    };*/
	
     /*"The data set has 225 numbers.  This represents 225 possible positions in the game. 225 = 15 rows x 15 rows.
      * Each number represents a specific element to be displayed. Here is the map:
      * 	0 = blue obstacle . Every 0 is a blue obstacle.
      *  1 = left border.
      *  2 = top border.
      *  4 = right border
      *  8 = bottom border
      *  16 = white dots

      *  The kind of element that fills the block space is determined by the sum of the attribute keys.
      *  Elements that only have 16 have a cheeze.
      *  All of the blue blocks, 0s, must be surrounded by borders, (1 or 2 or 3 or 4 or 6 or 8) + 16.
      *  " (gaspar coding ) */
	
    
}
