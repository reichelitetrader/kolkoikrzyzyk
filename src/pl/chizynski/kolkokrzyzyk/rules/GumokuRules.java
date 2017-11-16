package pl.chizynski.kolkokrzyzyk.rules;

import pl.chizynski.kolkokrzyzyk.boards.Board;

public class GumokuRules extends Rules {

    public GumokuRules(Board board) {
        super(board);
    }

    @Override
    public boolean checkWinInRow(char znak, int wiersz) {
        boolean result = false;
        int amount_of_exist = 0;
        for (int i = 0; i < board.getFields()[wiersz].length; i++) {
            // System.out.println("["+wiersz+"]["+i+"]"); 
            if (board.getFields()[i][wiersz].getSymbol() == znak) {
                amount_of_exist++;
            } else {
                amount_of_exist = 0;
            }
            if (amount_of_exist == 5) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean checkWinInColumn(char znak, int kolumna) {
        boolean result = false;
        int amount_of_exist = 0;
        for (int i = 0; i < board.getFields()[kolumna].length; i++) {
            //   System.out.println("["+kolumna+"]["+i+"]"); 
            if (board.getFields()[kolumna][i].getSymbol() == znak) {

                amount_of_exist++;

            } else {
                amount_of_exist = 0;
            }
            if (amount_of_exist == 5) {
                result = true;
            }
        }
        return result;
    }
    
    
    @Override
    public boolean checkWinInFirstSlant(char znak) {
        boolean result = false;
        int amount_of_exist = 0;
        for (int i = 0; i < board.getFields().length; i++) {
            if (board.getFields()[i][i].getSymbol() == znak) {
                amount_of_exist++;
            }else{
                amount_of_exist =0;
            }
        if(amount_of_exist ==5){
        result = true;
        }
    }
        return result;
    }

    @Override
    public boolean checkWinInSecondSlant(char znak) {
        boolean result = false;
        int amount_of_exist = 0;
        for (int i = 0, j = board.getFields().length - 1; i < board.getFields().length; i++, j--) {
            if (board.getFields()[i][j].getSymbol() == znak) {
                amount_of_exist++;
            }else{
                amount_of_exist = 0;
            }
            if(amount_of_exist ==5){
                result = true;
            }
        }
        return result;
    }

}
