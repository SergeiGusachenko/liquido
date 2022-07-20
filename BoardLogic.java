package com.company;

import java.util.Arrays;

public class BoardLogic {
    private char[][] board;
    private int lastDropColumn = -1;
    private int lastDropRow = -1;
    private int width, height;
    private ConsoleView view;
    public BoardLogic(ConsoleView consoleView, int width, int height){
        this.width = width;
        this.height = height;
        board = new char[width][height];
        view = consoleView;
        for (int i = 0; i < height; i++)
            Arrays.fill(board[i] = new char[width], '.');
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

    public int readAndCheckIfCanFitOnTheBoard(char player,int columnNumber){
        boolean res = false;
        while(!res){

            res = columnNumber >= 0 && columnNumber < width;
            if(res == true && board[0][columnNumber] == '.')
                return columnNumber;
            else{
                res = true;
                view.printMessage("\nPlayer " + player + " you cannot put stone in column number: " + columnNumber);
                view.printMessage("Please choose another one:");
            }
        }
        return -1;
    }

    public void printBoard(){
        view.printBoard(this.board);
    }

    public boolean isWinner() {
        char player = board[lastDropRow][lastDropColumn];
        return  chechHorizontal(player) ||
                checkVertical(player) ||
                checkDiagonal(player) ||
                checkBackDiagonal(player);
    }

}
