package pl.chizynski.kolkokrzyzyk;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pl.chizynski.kolkokrzyzyk.boards.Board;

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
                buttons[i][j].addActionListener((e) -> {
                    this.game.getBoard().getFields()[0][0] = Board.CROSS;
                    this.game.getBoard().displayState();
                });

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
