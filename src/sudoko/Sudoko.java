package sudoko;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Sudoko {

    public static void main(String[] args) {
        int[][][] board = setUpBoard();
        boolean isZero;
        displayBoard(board);
        System.out.println("Input");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (board[i][j][k] == 0) {
                        board = mainCheck(board);
                    }
                 }
            }
        }

        System.out.print("\n" + "----------------------------------------------------" + "\n");
        board = mainCheck(board);
        System.out.println("Output");
        displayBoard(board);
    }

    public static int[][][] setUpBoard() {
        int[][][] board = new int[9][3][3];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {


                for (int k = 0; k < 3; k++) {
                    board[i][j][k] = Integer.parseInt(JOptionPane.showInputDialog("Eneter number in Squre: " + (i + 1) + "  row: " + (j + 1) + "  col: " + (k + 1)));
                }
            }
        }
        return board;
    }

    public static void displayBoard(int[][][] carp) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(carp[i][j][k]);
                }
                System.out.print("   ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        for (int j = 0; j < 3; j++) {
            for (int i = 3; i < 6; i++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(carp[i][j][k]);
                }
                System.out.print("   ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        for (int j = 0; j < 3; j++) {
            for (int i = 6; i < 9; i++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(carp[i][j][k]);
                }
                System.out.print("   ");
            }
            System.out.print("\n");
        }
    }

    public static int[][][] mainCheck(int[][][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (board[i][j][k] == 0) {
                        board[i][j][k] = rowColSquareCheck(board[i][j][k], board, i, j, k);
                    }
                }
            }
        }
        return board;
    }

    public static int rowColSquareCheck(int checking, int[][][] board, int sq, int row, int col) {
        int output;
        ArrayList<Integer> Possible = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            Possible.add(i + 1);
        }
        for (int i = (sq / 3) * 3; i < ((sq / 3) * 3) + 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (board[i][row][k] != 0) {
                    Possible.set(board[i][row][k] - 1, 0);
                }
            }
        }
        for (int i = sq % 3; i < 9; i = i + 3) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j][col] != 0) {
                    Possible.set(board[i][j][col] - 1, 0);
                }
            }
        }
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if (board[sq][j][k] != 0) {
                    Possible.set(board[sq][j][k] - 1, 0);
                }
            }
        }
        Collections.sort(Possible);
        Collections.reverse(Possible);
        for (int i = 8; i >= 0; i--) {
            if (Possible.get(i) == 0) {
                Possible.remove(i);
            }
        }
        if (Possible.size() == 1) {
            output = Possible.get(0);
        } else {
            output = 0;
        }
        return output;
    }
}
