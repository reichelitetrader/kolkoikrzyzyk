package pl.chizynski.kolkokrzyzyk.rules;

import pl.chizynski.kolkokrzyzyk.KolkoKrzyzyk;
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

    /**
     * @param znak wybor czy jest kolko czy krzyzyk
     * @param wiersz numer wiersza w ktorym nastapila wygrana
     * @return jesli wygrywa kolko/krzyzyk zwraca true jesli nie wygrywa false
     */
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
    }

    public boolean checkWinInSecondSlant(char znak) {
        boolean result = true;
        for (int i = 0, j = board.fields.length - 1; i < board.fields.length; i++, j--) {
            if (board.fields[i][j].getSymbol() != znak) {
                result = false;
            }
        }
        return result;
    }

    public boolean checkDraw() {

        return KolkoKrzyzyk.numerRuchu == getMaxNumberMoves() && !checkWin(KolkoKrzyzyk.kolko.getSymbol()) && !checkWin(KolkoKrzyzyk.krzyzyk.getSymbol());

    }

    public double getMaxNumberMoves() {

        return Math.pow(board.fields.length, 2) + 1;
    }
}
