package pl.chizynski.kolkokrzyzyk.rules;

import pl.chizynski.kolkokrzyzyk.Game;
import pl.chizynski.kolkokrzyzyk.boards.Board;

public class Rules {

    public Board board = null;

    public Rules(Board board) {  //powiazac zeby rules wiedzialo na ktorym board pracowac
        this.board = board;

    }

    public boolean checkWin(char znak) {
        for (int i = 0; i < board.fields.length; i++) {
            if (checkWinInColumn(znak, i)) {
                return true;
            }

        }
        for (int i = 0; i < board.fields.length; i++) {
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

        for (int i = 0; i < board.fields[kolumna].length; i++) {
            //   System.out.println("["+kolumna+"]["+i+"]"); 
            if (board.fields[kolumna][i].getSymbol() != znak) {

                result = false;
            }
        }
        return result; 
    }
// metoda zwraca wygrana w kolumnie
//@param znak wybor czy kolko czy krzyzyk
//@param kolumna wybor kolumny w ktorej nastapila wygrana
//@return zwraca wynik jesli wygrywa kolko/krzyzyk zwraca true jesli nie false 

    public boolean checkWinInRow(char znak, int wiersz) {
        boolean result = true;
        for (int i = 0; i < board.fields[wiersz].length; i++) {
            // System.out.println("["+wiersz+"]["+i+"]"); 
            if (board.fields[i][wiersz].getSymbol() != znak) {
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

    public boolean checkWinInSlant(char znak) {
        return checkWinInFirstSlant(znak) || checkWinInSecondSlant(znak);
    }

    public boolean checkWinInFirstSlant(char znak) {
        boolean result = true;
        for (int i = 0; i < board.fields.length; i++) {
            if (board.fields[i][i].getSymbol() != znak) {
                result = false;
            }

        }
        return result;
    }//metoda sprawdza wygrana na 1 skosie
    //@param znak wybor czy kolko czy krzyzyk
    //@return jesli wygra kolko czy krzyzyk zwraca wynik true jesli nie to false

    public boolean checkWinInSecondSlant(char znak) {
        boolean result = true;
        for (int i = 0, j = board.fields.length - 1; i < board.fields.length; i++, j--) {
            if (board.fields[i][j].getSymbol() != znak) {
                result = false;
            }
        }
        return result;
    }
    //metoda zwraca wygrana na 2 skosie
    //@param znak wybiera czy kolko czy krzyzyk
    //@return zwraca wynik jesli wygra krzyzyk czy kolko to true jesli nie false

    public boolean checkDraw() {

        return Game.numerRuchu == getMaxNumberMoves() && !checkWin(Game.kolko.getSymbol()) && !checkWin(Game.krzyzyk.getSymbol());

    }

    public double getMaxNumberMoves() {

        return Math.pow(board.fields.length, 2) + 1;
    }
}
