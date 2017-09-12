package pl.chizynski.kolkokrzyzyk.players;

import java.util.Scanner;
import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.figures.Kolko;
import pl.chizynski.kolkokrzyzyk.figures.Krzyzyk;

public class Human extends Player {

    public static Scanner scanner = new Scanner(System.in);

    public Human(Board board) {
        super(board);

    }

    @Override
    public boolean wykonaj_ruch(int numerRuchu, char wynik) {
        int wiersz = 0;
        int kolumna = 0;
        boolean value = true;
        System.out.println("wykonaj ruch:");

        try {
            System.out.println("pobierz wartosc wiersza:");
            wiersz = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exc) {
            value = false;
            System.out.println("podales niepoprawna wartosc:");
        } catch (ArrayIndexOutOfBoundsException exc) {
            value = false;
            System.out.println("wyszedles poza zakres:");
        }

        try {
            System.out.println("pobierz wartosc kolumny:");
            kolumna = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exc) {
            value = false;
            System.out.println("podales niepoprawna wartosc:");
        } catch (ArrayIndexOutOfBoundsException exc) {
            value = false;
            System.out.println("wyszedles poza zakres:");
        }

        try {
            if (Board.puste == this.board.fields[wiersz][kolumna]) {

                if (numerRuchu % 2 != 0) {
                    if (Board.kolko.getSymbol() == wynik) {
                        board.fields[wiersz][kolumna] = new Kolko();
                    } else {
                        board.fields[wiersz][kolumna] = new Krzyzyk();
                    }
                } else if (numerRuchu % 2 == 0) {
                    if (wynik == Board.kolko.getSymbol()) {
                        board.fields[wiersz][kolumna] = Board.krzyzyk;
                    } else if (wynik == Board.krzyzyk.getSymbol()) {
                        board.fields[wiersz][kolumna] = Board.kolko;
                    }
                }
            } else {
                System.out.println("to pole jest zajete wybierz inne:");
                value = false;
            }

        } catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println("wyszedles poza zakres:");
            value = false;
        }
       return value; 
    }

}
