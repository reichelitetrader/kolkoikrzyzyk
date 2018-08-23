package pl.chizynski.kolkokrzyzyk.figures;

public class FigureFactory {
    public static Figure createFigure(char symbol) {
        Figure figure = new Figure();
        if (symbol == 'O') {
            figure = new Kolko();
        } else if (symbol == 'X') {
            figure = new Krzyzyk();
        }
        return figure;
    }
}
