package pl.chizynski.kolkokrzyzyk.players;

import pl.chizynski.kolkokrzyzyk.boards.Board;

public class PlayerFactory {

    public static Player createPlayer(String message, Board board, String wybor) {
        Player player = null;

        if (wybor.equals("K")) {
            player = new Computer(board);
        } else if (wybor.equals("C")) {
            player = new Human(board);
        }
        return player;
    }

}
