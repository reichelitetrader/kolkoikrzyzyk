package pl.chizynski.kolkokrzyzyk.players;
import pl.chizynski.kolkokrzyzyk.boards.Board;

public abstract class Player  {
    private Board board;
    
    public Player(Board board){
        this.board = board;
    }
    
    public abstract boolean move(int turnNumber);
    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }
}