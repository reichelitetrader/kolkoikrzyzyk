package pl.chizynski.kolkokrzyzyk.boards;

import pl.chizynski.kolkokrzyzyk.Game;
import pl.chizynski.kolkokrzyzyk.figures.Figure;
import pl.chizynski.kolkokrzyzyk.figures.FigureFactory;


public class Board {

    public Game game = new Game();
    public static Figure puste = new Figure();
    public Figure[][] fields = new Figure[3][3];
    public static Figure kolko = FigureFactory.createFigure('O');
    public static Figure krzyzyk = FigureFactory.createFigure('X');
    

    public Board(int size, Game game) {
        this.game = game;     //referencja do game
        this.fields = new Figure[size][size];
        this.setInitialState();
    }

    public void setInitialState() {
        for (int i = 0; i < this.fields.length; i++) {
            for (int j = 0; j < this.fields[i].length; j++) {
                this.fields[i][j] = puste;
            }
        }
    }

    public void displayState() {
        System.out.println("ponizej stan planszy:");

        for (int i = 0; i < this.fields.length; i++) {
            System.out.print("|");
            for (int j = 0; j < this.fields[i].length; j++) {
                System.out.print(this.fields[i][j].getSymbol() + "|");
            }
            System.out.println("");
        }
    }
}
