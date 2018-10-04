package pl.chizynski.kolkokrzyzyk;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pl.chizynski.kolkokrzyzyk.boards.Board;
import pl.chizynski.kolkokrzyzyk.boards.BoardFactory;
import pl.chizynski.kolkokrzyzyk.figures.Figure;
import pl.chizynski.kolkokrzyzyk.players.Computer;
import pl.chizynski.kolkokrzyzyk.rules.RulesFactory;

public class Window extends JFrame {

    String playerTypes[] = {"Computer", "Human"};
    String playerVersus[] = {"Human", "Computer"};
    JComboBox playerTypesCombo;
    JComboBox playerVersusCombo;

    private Game game = null;
    JButton[][] buttons = new JButton[3][3];
    JButton buttonName = new JButton();

    public Window(Game game) {
        this.game = game;
        this.buttonName = new JButton("Start");
        this.playerTypesCombo = new JComboBox(playerTypes);
        this.playerVersusCombo = new JComboBox(playerVersus);
        JFrame frame = this; //utworzenie okna ramowego z tytułem 

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout experimentLayout = new GridLayout(0, 1); //ilosc wierszy i kolumn
        this.setLayout(experimentLayout);
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(new GridLayout(4, 3));
        compsToExperiment.add(playerTypesCombo);
        compsToExperiment.add(playerVersusCombo);
        compsToExperiment.add(this.buttonName);
         
        buttonName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "bedzie gral " + playerTypesCombo.getSelectedItem() + " vs " + playerVersusCombo.getSelectedItem());
                game.setBoard(BoardFactory.createBoard("M", game));
                game.setRules(RulesFactory.createRules("M", game.getBoard()));
                unblockButtons();
                game.newGame();
            }
        });
        JPanel controls = new JPanel();

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setText(" ");
                buttons[i][j].setSize(400, 200);
                compsToExperiment.add(buttons[i][j]);
                buttons[i][j].addActionListener(new CustomActionListener(game, this, i, j));
            }
            
            frame.getContentPane().add(compsToExperiment);
            frame.setVisible(true);// pokazanie okna 
            frame.pack(); //spakowanie okna 
            frame.setSize(600, 400);
        }
        blockButtons();
    }

    public void clearButtons() {
        for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons.length; j++) {
                this.buttons[i][j].setText(" ");
            }
        }
    }

    public void refreshDisplayState() {
        for (int i = 0; i < this.game.getBoard().getFields().length; i++) {
            for (int j = 0; j < this.game.getBoard().getFields().length; j++) {
                String value = Character.toString(this.game.getBoard().getFields()[i][j].getSymbol());
                buttons[i][j].setText(value);
            }
        }
    }

    public void blockButtons() {
        for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons.length; j++) {
                this.buttons[i][j].setEnabled(false);
            }
        }
    }
    
    public void unblockButtons(){
         for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons.length; j++) {
                this.buttons[i][j].setEnabled(true);
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
        Computer computer = new Computer(board);

        if (Board.EMPTY == this.game.getBoard().getFields()[i][j] && !this.game.getEndOfGame()) {
            if (this.game.getTurnNumber() % 2 != 0) {

                this.game.getBoard().getFields()[i][j] = Board.CROSS;
                //this.window.buttons[i][j].setText(String.valueOf(Board.CROSS.getSymbol()));

            } else if (this.game.getTurnNumber() % 2 == 0) {
                this.game.getBoard().getFields()[i][j] = Board.CIRCLE;
                //this.window.buttons[i][j].setText(String.valueOf(Board.CIRCLE.getSymbol()));
            }

            if (this.game.checkIfOnePlayerIsComputer()) {
                this.game.nextTurn(true);
                computer.move(this.game.getTurnNumber());
            }
            this.window.refreshDisplayState();
            this.game.nextTurn(true);

        } else {
            System.out.println("to pole jest zajete wybierz inne:");
        }
        System.out.println("jestem przyciskiem i poczulem sie dotkniety:" + i + " " + j);

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
}