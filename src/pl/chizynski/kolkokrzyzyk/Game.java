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
    public static int numerRuchu = 1;
    Board board = new Board(3);

    Rules rules = null;

    Player playerA;
    Player playerB;
    Player activePlayer;

    char wynik = 0;

    public static void main(String[] args) {
        int wiersz = 0;
        int kolumna = 0;
        boolean value = true;
        boolean endofgame = false;
        Game game = new Game();
        game.activePlayer = game.playerA;

        String boardSizeInString = "";
        System.out.println("Wybierz wielkosc planszy M [3x3]  D [5x5] BD [7x7] GUMOKU [13x13]:");
        boardSizeInString = scanner.nextLine();

        game.board = BoardFactory.createBoard(boardSizeInString);

        game.rules = RulesFactory.createRules(boardSizeInString, game.board);

        //TODO: Tworzy nowa plansze ale nie wyswietla stanow;
        System.out.println("wybierz kto zaczyna?:");
        game.wynik = game.wybierz_znak();
        game.playerA = game.choice_player("wybor gracza1 [K/C]:");
        game.playerB = game.choice_player("wybor gracza2 [K/C]:");

        while (true) {
            System.out.println("kolejna tura:");
            game.board.displayState();

            if (game.rules.checkWin(game.krzyzyk.getSymbol())) {
                endofgame = true;
                System.out.println("wygrywa X:");
            }
            if (game.rules.checkWin(game.kolko.getSymbol())) {
                endofgame = true;
                System.out.println("wygrywa O:");

            }

            if (game.rules.checkDraw()) {
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
                game.switchActivePlayer();
                value = game.activePlayer.wykonaj_ruch(game.numerRuchu, game.wynik);

                game.nextTurn(value);
            }

        }
    }

    public static Scanner scanner = new Scanner(System.in);

    public char wybierz_znak() {
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

    public void switchActivePlayer() {
        if (numerRuchu % 2 != 0) {
            activePlayer = playerA;

        } else {
            activePlayer = playerB;

        }
    }

    public void nextTurn(boolean value) {

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
