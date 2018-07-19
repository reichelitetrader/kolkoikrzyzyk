package pl.chizynski.kolkokrzyzyk.players;

import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.figures.FigureFactory;

public class Computer extends Player {

    public Computer(Board board) {
        super(board);
    }

    @Override
    public boolean move(int turnNumber, char figure) {

        while (true) {
            int x = randomaise();
            int y = randomaise();
            
            if (Board.EMPTY == this.getBoard().getFields()[x][y]) {
                if (turnNumber % 2 != 0) {
                    if (Board.CROSS.getSymbol() == figure) {
                        this.getBoard().getFields()[x][y] = Board.CROSS;
                    } else {
                        this.getBoard().getFields()[x][y] = Board.CIRCLE;
                    }

                } else if (turnNumber % 2 == 0) {
                    if (figure == Board.CIRCLE.getSymbol()) {
                        this.getBoard().getFields()[x][y] = Board.CROSS;
                    } else if (figure == Board.CROSS.getSymbol()) {
                        this.getBoard().getFields()[x][y] = Board.CIRCLE;
                    }
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
