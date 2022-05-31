import javax.swing.*;

public class TicTacToe extends JFrame {
    public TicTacToe() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("TicTacToe");
        setResizable(false);
        setSize(450, 450);
        setLocationRelativeTo(null);

        Board board = new Board();
        add(board);

        setVisible(true);

    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}