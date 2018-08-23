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
        for (int i = 0; i < getBoard().getFields()[wiersz].length; i++) {
            // System.out.println("["+wiersz+"]["+i+"]"); 
            if (getBoard().getFields()[i][wiersz].getSymbol() == znak) {
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
        for (int i = 0; i < getBoard().getFields()[kolumna].length; i++) {
            //   System.out.println("["+kolumna+"]["+i+"]"); 
            if (getBoard().getFields()[kolumna][i].getSymbol() == znak) {
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
        for (int i = 0; i < getBoard().getFields().length; i++) {
            if (getBoard().getFields()[i][i].getSymbol() == znak) {
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
        for (int i = 0, j = getBoard().getFields().length - 1; i < getBoard().getFields().length; i++, j--) {
            if (getBoard().getFields()[i][j].getSymbol() == znak) {
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