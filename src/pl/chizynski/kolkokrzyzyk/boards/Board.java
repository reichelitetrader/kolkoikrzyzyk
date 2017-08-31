package pl.chizynski.kolkokrzyzyk.boards;

import pl.chizynski.kolkokrzyzyk.figures.Figure;
import pl.chizynski.kolkokrzyzyk.figures.Kolko;
import pl.chizynski.kolkokrzyzyk.figures.Krzyzyk;

public class Board {

    public static Figure puste = new Figure();
    public Figure[][] fields = new Figure[3][3];
      public static Figure kolko = new Kolko();
    public static Figure krzyzyk = new Krzyzyk();

    public Board(int size) {
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
