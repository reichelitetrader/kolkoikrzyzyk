package pl.chizynski.kolkokrzyzyk.players;

import static pl.chizynski.kolkokrzyzyk.Game.kolko;
import static pl.chizynski.kolkokrzyzyk.Game.krzyzyk;
import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.figures.FigureFactory;
import pl.chizynski.kolkokrzyzyk.figures.Kolko;
import pl.chizynski.kolkokrzyzyk.figures.Krzyzyk;

public class Computer extends Player {

    public Computer(Board board) {
        super(board);
    }

    @Override
    public boolean move(int numerRuchu, char wynik) {

        while (true) {
            int x = (int) Math.floor(Math.random() * this.board.getFields().length);
            int y = (int) Math.floor(Math.random() * board.getFields().length);
            if (Board.puste == board.getFields()[x][y]) {
                if (numerRuchu % 2 != 0) {
                    if (krzyzyk.getSymbol() == wynik) {
                        board.getFields()[x][y] = FigureFactory.createFigure('X');
                    } else {
                        board.getFields()[x][y] = FigureFactory.createFigure('O');
                    }

                } else if (numerRuchu % 2 == 0) {
                    if (wynik == kolko.getSymbol()) {
                        board.getFields()[x][y] = krzyzyk;
                    } else if (wynik == krzyzyk.getSymbol()) {
                        board.getFields()[x][y] = kolko;
                    }
                }
                break;
            }
        }
        return true;
    }

}
