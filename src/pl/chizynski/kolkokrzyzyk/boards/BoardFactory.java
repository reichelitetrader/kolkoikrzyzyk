package pl.chizynski.kolkokrzyzyk.boards;

public class BoardFactory {

    public static Board createBoard(String boardSizeInString) {
        Board board = new Board(5);
        if (boardSizeInString.equals("M")) {
            System.out.println("wybrales plansze mala 3x3:");
        } else if (boardSizeInString.equals("D")) {
            System.out.println("wybrales plansze duza 5x5:");
            board = new Board(5);

        } else if (boardSizeInString.equals("BD")) {
            System.out.println("wybrales plansze duza 7x7:");
            board = new Board(7);
        }
        return board;
    }

}
