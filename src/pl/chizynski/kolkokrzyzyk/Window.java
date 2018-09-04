package pl.chizynski.kolkokrzyzyk;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.figures.Figure;

public class Window extends JFrame {

    private Game game = null;
    JButton[][] buttons = new JButton[3][3];

    public Window(Game game) {
        this.game = game;

        JFrame frame = this; //new JFrame("test"); //utworzenie okna ramowego z tytułem 

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout experimentLayout = new GridLayout(0, 1); //ilosc wierszy i kolumn
        this.setLayout(experimentLayout);
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(new GridLayout(3, 3));
        JPanel controls = new JPanel();

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setText(" ");
                buttons[i][j].setSize(400, 200);
                compsToExperiment.add(buttons[i][j]);
                buttons[i][j].addActionListener(new CustomActionListener(game, this, i, j));
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
            frame.setSize(800, 700);
        }

    }

    public void clearButtons() {
        for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons.length; j++) {
                this.buttons[i][j].setText(" ");
            }
        }
    }
}

class CustomActionListener implements ActionListener {

    private Game game = null;
    private Window window = null;
    int i = 0;
    int j = 0;

    public CustomActionListener(Game game, Window window, int i, int j) {
        this.game = game;
        this.window = window;
        this.j = j;
        this.i = i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Board board = this.game.getBoard();
        Figure[][] fields = board.getFields();
        if (Board.EMPTY == this.game.getBoard().getFields()[i][j] && !this.game.getEndOfGame()) {

            if (this.game.getNumerRuchu() % 2 != 0) {
                this.game.getBoard().getFields()[i][j] = Board.CROSS;
                this.window.buttons[i][j].setText(String.valueOf(Board.CROSS.getSymbol()));
            } else if (this.game.getNumerRuchu() % 2 == 0) {
                this.game.getBoard().getFields()[i][j] = Board.CIRCLE;
                this.window.buttons[i][j].setText(String.valueOf(Board.CIRCLE.getSymbol()));
            }

            this.game.nextTurn(true);

        } else {
            System.out.println("to pole jest zajete wybierz inne:");
        }

        if (this.game.checkWin(Board.CROSS.getSymbol())) {
            JOptionPane.showMessageDialog(null, "wygrywa X:");
            int result = JOptionPane.showConfirmDialog(window, "czy chcesz rozpoczac nowa gre?");

            switch (result) {
                case JOptionPane.YES_OPTION:
                    this.window.clearButtons();
                    this.game.newGame();
                    break;
                case JOptionPane.NO_OPTION:
                    System.exit(0);
                    break;

                //default:  else
            }

        } else if (this.game.checkWin(Board.CIRCLE.getSymbol())) {
            JOptionPane.showMessageDialog(null, "wygrywa O:");
        }

        this.game.getBoard().displayState();

    }
}
