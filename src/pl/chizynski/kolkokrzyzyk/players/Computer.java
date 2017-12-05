package pl.chizynski.kolkokrzyzyk.players;

import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.figures.FigureFactory;

public class Computer extends Player {

    public Computer(Board board) {
        super(board);
    }

    @Override
    public boolean move(int numerRuchu, char wynik) {

        while (true) {
            int x = randomaise();
            int y = randomaise();
            
            if (Board.EMPTY == board.getFields()[x][y]) {
                if (numerRuchu % 2 != 0) {
                    if (Board.CROSS.getSymbol() == wynik) {
                        board.getFields()[x][y] = Board.CROSS;
                    } else {
                        board.getFields()[x][y] = Board.CIRCLE;
                    }

                } else if (numerRuchu % 2 == 0) {
                    if (wynik == Board.CIRCLE.getSymbol()) {
                        board.getFields()[x][y] = Board.CROSS;
                    } else if (wynik == Board.CROSS.getSymbol()) {
                        board.getFields()[x][y] = Board.CIRCLE;
                    }
                }
                break;
            }
        }
        return true;
    }

    public int randomaise() {

        int x = (int) Math.floor(Math.random() * board.getFields().length);
        return x;
    }
}
