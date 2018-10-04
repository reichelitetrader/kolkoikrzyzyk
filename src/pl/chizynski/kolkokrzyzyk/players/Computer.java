package pl.chizynski.kolkokrzyzyk.players;
import pl.chizynski.kolkokrzyzyk.boards.Board;

public class Computer extends Player {

    public Computer(Board board) {
        super(board);
    }

    @Override
    public boolean move(int turnNumber) {
        while (true) {
            int x = randomaise();
            int y = randomaise();
            
            if (Board.EMPTY == this.getBoard().getFields()[x][y]) {
                if (turnNumber % 2 != 0) {
                        this.getBoard().getFields()[x][y] = Board.CROSS;
                } else if (turnNumber % 2 == 0) {
                        this.getBoard().getFields()[x][y] = Board.CIRCLE;
                    } 
                break;
            }
        }
        return true;
    }

    public int randomaise() {
        int x = (int) Math.floor(Math.random() * this.getBoard().getFields().length);
        return x;
    }
}