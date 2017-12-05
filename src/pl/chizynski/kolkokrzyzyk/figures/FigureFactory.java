package pl.chizynski.kolkokrzyzyk.figures;

public class FigureFactory {

    public static Figure createFigure(char symbol) {
        char kolko = 'O';
        char krzyzyk = 'X';
        Figure figure = new Figure();
        if (symbol == 'O') {
            figure = new Kolko();
        } else if (symbol == 'X') {
            figure = new Krzyzyk();
        }
        return figure;
    }

}
