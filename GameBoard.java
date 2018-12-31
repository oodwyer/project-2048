  /*  Name: Olivia O'Dwyer
   *  PennKey: oodwyer
   *  Recitation: 210
   *
   * 
   * Description: This class GameBoard is a 2D array of the private class Tile 
   * objects, which is also contained within this file. 
   *
   */

public class GameBoard {
    
    //creates enum for Directions
    public enum Direction { UP, DOWN, LEFT, RIGHT } 
    private Direction direction;
    
    //initializes number of moves variable at 0 
    private int moves = 0;
    
    //initializes 2D array gameTiles
    private Tile[][] gameTiles;
    
     
    /*
      * Input: none
      *
      * Output: creates a new GameBoard
      *
      * Description: The constructor; creates a new gameBoard with 16 tiles 
      */
    public GameBoard() {
        //create 2D tile array
        gameTiles = new Tile[4][4];
        this.gameTiles = gameTiles;
        
        //creates 16 new tiles in each of the positions of the array with val 0
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameTiles[i][j] = new Tile(i, j, 0);
            }
        }
        
        //insert two initial tiles at random locations 
        for (int i = 0; i < 2; i++) {
            int xpos = (int) Math.floor(Math.random() * 4);
            int ypos = (int) Math.floor(Math.random() * 4);
            while (gameTiles[xpos][ypos].getTileVal() != 0) {
                xpos = (int) Math.floor(Math.random() * 4);
                ypos = (int) Math.floor(Math.random() * 4);
            }
            
            //determines value of tile as 2 or 4 based on random probability 
            if (Math.random() < .75) {
                gameTiles[xpos][ypos].setTileVal(2);
            } else {
                gameTiles[xpos][ypos].setTileVal(4);
            }
        }

    }

     
    /*
      * Input: Direction d
      *
      * Output: none
      *
      * Description: The move function, contains separate sections depending 
      * on the direction specified 
      */
    public void move(Direction d) {
        boolean move = false; 
        if (d == Direction.UP) {
            //loops through each of the tiles except for the top row 
            for (int i = 0; i < 4; i++) { 
                for (int j = 1; j < 4; j++) { 
                    //if the tile does not have a value of 0 
                    if (gameTiles[i][j].getTileVal() != 0) {
                        //if the tile above does not have a value of 0 
                        if (gameTiles[i][j - 1].getTileVal() != 0) {
                            //if the tile above has the same value as current
                            if (gameTiles[i][j - 1].getTileVal() == 
                                gameTiles[i][j].getTileVal()) {
                                //if the tile has not yet been merged 
                                if (!gameTiles[i][j - 1].getMerged()) {
                                    //set the tile above's value to twice val 
                                    gameTiles[i][j - 1].setTileVal(
                                              gameTiles[i][j].getTileVal() * 2);
                                    //set merge value to true 
                                    gameTiles[i][j - 1].setMerged(true);
                                    //set current tile value to 0 
                                    gameTiles[i][j].setTileVal(0);
                                    //a move has been made 
                                    move = true;
                                }
                            } 
                        } else { //move up to blank space
                            int hold = j;
                            //while the value above is still 0, move up
                            while (hold > 0 && gameTiles[i][hold - 1].
                                       getTileVal() == 0) {
                                hold -= 1;
                            }
                            //reset position to highest open spot 
                            gameTiles[i][hold].setTileVal(gameTiles[i][j].
                                                              getTileVal());
                            gameTiles[i][j].setTileVal(0);
                            //a move has been made 
                            move = true;
                            
                            //test again to see if tile above can be merged with 
                            if (hold != 0) {
                                if (gameTiles[i][hold - 1].getTileVal() != 0) {
                                    if (gameTiles[i][hold - 1].getTileVal() == 
                                        gameTiles[i][hold].getTileVal()) {
                                        if (!gameTiles[i][j - 1].getMerged()) {
                                            gameTiles[i][hold - 1].setTileVal(
                                           gameTiles[i][hold].getTileVal() * 2);
                                            gameTiles[i][j - 1].setMerged(true);
                                            gameTiles[i][hold].setTileVal(0);
                                        }
                                    }
                                }
                            }
                        }
                        
                        //at end of round, set all tile's merge values to false 
                        gameTiles[i][j - 1].setMerged(false);
                    }
                }
            }
            //increase number of moves if it has been made 
            if (move) {
                moves++;
            }
        } else if (d == Direction.DOWN) {
            //repeat above with down direction, except loop through from bottom 
            for (int i = 0; i < 4; i++) {
                for (int j = 2; j >= 0; j--) {
                    if (gameTiles[i][j].getTileVal() != 0) {
                        if (gameTiles[i][j + 1].getTileVal() == 0) {
                            int hold = j;
                            while (hold < 3 && gameTiles[i][hold + 1].
                                       getTileVal() == 0) {
                                hold++; 
                            }
                            gameTiles[i][hold].setTileVal(gameTiles[i][j].
                                                              getTileVal());
                            gameTiles[i][j].setTileVal(0);
                            move = true;
                            if (hold != 3) {
                                if (gameTiles[i][hold + 1].getTileVal() != 0) {
                                    if (gameTiles[i][hold + 1].getTileVal() == 
                                        gameTiles[i][hold].getTileVal()) {
                                        if (!gameTiles[i][hold + 1].getMerged()) { 
                                            gameTiles[i][hold + 1].setTileVal(
                                           gameTiles[i][hold].getTileVal() * 2);
                                         gameTiles[i][hold + 1].setMerged(true);
                                            gameTiles[i][hold].setTileVal(0);
                                        }
                                    }
                                }
                            }
                        } else if (gameTiles[i][j + 1].getTileVal() == 
                                   gameTiles[i][j].getTileVal()) {
                            if (!gameTiles[i][j + 1].getMerged()) {
                                gameTiles[i][j + 1].setTileVal(gameTiles[i][j].
                                                            getTileVal() * 2);
                                gameTiles[i][j + 1].setMerged(true);
                                gameTiles[i][j].setTileVal(0);
                                move = true;
                            }
                        }
                        gameTiles[i][j + 1].setMerged(false);  
                    } 
                } 
            }
             //if a move has been made, increase moves by 1 
             if (move) {
                 moves++;
             }
        } else if (d == Direction.LEFT) { //repeat above with left direction
            for (int i = 1; i < 4; i++) { 
                for (int j = 0; j < 4; j++) { 
                    //test to see if tile above
                    if (gameTiles[i][j].getTileVal() != 0) {
                        if (i > 0 && gameTiles[i - 1][j].getTileVal() != 0) {
                            if (gameTiles[i - 1][j].getTileVal() == 
                                gameTiles[i][j].getTileVal()) {
                                if (!gameTiles[i - 1][j].getMerged()) {
                                    gameTiles[i - 1][j].setTileVal(
                                              gameTiles[i][j].getTileVal() * 2);
                                    gameTiles[i - 1][j].setMerged(true);
                                    gameTiles[i][j].setTileVal(0);
                                    move = true;
                                } 
                            }
                        } else { //move left
                            int hold = i;
                            while (hold > 0 && gameTiles[hold - 1][j].
                                       getTileVal() == 0) {
                                hold -= 1;
                            }
                            gameTiles[hold][j].setTileVal(gameTiles[i][j].
                                                              getTileVal());
                            gameTiles[i][j].setTileVal(0);
                            move = true;
                            if (hold != 0) {
                                if (gameTiles[hold - 1][j].getTileVal() != 0) {
                                    if (gameTiles[hold - 1][j].getTileVal() == 
                                        gameTiles[hold][j].getTileVal()) {
                                      if (!gameTiles[hold - 1][j].getMerged()) {
                                           gameTiles[hold - 1][j].setTileVal(
                                           gameTiles[hold][j].getTileVal() * 2);
                                           gameTiles[hold - 1][j].setMerged(
                                                                          true);
                                            gameTiles[hold][j].setTileVal(0);
                                        }
                                    }
                                }
                            }
                            
                        }
                        gameTiles[i - 1][j].setMerged(false);
                    }
                    
                    
                }
            }
             if (move) {
                 moves++;
             }
        } else if (d == Direction.RIGHT) { //repeat above with right
            
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    if (gameTiles[i][j].getTileVal() != 0) {
                        if (gameTiles[i + 1][j].getTileVal() == 0) {
                            int hold = i;
                            while (hold < 3 && gameTiles[hold + 1][j].
                                       getTileVal() == 0) {
                                hold++; 
                            }
                            gameTiles[hold][j].setTileVal(gameTiles[i][j].
                                                              getTileVal());
                            gameTiles[i][j].setTileVal(0);
                            move = true;
                            if (hold != 3) {
                                if (gameTiles[hold + 1][j].getTileVal() != 0) {
                                    if (gameTiles[hold + 1][j].getTileVal() == 
                                        gameTiles[hold][j].getTileVal()) {
                                        if (!gameTiles[hold + 1][j].
                                                getMerged()) {
                                            
                                            gameTiles[hold + 1][j].setTileVal(
                                          gameTiles[hold][j].getTileVal() * 2);
                                         gameTiles[hold + 1][j].setMerged(true);
                                            gameTiles[hold][j].setTileVal(0);
                                        }
                                    }
                                }
                            }
                        } else if (gameTiles[i + 1][j].getTileVal() == 
                                   gameTiles[i][j].getTileVal()) {
                            if (!gameTiles[i + 1][j].getMerged()) {
                                gameTiles[i + 1][j].setTileVal(gameTiles[i][j].
                                                              getTileVal() * 2);
                                gameTiles[i + 1][j].setMerged(true);
                                gameTiles[i][j].setTileVal(0);
                                move = true;
                            }
                        } 
                    }
                    gameTiles[i + 1][j].setMerged(false);
                } 
            }      
            
            //end of directions
            if (move) {
                moves++;
            }
        }
        
        //if a move has been made, insert new random tile in empty location 
        if (move) {
            int xpos = (int) Math.floor(Math.random() * 4);
            int ypos = (int) Math.floor(Math.random() * 4);
        while (gameTiles[xpos][ypos].getTileVal() != 0) {
            xpos = (int) Math.floor(Math.random() * 4);
            ypos = (int) Math.floor(Math.random() * 4);
        }
        if (Math.random() < .75) {
            gameTiles[xpos][ypos].setTileVal(2);
        } else {
            gameTiles[xpos][ypos].setTileVal(4);
        }
}
}
    
     /*
      * Input: none
      *
      * Output: int moves
      *
      * Description: returns the moves variable
      */
    public int getMoves() {
        return moves;
    }
    
     /*
      * Input: int xpos, int ypos
      *
      * Output: Tile 
      *
      * Description: returns the tile in the specified index in the tile array
      */
    public Tile getTile(int xpos, int ypos) {
        return gameTiles[xpos][ypos];
    }
    
     /*
      * Input: none
      *
      * Output: boolean
      *
      * Description: Tests whether or not a move can still be made on the board,
      * returns true if the game has been lost 
      */
    public boolean cannotMove() {
        //loops through each of the tiles 
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //if the value is 0, return false
                if (gameTiles[i][j].getTileVal() == 0) {
                    return false; 
                } 
                //if can merge to the left, returns false
                if (i != 0) {
                    if (gameTiles[i - 1][j].getTileVal() == gameTiles[i][j].
                            getTileVal()) {
                        return false;
                    }
                }
                
                //if can merge to the right, return false
                if (i != 3) {
                    if (gameTiles[i + 1][j].getTileVal() == gameTiles[i][j].
                            getTileVal()) {
                        return false;
                    }
                }
                
                //if can merge up, return false
                if (j != 0) {
                    if (gameTiles[i][j - 1].getTileVal() == gameTiles[i][j].
                            getTileVal()) {
                        return false;
                    }
                }
                
                //if can merge down, return false 
                if (j != 3) {
                    if (gameTiles[i][j + 1].getTileVal() == gameTiles[i][j].
                            getTileVal()) {
                        return false;
                    }
                }
            }
        }
        
        //otherwise, return true
        return true; 
    }
    
     /*
      * Input: none
      *
      * Output: none
      *
      * Description: The draw functions redraws tiles in updated positions 
      * with updated values as well as updating number of moves displayed
      */
    public void draw() {
        //redraw initial white squares over the spots
        PennDraw.setPenColor(PennDraw.WHITE);
        double x = .016;
        double y = .9776;
        for (int i = 0; i < 4; i++) {
            x += .192;
            y = .9776;
            for (int j = 0; j < 4; j++) {
                y -= .192;
             PennDraw.filledRectangle(x, y, .08, .08);
            }
        }
        
        //draw each of the tiles w values not 0 
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameTiles[i][j].getTileVal() != 0) {
                   x = .208 + i * .192;
                   y = .7856 - j * .192;
                   
                   //draw tile at the x and y position, color depends on val
                   if (gameTiles[i][j].getTileVal() == 2) {
                       //light blue
                   PennDraw.setPenColor(204, 229, 255);
                   } else if (gameTiles[i][j].getTileVal() == 4) {
                       //light purple
                       PennDraw.setPenColor(229, 204, 255);
                   } else if (gameTiles[i][j].getTileVal() == 8) {
                       //light green
                       PennDraw.setPenColor(153, 255, 204);
                   } else if (gameTiles[i][j].getTileVal() == 16) {
                       //light orange
                       PennDraw.setPenColor(255, 204, 153);
                   } else if (gameTiles[i][j].getTileVal() == 32) {
                       //pink
                       PennDraw.setPenColor(255, 204, 229);
                   } else if (gameTiles[i][j].getTileVal() == 64) {
                       //red
                       PennDraw.setPenColor(255, 102, 102);
                   } else if (gameTiles[i][j].getTileVal() == 128) {
                       //yellow
                       PennDraw.setPenColor(255, 255, 153);
                   } else if (gameTiles[i][j].getTileVal() == 256) {
                       //purple
                       PennDraw.setPenColor(153, 51, 255);
                   } else if (gameTiles[i][j].getTileVal() == 512) {
                       //navy 
                       PennDraw.setPenColor(0, 178, 142);
                   } else if (gameTiles[i][j].getTileVal() == 1024) {
                       //dark red
                       PennDraw.setPenColor(153, 0, 76);
                   } else if (gameTiles[i][j].getTileVal() == 2048) {
                       //white
                       PennDraw.setPenColor(255, 255, 255);
                   }
                       
                   //draw value on top of tile 
                   PennDraw.filledRectangle(x, y, .08, .08);
                   String num = Integer.toString(gameTiles[i][j].getTileVal());
                   PennDraw.setPenColor(PennDraw.BLACK);
                   PennDraw.setFontSize(40);
                   PennDraw.text(x, y, Integer.toString(gameTiles[i][j].
                                                            getTileVal()));
                }
            }
        }
        
       //draw new number of moves 
       PennDraw.setPenColor(PennDraw.BLUE);
       PennDraw.filledRectangle(.75, .05, .3, .05);
       PennDraw.setPenColor(PennDraw.WHITE);
       PennDraw.text(.7, .05, "Moves: " + moves); 
    }
    }
    

//private class tile with individual tile objects 
class Tile {
    
    //fields 
    private double size = .08; 
    private int x; 
    private int y; 
    private int val; 
    private int xpos;
    private int ypos; 
    private boolean merged; 
    
     /*
      * Input: int xpos, int ypos, int val
      *
      * Output: creates new Tile object
      *
      * Description: constructor that sets the values for the tile of its 
      * position and initial value 
      */
    public Tile(int xpos, int ypos, int val) {
        this.val = val; 
        this.xpos = xpos; 
        this.ypos = ypos; 
    }
        
     /*
      * Input: none
      *
      * Output: int val
      *
      * Description: getter for val variable
      */
    public int getTileVal() {
        return val;
    }
  
     /*
      * Input: int newVal
      *
      * Output: none
      *
      * Description: sets val variable of tile to newVal
      */
    public void setTileVal(int newVal) {
        val = newVal;
    }
    
     /*
      * Input: boolean b
      *
      * Output: none
      *
      * Description: Sets merged variable to boolean b
      */
    public void setMerged(boolean b) {
        merged = b; 
    }
    
     /*
      * Input: none
      *
      * Output: boolean
      *
      * Description: getter for the merged variable 
      */    
    public boolean getMerged() {
        return merged; 
    }

    
}