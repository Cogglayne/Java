
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Board extends JFrame {

    public Board() {
        super("TicTacToe");
        add(new BoardLayout());
        getContentPane().setLayout(new GridLayout(0, BoxLayout.Y_AXIS));
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

class BoardLayout extends JPanel {

    private final JTextField[] squares = new JTextField[10]; // the final textfield is turn tracker
    private String turn;
    private String playerOne;
    private String playerTwo;

    public BoardLayout() {
        boardLayout();

    }

    /**
     * Returns true and shows the winner if a player has three in a row returns false otherwise.
     * Clears the board after a game has been won, alternates turns to keep turn tracker showing who won.
     */
    private boolean threeInARow() {
        if (squares[0].getText().equalsIgnoreCase(turn) && squares[1].getText().equalsIgnoreCase(turn) && squares[2].getText().equalsIgnoreCase(turn)) {
            JOptionPane.showMessageDialog(null, turn + " is the winner");
            alternateTurns();
            clearBoard();
            return true;
        }
        if (squares[0].getText().equalsIgnoreCase(turn) && squares[3].getText().equalsIgnoreCase(turn) && squares[6].getText().equalsIgnoreCase(turn)) {
            JOptionPane.showMessageDialog(null, turn + " is the winner");
            alternateTurns();
            clearBoard();
            return true;
        }
        if (squares[0].getText().equalsIgnoreCase(turn) && squares[4].getText().equalsIgnoreCase(turn) && squares[8].getText().equalsIgnoreCase(turn)) {
            JOptionPane.showMessageDialog(null, turn + " is the winner");
            alternateTurns();
            clearBoard();
            return true;
        }
        if (squares[6].getText().equalsIgnoreCase(turn) && squares[7].getText().equalsIgnoreCase(turn) && squares[8].getText().equalsIgnoreCase(turn)) {
            JOptionPane.showMessageDialog(null, turn + " is the winner");
            alternateTurns();
            clearBoard();
            return true;
        }
        if (squares[6].getText().equalsIgnoreCase(turn) && squares[4].getText().equalsIgnoreCase(turn) && squares[2].getText().equalsIgnoreCase(turn)) {
            JOptionPane.showMessageDialog(null, turn + " is the winner");
            alternateTurns();
            clearBoard();
            return true;
        }
        if (squares[2].getText().equalsIgnoreCase(turn) && squares[5].getText().equalsIgnoreCase(turn) && squares[8].getText().equalsIgnoreCase(turn)) {
            JOptionPane.showMessageDialog(null, turn + " is the winner");
            alternateTurns();
            clearBoard();
            return true;
        }
        if (squares[3].getText().equalsIgnoreCase(turn) && squares[4].getText().equalsIgnoreCase(turn) && squares[5].getText().equalsIgnoreCase(turn)) {
            JOptionPane.showMessageDialog(null, turn + " is the winner");
            alternateTurns();
            clearBoard();
            return true;
        }
        if (squares[1].getText().equalsIgnoreCase(turn) && squares[4].getText().equalsIgnoreCase(turn) && squares[7].getText().equalsIgnoreCase(turn)) {
            JOptionPane.showMessageDialog(null, turn + " is the winner");
            alternateTurns();
            clearBoard();
            return true;
        }
        return false;
    }

    /**
     * Clears the board
     */
    private void clearBoard() {
        for (JTextField square : squares) {
            square.setText("");
        }
    }

    /**
     * Changes which player can make a move, sets turn tracker to the current player
     */
    private void alternateTurns() {
        if (turn.equalsIgnoreCase(playerOne)) {
            turn = playerTwo;
            squares[9].setText(playerTwo);

        } else if (turn.equalsIgnoreCase(playerTwo)) {
            turn = playerOne;
            squares[9].setText(playerOne);
        }// if its player one's turn it changes to player two's turn and if its player two's turn it changes to player one's turn 
        
    }

    /**
     * Handles stylistic changes and running the game
     */
    private void boardLayout() {

        UIManager manager = new UIManager();
        UIManager.put("Panel.background", Color.black);
        UIManager.put("OptionPane.background", Color.black);
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("OptionPane.messageFont", new Font("Times New Roman", Font.BOLD, 20));
        // changes the apperance and font of my JOptionMessages
        setLayout(new GridLayout(4, 3));

        for (int i = 0; i < squares.length; i++) {
            final int test = i;
            squares[i] = new JTextField();
            squares[i].setHorizontalAlignment(JTextField.CENTER);
            squares[i].setFont(new Font("Times New Roman", Font.BOLD, 70));
            squares[i].setBackground(Color.black);
            squares[i].setForeground(Color.white);
            squares[i].setCaretColor(Color.white);
            add(squares[i]);
            // changes the apperance and font of my JTextFields and then adds them to the JPanel
            squares[i].addActionListener(e -> {
                try {
                    if (!squares[test].getText().equalsIgnoreCase(turn)) {
                        if (squares[test].isFocusOwner()) {
                            squares[test].setText("");
                        } // if the text entered does not match turn then the textfield with the cursor is emptied

                        alternateTurns();
                        // alternates turns so a player who dosent put in the correct input won't lose their turn
                    } else {
                        squares[test].setEditable(false);
                    }// after text is entered that matches the current turn the textfield is set to uneditable

                    if (threeInARow()) {
                        for (JTextField square : squares) {
                            square.setEditable(false);
                        }
                    }// after a game has been won all textfields are set to uneditable
                    alternateTurns();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, " Hit start to play");

                }
            });
        }// creating the JTextfields for the game

        for (JTextField textField : squares) {
           textField.setHorizontalAlignment(JTextField.CENTER);
           textField.setFont(new Font("Times New Roman", Font.BOLD, 70));
           textField.setBackground(Color.black);
           textField.setForeground(Color.white);
           textField.setCaretColor(Color.white);
           add(textField);
           // changes the apperance and font of my JTextFields and then adds them to the JPanel
           textField.addActionListener(e -> {
               try {
                   if (!textField.getText().equalsIgnoreCase(turn)) {
                       if (textField.isFocusOwner()) {
                           textField.setText("");
                       } // if the text entered does not match turn then the textfield with the cursor is emptied

                       alternateTurns();
                       // alternates turns so a player who dosent put in the correct input won't lose their turn
                   } else {
                       textField.setEditable(false);
                   }// after text is entered that matches the current turn the textfield is set to uneditable

                   if (threeInARow()) {
                       for (JTextField square : squares) {
                           square.setEditable(false);
                       }
                   }// after a game has been won all textfields are set to uneditable
                   alternateTurns();

               } catch (Exception ex) {
                   JOptionPane.showMessageDialog(null, " Hit start to play");

               }
           });
        }// Stylistic changes to the JTextFields along with adding actionlisteners to play the game

        JButton guideButton = new JButton("Guide");
        JButton startButton = new JButton("Start");
        JButton[] buttons = {guideButton, startButton};
        for (JButton button : buttons) {
            button.setBackground(Color.white);
            button.setFont(new Font("Times New Roman", Font.BOLD, 40));
        }// changes the background and font for the buttons
        
        guideButton.addActionListener(e -> {
            try {

                JOptionPane.showMessageDialog(null, "Get three in a row to win. Pressing start will begin the game and decide whether player one or player two goes first.\n"
                        + "Hitting enter after a player has made their turn will alternate between player one and player two.\n "
                        + "After the game has been won or ended in a draw hit start to begin another game");

            } catch (HeadlessException err) {
                System.out.println(err);
             }
        }); // outputs a JOptionMessage about how the game works
        startButton.addActionListener(e -> {
            try {
                playerOne = null;
                playerTwo = null;
                // setting the strings to null allows the players to pick different tokens every game
                while (playerOne == null || playerOne.equals("")){
                playerOne = JOptionPane.showInputDialog("Player one choose your token");   
                } // makes it so playerOne must have a token for the game to start
                while (playerOne.equals(playerTwo) || playerTwo == null || playerTwo.equals("")) {
                 playerTwo = JOptionPane.showInputDialog("Player two choose your token, cannot be player one's token");
                }// prevents both players from having the same token, also makes it so playerTwo must have a token for the game to start
                clearBoard();
                // clears the board for a new game
                for (JTextField square : squares) {
                    square.setEditable(true);
                } // sets the textfields to be editable for a new game
                Random rng = new Random();
                int random = rng.nextInt(2);
                if (random == 1) {
                    turn = playerOne;
                    squares[9].setText(playerOne);
                } else {
                    turn = playerTwo;
                    squares[9].setText(playerTwo);
                }// if the random is 1 then turn tracker and turn are set to player one, otherwise turn tracker and turn are set to player two
                JOptionPane.showMessageDialog(null, turn + " starts");
            } catch (HeadlessException err) {
                System.out.println(err);
            }
        }); // clears the board and initializes turn, if turn has already been initialized then it will randomly assign player one or player two to go first
        add(startButton, 9);
        add(guideButton);

    }
}
