package pl.chizynski.kolkokrzyzyk.rules;
import pl.chizynski.kolkokrzyzyk.boards.Board;

public class Rules {
    private Board board = null;

    public Rules(Board board) {  //powiazac zeby rules wiedzialo na ktorym board pracowac
        this.board = board;
    }

    public boolean checkWin(char znak) {
        for (int i = 0; i < board.getFields().length; i++) {
            if (checkWinInColumn(znak, i)) {
                return true;
            }
        }
        for (int i = 0; i < board.getFields().length; i++) {
            if (checkWinInRow(znak, i)) {
                return true;
            }
        }
        if (checkWinInSlant(znak)) {
            return true;
        }
        return false;
    }

    public boolean checkWinInColumn(char znak, int kolumna) {
        boolean result = true;

        for (int i = 0; i < board.getFields()[kolumna].length; i++) {
            //   System.out.println("["+kolumna+"]["+i+"]"); 
            if (board.getFields()[kolumna][i].getSymbol() != znak) {
                result = false;
            }
        }
        return result; 
    }
// metoda zwraca wygrana w kolumnie
//@param znak wybor czy CIRCLE czy CROSS
//@param kolumna wybor kolumny w ktorej nastapila wygrana
//@return zwraca wynik jesli wygrywa CIRCLE/krzyzyk zwraca true jesli nie false 

    public boolean checkWinInRow(char znak, int wiersz) {
        boolean result = true;
        for (int i = 0; i < board.getFields()[wiersz].length; i++) {
            // System.out.println("["+wiersz+"]["+i+"]"); 
            if (board.getFields()[i][wiersz].getSymbol() != znak) {
                result = false;
            }
        }
        return result;
    }
    /**
     * metoda sprawdza wygrana w wierszu
     * @param znak wybor czy jest kolko czy krzyzyk
     * @param wiersz numer wiersza w ktorym nastapila wygrana
     * @return jesli wygrywa kolko/krzyzyk zwraca true jesli nie wygrywa false
     */

    /**
     * metoda sprawdza wygrana w wierszu
     * @param znak wybor czy jest CIRCLE czy CROSS
     * @param wiersz numer wiersza w ktorym nastapila wygrana
     * @return jesli wygrywa CIRCLE/CROSS zwraca true jesli nie wygrywa false
     */
    public boolean checkWinInSlant(char znak) {
        return checkWinInFirstSlant(znak) || checkWinInSecondSlant(znak);
    }

    public boolean checkWinInFirstSlant(char znak) {
        boolean result = true;
        for (int i = 0; i < board.getFields().length; i++) {
            if (board.getFields()[i][i].getSymbol() != znak) {
                result = false;
            }
        }
        return result;
    }//metoda sprawdza wygrana na 1 skosie
    //@param znak wybor czy CIRCLE czy CROSS
    //@return jesli wygra CIRCLE czy CROSS zwraca wynik true jesli nie to false

    public boolean checkWinInSecondSlant(char znak) {
        boolean result = true;
        for (int i = 0, j = board.getFields().length - 1; i < board.getFields().length; i++, j--) {
            if (board.getFields()[i][j].getSymbol() != znak) {
                result = false;
            }
        }
        return result;
    }
    //metoda zwraca wygrana na 2 skosie
    //@param znak wybiera czy CIRCLE czy CROSS
    //@return zwraca wynik jesli wygra CROSS czy CIRCLE to true jesli nie false

    public boolean checkDraw() {
        return board.getGame().getTurnNumber()  == getMaxNumberMoves()
                && !checkWin(Board.CIRCLE.getSymbol()) 
                && !checkWin(Board.CROSS.getSymbol());
    }

    public double getMaxNumberMoves() {
        return Math.pow(board.getFields().length, 2) + 1;
    }
    
    public Board getBoard(){
        return board;
    }
    public void setBoard(Board board){
        this.board = board;
    }
}