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
            if (sprawdz_wygrana_w_kolumnie(znak, i)) {
                return true;
            }

        }
        for (int i = 0; i < board.fields.length; i++) {
            if (sprawdz_wygrana_w_wierszu(znak, i)) {
                return true;
            }
        }

        if (sprawdz_wygrana_na_skos(znak)) {
            return true;
        }
        return false;
    }

    public boolean sprawdz_wygrana_w_kolumnie(char znak, int kolumna) {
        boolean result = true;

        for (int i = 0; i < board.fields[kolumna].length; i++) {
            //   System.out.println("["+kolumna+"]["+i+"]"); 
            if (board.fields[kolumna][i].getSymbol() != znak) {

                result = false;
            }
        }
        return result;
    }

    public boolean sprawdz_wygrana_w_wierszu(char znak, int wiersz) {
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
     * @param znak wybor czy jest kolko czy krzyzyk
     * @param wiersz numer wiersza w ktorym nastapila wygrana
     * @return jesli wygrywa kolko/krzyzyk zwraca true jesli nie wygrywa false
     */

    public boolean sprawdz_wygrana_na_skos(char znak) {
        return sprawdz_wygrana_na_skos_1(znak) || sprawdz_wygrana_na_skos_2(znak);
    }

    public boolean sprawdz_wygrana_na_skos_1(char znak) {
        boolean result = true;
        for (int i = 0; i < board.fields.length; i++) {
            if (board.fields[i][i].getSymbol() != znak) {
                result = false;
            }

        }
        return result;
    }

    public boolean sprawdz_wygrana_na_skos_2(char znak) {
        boolean result = true;
        for (int i = 0, j = board.fields.length - 1; i < board.fields.length; i++, j--) {
            if (board.fields[i][j].getSymbol() != znak) {
                result = false;
            }
        }
        return result;
    }

    public boolean sprawdz_remis() {
        
        return KolkoKrzyzyk.numerRuchu == pobierz_max_ilosc_ruchow() && !checkWin(KolkoKrzyzyk.kolko.getSymbol()) && !checkWin(KolkoKrzyzyk.krzyzyk.getSymbol());

    }
    
    public double pobierz_max_ilosc_ruchow() {

        return Math.pow(board.fields.length, 2) + 1;
    }
}
