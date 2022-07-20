package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class ConnectFour {
    private char[][] board;
    private int width, height;
    private int lastDropColumn = -1;
    private int lastDropRow = -1;

    public ConnectFour(int width, int height) {
        this.width = width;
        this.height = height;
        board = new char[width][height];
        for (int i = 0; i < height; i++)
            Arrays.fill(board[i] = new char[width], '.');

    }

    public void printBoard(){
        for(int i = 0; i < height; i++){
            System.out.println(board[i]);
        }
    }

    public boolean chechHorizontal(char player){
        for(int row = 0; row<board.length; row++){
            for (int col = 0;col < board[0].length - 3;col++){
                if (board[row][col] == player   &&
                        board[row][col+1] == player &&
                        board[row][col+2] == player &&
                        board[row][col+3] == player){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkVertical(char player){
        for(int row = 0; row < board.length - 3; row++){
            for(int col = 0; col < board[0].length; col++){
                if (board[row][col] == player   &&
                        board[row+1][col] == player &&
                        board[row+2][col] == player &&
                        board[row+3][col] == player){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDiagonal(char player){
        for(int row = 3; row < board.length; row++){
            for(int col = 0; col < board[0].length - 3; col++){
                if (board[row][col] == player   &&
                        board[row-1][col+1] == player &&
                        board[row-2][col+2] == player &&
                        board[row-3][col+3] == player){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkBackDiagonal(char player){
        for(int row = 0; row < board.length - 3; row++){
            for(int col = 0; col < board[0].length - 3; col++){
                if (board[row][col] == player   &&
                        board[row+1][col+1] == player &&
                        board[row+2][col+2] == player &&
                        board[row+3][col+3] == player){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isWinner() {
        char player = board[lastDropRow][lastDropColumn];
        return chechHorizontal(player) ||
                checkVertical(player) ||
                checkDiagonal(player) ||
                checkBackDiagonal(player);
    }

    public void dropTheStone(char player, int colomnNumber) {
        for (int row = height - 1; row >= 0; row--) {
            if (board[row][colomnNumber] == '.') {
                board[row][colomnNumber] = player;
                lastDropColumn = colomnNumber;
                lastDropRow = row;
                return;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

            int height = 6; int width = 7; // use hardcoded h and w for simplicity
            int moves = height * width;
            ConnectFour board = new ConnectFour(width, height);
            System.out.println("Enter 0-" + (width - 1) + " to choose a column to drop a stone");
            board.printBoard();
            char player = 'R';
            while(moves > 0) {
                player = (player == 'R') ? 'Y' : 'R';
                System.out.println("\nPlayer " + player + " please choose the column: ");// TO-DO check for outside of the board values
                int colomnNumber = input.nextInt();
                board.dropTheStone(player, colomnNumber);
                board.printBoard();
                if (board.isWinner()) {
                    System.out.println("\nAND THE WINNER IS " + player + " !!!!!!!");
                    return;
                }
                moves--;
            }
            System.out.println("GAME OVER");
         // TO-DO catch block should be here
    }
}