//gra kółko i krzyżyk
package pl.chizynski.kolkokrzyzyk;

import java.util.Scanner;
import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.figures.Figure;
import pl.chizynski.kolkokrzyzyk.figures.Kolko;
import pl.chizynski.kolkokrzyzyk.figures.Krzyzyk;
import pl.chizynski.kolkokrzyzyk.players.Computer;
import pl.chizynski.kolkokrzyzyk.players.Human;
import pl.chizynski.kolkokrzyzyk.players.Player;
import pl.chizynski.kolkokrzyzyk.rules.Rules;

public class Game {

    public static Figure kolko = new Kolko();
    public static Figure krzyzyk = new Krzyzyk();
    static Board board = new Board(3);
    static Rules rules = new Rules(board);
    static Player playerA;
    static Player playerB;

    public static int numerRuchu = 1;
    static char wynik = 0;

    public static void main(String[] args) {
        int wiersz = 0;
        int kolumna = 0;
        boolean value = true;
        boolean endofgame = false;

        board.displayState();
        System.out.println("wybierz kto zaczyna?:");
        wynik = wybierz_znak();

        String wybor = "";
        System.out.println("wybor gracza1 [K/C]:");
        wybor = scanner.nextLine();
        if (wybor.equals("K")) {
            playerA = new Computer(board);
        } else if (wybor.equals("C")) {
            playerA = new Human(board);
        }

        String wybor1 = "";
        System.out.println("wybor gracza2 [K/C]:");
        wybor1 = scanner.nextLine();
         if (wybor1.equals("K")) {
            playerB = new Computer(board);
        } else if (wybor1.equals("C")) {
            playerB = new Human(board);
        }

        String wybor_planszy = "";
        System.out.println("Wybierz wielkosc planszy M [3x3]  D [5x5] BD [7x7]:");
        wybor_planszy = scanner.nextLine();

        if (wybor_planszy.equals("M")) {
            System.out.println("wybrales plansze mala 3x3:");
        } else if (wybor_planszy.equals("D")) {
            System.out.println("wybrales plansze duza 5x5:");
            board = new Board(5);

        } else if (wybor_planszy.equals("BD")) {
            System.out.println("wybrales plansze duza 7x7:");
            board = new Board(7);
        }

        while (true) {
            System.out.println("kolejna tura:");
            board.displayState();

            if (rules.checkWin(krzyzyk.getSymbol())) {
                endofgame = true;
                System.out.println("wygrywa X:");
            }
            if (rules.checkWin(kolko.getSymbol())) {
                endofgame = true;
                System.out.println("wygrywa O:");

            }

            if (rules.checkDraw()) {
                endofgame = true;
                System.out.println("remis");

            }

            if (endofgame == true) {
                String T = "";
                System.out.println("czy chcesz rozpoczac nowa gre? [T/n]");
                T = scanner.nextLine();
                if (T.equals("T")) {

                    numerRuchu = 0;

                    endofgame = false;
                } else {
                    break;
                }
            }

            if (  numerRuchu % 2 != 0) {

                value = playerA.wykonaj_ruch(numerRuchu, wynik);

            } else {
                value = playerB.wykonaj_ruch(numerRuchu, wynik);
            }
            if (value == true) {
                numerRuchu++;
            } else {
                System.out.println("brak zwiekszania tury:");
            }

        }
    }

    public static Scanner scanner = new Scanner(System.in);

    public static char wybierz_znak() {
        String wybor = "";
        wybor = scanner.nextLine();
        if (wybor.equals("O")) {
            System.out.println("zaczyna kolko:");
            return wybor.charAt(0);

        } else if (wybor.equals("X")) {
            System.out.println("zaczyna krzyzyk:");
            return wybor.charAt(0);
        }
        return wynik;
    }

}
