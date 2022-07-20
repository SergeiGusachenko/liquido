package com.company;

public class ConsoleView {

    public ConsoleView(){

    }

    public void printHelp(){
        System.out.println("------------------------ConnectFour-----------------------");
        System.out.println("The pieces fall straight down, occupying the lowest available space within the column.\n The objective of the game is to be the first to form a horizontal, vertical,\n or diagonal line of four of one's own tokens. ");
        System.out.println("Rules:");
        System.out.println("1.Choose the size of the board");
        System.out.println("2.Choose the column number you would like to drop a stone");
        System.out.println("----------------------------------------------------------------------");

    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printBoard(char[][] board){
        for(int i = 0; i < board.length; i++){
            System.out.println(board[i]);
        }
    }
}
