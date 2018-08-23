package pl.chizynski.kolkokrzyzyk.boards;
import pl.chizynski.kolkokrzyzyk.Game;

public class BoardFactory {
    public static Board createBoard(String boardSizeInString, Game game) {
        Board board = new Board(3, game);
        if (boardSizeInString.equals("M")) {
            System.out.println("wybrales plansze mala 3x3:");
        } else if (boardSizeInString.equals("D")) {
            System.out.println("wybrales plansze duza 5x5:");
            board = new Board(5,game);

        } else if (boardSizeInString.equals("BD")) {
            System.out.println("wybrales plansze duza 7x7:");
            board = new Board(7, game);
        } else if (boardSizeInString.equals("GUMOKU")){
            System.out.println("wybrales plansze duza 13x13:");
            board = new Board(13, game);
        }
        return board;
    }
}
