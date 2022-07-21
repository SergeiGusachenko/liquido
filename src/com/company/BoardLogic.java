package com.company;

import java.util.Arrays;

public class BoardLogic {
    private char[][] board;
    private int lastDropColumn = -1;
    private int lastDropRow = -1;
    private int width, height;
    private ConsoleView view;
    private final int NUM_OF_STONES_TO_WIN = 4;

    public BoardLogic(ConsoleView consoleView, int width, int height){
        this.width = width;
        this.height = height;
        board = new char[width][height];
        view = consoleView;
        for (int i = 0; i < height; i++)
            Arrays.fill(board[i] = new char[width], '.');
    }

    public boolean chechHorizontal(char player){
        int count = 0;
        int max = NUM_OF_STONES_TO_WIN - 1;

        for(int i = 1; i <= max; i++){
            if(lastDropColumn >= max ){
                if(board[lastDropRow][lastDropColumn - i] == player)
                    count++;
            }else if(lastDropColumn < board[0].length - max){
                if(board[lastDropRow][lastDropColumn + i] == player)
                    count++;
            }
        }
        return count == max;
    }

    public boolean checkVertical(char player){
        int count = 0;
        int max = NUM_OF_STONES_TO_WIN - 1;
        for(int i = 1; i <= max; i++){
            if(lastDropRow >= max && board[lastDropRow - i][lastDropColumn] == player)
                count++;
            else if(lastDropRow < board.length - max && board[lastDropRow + i][lastDropColumn] == player)
                count++;
        }
        return count == max;
    }

    public boolean checkDiagonal(char player){

        int count = 0;
        int max = NUM_OF_STONES_TO_WIN - 1; // we just need n - 1 same stones
        for(int  i = 1; i <= max ; i++){
            if(lastDropColumn < board[0].length - max && lastDropRow >= max )
                if(board[lastDropRow - i][lastDropColumn + i] == player)
                    count++;
                else if(lastDropColumn >= max && lastDropRow < board.length - max )
                    if(board[lastDropRow + i][lastDropColumn - i] == player)
                        count++;
        }
        return count == max;
    }
    public boolean checkBackDiagonal(char player){
        int count = 0;
        int max = NUM_OF_STONES_TO_WIN - 1;
        for(int i = 1; i <= max ; i++){
            if(board.length - max > lastDropRow && board[0].length - max > lastDropColumn){
                if(board[lastDropRow + i][lastDropColumn + i] == player)
                    count++;
            }
            if( lastDropRow >= max && lastDropColumn >= max){
                if(board[lastDropRow - i][lastDropColumn - i] == player)
                    count++;
            }
        }
        return count == max;
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
