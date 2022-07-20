package com.company;
import java.util.Scanner;

public class ConnectFour {
    private int width, height;
    private int moves;
    private Scanner input = null;
    private ConsoleView console;
    private BoardLogic boardLogic;

    public ConnectFour() {
        this.width = -1;
        this.height = -1;
        this.input = new Scanner(System.in);
        console = new ConsoleView();
        initialization();
    }

    public void initialization(){
        console.printHelp();
        while(width == -1) {
            console.printMessage("\n Please provide width of the board greater than 3 and less than " + Integer.MAX_VALUE);
            width = readAndCheckBoardDimension();
        }

        while(height == -1) {
            console.printMessage("\n Please provide height of the board greater than 3 and less than " + Integer.MAX_VALUE);
            height = readAndCheckBoardDimension();
        }
        moves = height * width;
        boardLogic = new BoardLogic(console, width,height);
    }

    public void game(){
        console.printMessage("Enter 0-" + (width - 1) + " to choose a column to drop a stone");
        boardLogic.printBoard();
        char player = 'R';
        while(moves > 0) {
            player = (player == 'R') ? 'Y' : 'R';
            int columnNumber = canFitOnTheBoard(player);
            boardLogic.dropTheStone(player, columnNumber);
            boardLogic.printBoard();
            if (boardLogic.isWinner()) {
                console.printMessage("\nAND THE WINNER IS " + player + " !!!!!!!");
                return;
            }
            moves--;
        }
        console.printMessage("GAME OVER");
    }

    public int canFitOnTheBoard(char player){
        int columnNumber = -1;
        console.printMessage("\nPlayer " + player + " please choose the column: ");
        while(columnNumber == -1){
            columnNumber = boardLogic.readAndCheckIfCanFitOnTheBoard(player, input.nextInt());
        }
        return columnNumber;
    }

    public int readAndCheckBoardDimension(){
        boolean isCorrect = false;
        int number = -1;
        while(!isCorrect) {
            number = input.nextInt();
            isCorrect = !(number <= 3 || number >= Integer.MAX_VALUE);
            if(!isCorrect)
                console.printMessage("Please provide value greater than 3 ");
        }
        return number;
    }

}