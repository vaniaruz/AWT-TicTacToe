// Tic Tac Toe is fun!
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Frame implements ActionListener {
    private Button[] buttons = new Button[9];
    private char currentPlayer = 'X';
    private Label statusLabel;

    public TicTacToe() {
        setLayout(new BorderLayout());
        setTitle("Tic Tac Toe");
        setSize(300, 350);

        Panel grid = new Panel();
        grid.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].addActionListener(this);
            grid.add(buttons[i]);
        }

        statusLabel = new Label("Player X's turn");
        Button resetButton = new Button("New Game");
        resetButton.addActionListener(e -> resetGame());

        Panel bottomPanel = new Panel(new GridLayout(2, 1));
        bottomPanel.add(statusLabel);
        bottomPanel.add(resetButton);

        add(grid, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Button clicked = (Button) e.getSource();
        if (!clicked.getLabel().equals("")) return;

        clicked.setLabel(String.valueOf(currentPlayer));

        if (checkWinner()) {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            disableButtons();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a draw!");
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }

    private boolean checkWinner() {
        int[][] winConditions = {
            {0,1,2},{3,4,5},{6,7,8}, // Rows
            {0,3,6},{1,4,7},{2,5,8}, // Columns
            {0,4,8},{2,4,6}          // Diagonals
        };
        for (int[] wc : winConditions) {
            String a = buttons[wc[0]].getLabel();
            String b = buttons[wc[1]].getLabel();
            String c = buttons[wc[2]].getLabel();
            if (!a.equals("") && a.equals(b) && b.equals(c)) return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (Button b : buttons) {
            if (b.getLabel().equals("")) return false;
        }
        return true;
    }

    private void resetGame() {
        for (Button b : buttons) {
            b.setLabel("");
            b.setEnabled(true); // Re-enable the button
        }
        currentPlayer = 'X';
        statusLabel.setText("Player X's turn");
    }
    
    private void disableButtons() {
        for (Button b : buttons) b.setEnabled(false);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
