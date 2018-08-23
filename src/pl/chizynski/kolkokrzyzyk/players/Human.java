package pl.chizynski.kolkokrzyzyk.players;

import java.util.Scanner;
import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.figures.FigureFactory;

public class Human extends Player {

    private static Scanner scanner = new Scanner(System.in);

    public Human(Board board) {
        super(board);
    }
    @Override
    public boolean move(int turnNumber) {
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
            if (Board.EMPTY == this.getBoard().getFields()[wiersz][kolumna]) {
                if (turnNumber % 2 != 0) {
                    this.getBoard().getFields()[wiersz][kolumna] = Board.CROSS;
                } else if (turnNumber % 2 == 0) {
                    this.getBoard().getFields()[wiersz][kolumna] = Board.CIRCLE;
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