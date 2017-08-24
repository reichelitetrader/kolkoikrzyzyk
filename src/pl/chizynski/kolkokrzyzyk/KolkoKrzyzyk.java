//gra kółko i krzyżyk
package pl.chizynski.kolkokrzyzyk;

import java.util.Scanner;
import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.figures.Figure;
import pl.chizynski.kolkokrzyzyk.figures.Kolko;
import pl.chizynski.kolkokrzyzyk.figures.Krzyzyk;
import pl.chizynski.kolkokrzyzyk.rules.Rules;

public class KolkoKrzyzyk {

    public static Figure kolko = new Kolko();
    public static Figure krzyzyk = new Krzyzyk();
    static Board board = new Board(3);
    static Rules rules = new Rules(board);

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
        System.out.println("wybor gracza, z kim chcesz zagrac? [K / C]:");
        wybor = scanner.nextLine();

        if (wybor.equals("K")) {
            System.out.println("rozpoczynasz nowa  gre z Computer:");

        } else if (wybor.equals("C")) {
            System.out.println("rozpoczynasz nowa gre z graczem:");
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

            if (rules.sprawdz_remis()) {
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

            if (wybor.equals("K") && numerRuchu % 2 != 0) {
                while (true) {
                    int x = (int) Math.floor(Math.random() * board.fields.length);
                    int y = (int) Math.floor(Math.random() * board.fields.length);
                    if (Board.puste == board.fields[x][y]) {
                        if (numerRuchu % 2 != 0) {
                            if (krzyzyk.getSymbol() == wynik) {
                                board.fields[x][y] = new Krzyzyk();
                            } else {
                                board.fields[x][y] = new Kolko();
                            }

                        } else if (numerRuchu % 2 == 0) {
                            if (wynik == kolko.getSymbol()) {
                                board.fields[x][y] = krzyzyk;
                            } else if (wynik == krzyzyk.getSymbol()) {
                                board.fields[x][y] = kolko;
                            }
                        }
                        break;
                    }
                }
            } else {
                value = pobierz_wartosc();
            }
            if (value == true) {
                numerRuchu++;
            } else {
                System.out.println("brak zwiekszania tury:");
            }

        }
    }

    public static Scanner scanner = new Scanner(System.in);

    public static boolean pobierz_wartosc() {
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
            if (Board.puste == board.fields[wiersz][kolumna]) {

                if (numerRuchu % 2 != 0) {
                    if (kolko.getSymbol() == wynik) {
                        board.fields[wiersz][kolumna] = new Kolko();
                    } else {
                        board.fields[wiersz][kolumna] = new Krzyzyk();
                    }
                } else if (numerRuchu % 2 == 0) {
                    if (wynik == kolko.getSymbol()) {
                        board.fields[wiersz][kolumna] = krzyzyk;
                    } else if (wynik == krzyzyk.getSymbol()) {
                        board.fields[wiersz][kolumna] = kolko;
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
