package pl.chizynski.kolkokrzyzyk;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pl.chizynski.kolkokrzyzyk.boards.Board;

public class Window extends JFrame {
    private Game game = new Game();

    public Window(Game game) {
        this.game = game;
        JFrame frame = this; //new JFrame("test"); //utworzenie okna ramowego z tytułem 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout experimentLayout = new GridLayout(0, 1); //ilosc wierszy i kolumn
        this.setLayout(experimentLayout);
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(new GridLayout(3,3));
        JPanel controls = new JPanel(); 
        JButton button = new JButton("Button 1");
        
        button.addActionListener(new ActionListener(){ //klasa anomimowa,
            //jesli interfejs ma 1 metode to interfejs funkcyjny ze mozna go uzywac w programowaniu funkcyjnym
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("test");
            }
        });
        //Add buttons to experiment with Grid Layout
        button.addActionListener((e)->{
           
            System.out.println("test2");
            this.game.getBoard().getFields()[0][0] = Board.CROSS;
            this.game.getBoard().displayState();
        }); 
//lambda funkcja anonimowa do interfejsu funkcyjnego
        compsToExperiment.add(button);
        compsToExperiment.add(new JButton("Button 2"));
        compsToExperiment.add(new JButton("Button 3"));
        compsToExperiment.add(new JButton("Button 4"));
        compsToExperiment.add(new JButton("Button 5"));
        compsToExperiment.add(new JButton("Button 6"));
        compsToExperiment.add(new JButton("Button 7"));
        compsToExperiment.add(new JButton("Button 8"));
        compsToExperiment.add(new JButton("Button 9"));

        frame.getContentPane().add(compsToExperiment);
        frame.setVisible(true);// pokazanie okna 
        frame.pack(); //spakowanie okna 

    }
}
