
package pl.chizynski.kolkokrzyzyk.rules;

import pl.chizynski.kolkokrzyzyk.boards.Board;

public class GumokuRules extends Rules {

    public GumokuRules(Board board) {
        super(board);
    }
    
     public boolean checkWinInRow(char znak, int wiersz) {
        boolean result = false;
        int amount_of_exist = 0;
        for (int i = 0; i < board.fields[wiersz].length; i++) {
            // System.out.println("["+wiersz+"]["+i+"]"); 
            if (board.fields[i][wiersz].getSymbol() == znak  ) {
                amount_of_exist++;
            }else{
                amount_of_exist = 0;
            }
            if(amount_of_exist == 5){
                result = true;
            }
        }
        return result;
    }

  
    
   }
   
