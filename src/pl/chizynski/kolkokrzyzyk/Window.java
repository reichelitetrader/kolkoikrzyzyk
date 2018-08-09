package pl.chizynski.kolkokrzyzyk;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.figures.Figure;

public class Window extends JFrame {

    private Game game = null;

    public Window(Game game) {

        this.game = game;
        JFrame frame = this; //new JFrame("test"); //utworzenie okna ramowego z tytułem 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout experimentLayout = new GridLayout(0, 1); //ilosc wierszy i kolumn
        this.setLayout(experimentLayout);
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(new GridLayout(3, 3));
        JPanel controls = new JPanel();

        JButton[][] buttons = new JButton[3][3];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton("X");
                compsToExperiment.add(buttons[i][j]);
                buttons[i][j].addActionListener(new CustomActionListener(game, i, j));

            }

//        button1.addActionListener(new ActionListener(){ //klasa anomimowa,
//            //jesli interfejs ma 1 metode to interfejs funkcyjny ze mozna go uzywac w programowaniu funkcyjnym
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("test");
//            }
//        });
            //button1.addActionListener((e) -> {     //lambda funkcja anonimowa do interfejsu funkcyjnego
            frame.getContentPane().add(compsToExperiment);
            frame.setVisible(true);// pokazanie okna 
            frame.pack(); //spakowanie okna 

        }
    }
}

class CustomActionListener implements ActionListener {

    private Game game = null;
    int i = 0;
    int j = 0;

    public CustomActionListener(Game game, int i, int j) {
        this.game = game;
        this.j = j;
        this.i = i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
   
        Board board = this.game.getBoard();
        Figure[][] fields = board.getFields();
        if (this.game.getNumerRuchu() % 2 != 0) {
            this.game.getBoard().getFields()[i][j] = Board.CROSS;
            
        } else if (this.game.getNumerRuchu() % 2 == 0) {
            this.game.getBoard().getFields()[i][j] = Board.CIRCLE;
        }
        this.game.nextTurn(true);
        this.game.getBoard().displayState();
    }

}
