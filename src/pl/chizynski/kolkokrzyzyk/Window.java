package pl.chizynski.kolkokrzyzyk;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
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
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");
        JButton button5 = new JButton("Button 5");
        JButton button6 = new JButton("Button 6");
        JButton button7 = new JButton("Button 7");
        JButton button8 = new JButton("Button 8");
        JButton button9 = new JButton("Button 9");
//        button1.addActionListener(new ActionListener(){ //klasa anomimowa,
//            //jesli interfejs ma 1 metode to interfejs funkcyjny ze mozna go uzywac w programowaniu funkcyjnym
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("test");
//            }
//        });
        //Add buttons to experiment with Grid Layout
        button1.addActionListener((e)->{     //lambda funkcja anonimowa do interfejsu funkcyjnego
            this.game.getBoard().getFields()[0][0] = Board.CROSS;
            this.game.getBoard().displayState();
        }); 
        button2.addActionListener((e)->{
            this.game.getBoard().getFields()[0][1] = Board.CROSS;
            this.game.getBoard().displayState();
        }); 
        button3.addActionListener((e)->{
            this.game.getBoard().getFields()[0][2] = Board.CROSS;
            this.game.getBoard().displayState();
        }); 
        button4.addActionListener((e)->{
            this.game.getBoard().getFields()[1][0] = Board.CROSS;
            this.game.getBoard().displayState();
        });
        button5.addActionListener((e)->{
            this.game.getBoard().getFields()[1][1] = Board.CROSS;
            this.game.getBoard().displayState();
        });
        button6.addActionListener((e)->{
            this.game.getBoard().getFields()[1][2] = Board.CROSS;
            this.game.getBoard().displayState();
        }); 
        button7.addActionListener((e)->{
            this.game.getBoard().getFields()[2][0] = Board.CROSS;
            this.game.getBoard().displayState();
        }); 
        button8.addActionListener((e)->{
            this.game.getBoard().getFields()[2][1] = Board.CROSS;
            this.game.getBoard().displayState();
        }); 
        button9.addActionListener((e)->{ 
            this.game.getBoard().getFields()[2][2] = Board.CROSS;
            this.game.getBoard().displayState();
        });
        compsToExperiment.add(button1);
        compsToExperiment.add(button2);
        compsToExperiment.add(button3);
        compsToExperiment.add(button4);
        compsToExperiment.add(button5);
        compsToExperiment.add(button6);
        compsToExperiment.add(button7);
        compsToExperiment.add(button8);
        compsToExperiment.add(button9);
        frame.getContentPane().add(compsToExperiment);
        frame.setVisible(true);// pokazanie okna 
        frame.pack(); //spakowanie okna 
    }
}
