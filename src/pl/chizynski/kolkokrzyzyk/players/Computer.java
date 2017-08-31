package pl.chizynski.kolkokrzyzyk.players;

import static pl.chizynski.kolkokrzyzyk.Game.kolko;
import static pl.chizynski.kolkokrzyzyk.Game.krzyzyk;
import static pl.chizynski.kolkokrzyzyk.Game.numerRuchu;
import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.figures.Kolko;
import pl.chizynski.kolkokrzyzyk.figures.Krzyzyk;

public class Computer extends Player {


    public Computer(Board board) {
        super(board);
    }

    public void wykonaj_ruch(int wynik) {

        while (true) {
            int x = (int) Math.floor(Math.random() * this.board.fields.length);
            int y = (int) Math.floor(Math.random() * board.fields.length);
            if (Board.puste == board.fields[x][y]) {
                if (numerRuchu % 2 != 0) {
                    if (krzyzyk.getSymbol() == wynik) {
                        board.fields[x][y] = new Krzyzyk();
                    } else {
                        board.fields[x][y] = new Kolko();
                    }

                } else if (numerRuchu % 2 == 0) {
                    if (wynik == kolko.getSymbol()) {
                        board.fields[x][y] = krzyzyk;
                    } else if (wynik == krzyzyk.getSymbol()) {
                        board.fields[x][y] = kolko;
                    }
                }
                break;
            }
        }

    }

}
