//gra kółko i krzyżyk
package pl.chizynski.kolkokrzyzyk;

import java.util.Scanner;
import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.boards.BoardFactory;
import pl.chizynski.kolkokrzyzyk.players.PlayerFactory;
import pl.chizynski.kolkokrzyzyk.figures.Figure;
import pl.chizynski.kolkokrzyzyk.figures.FigureFactory;
import pl.chizynski.kolkokrzyzyk.players.Player;
import pl.chizynski.kolkokrzyzyk.rules.Rules;
import pl.chizynski.kolkokrzyzyk.rules.RulesFactory;

public class Game {

    public static Figure kolko = FigureFactory.createFigure('O');
    public static Figure krzyzyk = FigureFactory.createFigure('X');
    Board board = new Board(3);

    static Rules rules = null;

    static Player playerA;
    static Player playerB;
    static Player activePlayer;

    public static int numerRuchu = 1;
    static char wynik = 0;

    public static void main(String[] args) {
        int wiersz = 0;
        int kolumna = 0;
        boolean value = true;
        boolean endofgame = false;
        activePlayer = playerA;
        Game game = new Game();

        String boardSizeInString = "";
        System.out.println("Wybierz wielkosc planszy M [3x3]  D [5x5] BD [7x7] GUMOKU [13x13]:");
        boardSizeInString = scanner.nextLine();

        game.board = BoardFactory.createBoard(boardSizeInString);

        rules = RulesFactory.createRules(boardSizeInString, game.board);

        //TODO: Tworzy nowa plansze ale nie wyswietla stanow;
        System.out.println("wybierz kto zaczyna?:");
        wynik = wybierz_znak();
        playerA = game.choice_player("wybor gracza1 [K/C]:");
        playerB = game.choice_player("wybor gracza2 [K/C]:");

        while (true) {
            System.out.println("kolejna tura:");
            game.board.displayState();

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
                endofgame = game.askAboutNewGame();

                if (endofgame == true) {
                    break;

                }
            }

            if (!endofgame) {
                switchActivePlayer();
                value = activePlayer.wykonaj_ruch(numerRuchu, wynik);

                nextTurn(value);
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

    public Player choice_player(String message) {

        String wybor = "";
        Player player = null;
        System.out.println(message);
        wybor = scanner.nextLine();

        player = PlayerFactory.createPlayer(message, board, wybor);
        return player;
    }

    public static void switchActivePlayer() {
        if (numerRuchu % 2 != 0) {
            activePlayer = playerA;

        } else {
            activePlayer = playerB;

        }
    }

    public static void nextTurn(boolean value) {

        if (value == true) {
            numerRuchu++;
        } else {
            System.out.println("brak zwiekszania tury:");
        }
    }

    public boolean askAboutNewGame() {
        String T = "";
        boolean endofgame = true;
        System.out.println("czy chcesz rozpoczac nowa gre? [T/n]");
        T = scanner.nextLine();

        if (T.equals("T")) {
            activePlayer = playerA;
            numerRuchu = 0;
            board.setInitialState();
            endofgame = false;
        } else {

        }

        return endofgame;
    }

}
