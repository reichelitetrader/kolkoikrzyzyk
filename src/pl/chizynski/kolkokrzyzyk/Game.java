//gra kółko i krzyżyk
package pl.chizynski.kolkokrzyzyk;

import java.util.Scanner;
import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.boards.BoardFactory;
import pl.chizynski.kolkokrzyzyk.players.PlayerFactory;
import pl.chizynski.kolkokrzyzyk.players.Player;
import pl.chizynski.kolkokrzyzyk.rules.Rules;
import pl.chizynski.kolkokrzyzyk.rules.RulesFactory;

public class Game {

    private int numerRuchu = 1;
    private Board board = null;
    private Rules rules = null;
    private Player playerA;
    private Player playerB;
    private Player activePlayer;
    private char figure = 0;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        Game game = new Game();
        Window window = new Window(game);
        game.startGame();
    }

    public char whoStarts() {
        String wybor = "";
        wybor = scanner.nextLine();
        if (wybor.equals("O")) {
            System.out.println("zaczyna kolko:");
            return wybor.charAt(0);

        } else if (wybor.equals("X")) {
            System.out.println("zaczyna krzyzyk:");
            return wybor.charAt(0);
        }
        return figure;
    }

    public Player createPlayer(String message) {

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

    public void startGame() {
        int wiersz = 0;
        int kolumna = 0;
        boolean value = true;
        boolean endofgame = false;

        activePlayer = playerA;  // JESLI METODA NIE JEST STATYCZNA UZYWAMY THIS LUB NIE MUSIMY SIE ODWOLYWAC POPRZEZ OBIEKTY 

        String boardSizeInString = "";
        System.out.println("Wybierz wielkosc planszy M [3x3]  D [5x5] BD [7x7] GUMOKU [13x13]:");
        boardSizeInString = scanner.nextLine();

        board = BoardFactory.createBoard(boardSizeInString, this);//wskazanmie na obiekto w ktorym jestrem

        rules = RulesFactory.createRules(boardSizeInString, board);

        //TODO: Tworzy nowa plansze ale nie wyswietla stanow;
        System.out.println("wybierz kto zaczyna?:");
        figure = whoStarts();
        playerA = createPlayer("wybor gracza1 [K/C]:");
        playerB = createPlayer("wybor gracza2 [K/C]:");

        while (true) {
            System.out.println("kolejna tura:");
            board.displayState();

            if (rules.checkWin(Board.CROSS.getSymbol())) {
                endofgame = true;
                System.out.println("wygrywa X:");
            }
            if (rules.checkWin(Board.CIRCLE.getSymbol())) {
                endofgame = true;
                System.out.println("wygrywa O:");

            }

            if (rules.checkDraw()) {
                endofgame = true;
                System.out.println("remis");

            }

            if (endofgame == true) {
                endofgame = askAboutNewGame();

                if (endofgame == true) {
                    break;

                }
            }

            if (!endofgame) {
                switchActivePlayer();
                value = activePlayer.move(numerRuchu, figure);

                nextTurn(value);
            }

        }

    }
    
    public int getNumerRuchu(){
        return numerRuchu;
    }

    public void setNumerRuchu(int numerRuchu){
        this.numerRuchu = numerRuchu;
    }
    
    public Board getBoard(){
        return board;
    }
    
    public void setBoard(Board board){
        this.board = board;
    }
    
    public Rules getRules(){
        return rules;
    }
    public void setRules(Rules rules){
        this.rules = rules;
    }
}
