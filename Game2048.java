  /*  Name: Olivia O'Dwyer
   *  PennKey: oodwyer
   *  Recitation: 210
   *
   *  Execution: java Game2048
   * 
   * Description: This class contains the main game function that sets up the 
   * initial board and uses the GameBoard class to play the game. 
   *
   */

public class Game2048 {
    
    //establishes GameBoard game object
    private static GameBoard game;
    
    public static void main(String[] args) {
        
        //set up the initial board and tiles 
        PennDraw.setPenColor(PennDraw.BLUE);
        PennDraw.filledRectangle(.5, .5, .5, .5);
        PennDraw.setPenColor(PennDraw.WHITE);
        
        //draw 16 blank spaces for 4 by 4 grid
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
        
        //create rectangle with basic instructions to be displayed 
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(.5, .94, .47, .05);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(.5, .96, "Welcome to 2048! The goal of the game is to " + 
                         "get a 2048 tile.");
        PennDraw.text(.5, .93, "Please use the w, a, s, and d keys to navigate.");
            
        //establish new GameBoard object
        game = new GameBoard();
        
        //use draw function to draw initial starting tiles 
        game.draw();
        
        //animation loop
        boolean inGame = true;
        String result = " ";
        
        //inGame becomes false when game is lost or won 
        while (inGame) {
          
        //set up tile movement based on keys 
        if (PennDraw.hasNextKeyTyped()) {
                // update the tile's positions
                char c = PennDraw.nextKeyTyped();
                switch (c) {
                    case 'w':
                    case 'W':
                        game.move(GameBoard.Direction.UP);
                        game.draw();
                        break;
                    case 'a':
                    case 'A':
                          game.move(GameBoard.Direction.LEFT);
                          game.draw();
                          break;
                    case 's':
                    case 'S':
                        game.move(GameBoard.Direction.DOWN);
                        game.draw();
                        break;
                    case 'd':
                    case 'D':
                        game.move(GameBoard.Direction.RIGHT);
                        game.draw();
                        break;
                }
           }
        
      
        //test for a 2048 tile 
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
        if (game.getTile(i, j).getTileVal() == 2048) {
            //set result to winning message
            result = "You win!";
            //exit the while loop
            inGame = false;   
                   }
            }
        }
        
        //test for loss 
        if (game.cannotMove()) {
            //set result to losing message
            result = "You lose!";
            //exit the while loop
            inGame = false;
        }
        
        }
        
        //end game and display win or loss message
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(.5, .5, .48, .1);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(.5, .5, result);
}
    
}
                
        
        
    
    
