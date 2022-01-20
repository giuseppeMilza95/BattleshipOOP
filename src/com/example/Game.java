package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game extends ShipDeployment {

    /**I have created a Map to help me out to the translation of the input from the player
     * Each letter corresponds to one integer need for the coordinate of the grid.
     * **/
    Map<String, Integer> translation;
    private Scanner scanner = new Scanner(System.in);
    private Grid playerGrid;
    private Grid computerGrid;
    private Grid hitGrid;
    private Random ran;

    public Game() {
        translation = new HashMap<>();
        translation.put("A", 0);
        translation.put("B", 1);
        translation.put("C", 2);
        translation.put("D", 3);
        translation.put("E", 4);
        translation.put("F", 5);
        translation.put("G", 6);
        translation.put("H", 7);
        translation.put("I", 8);
        translation.put("L", 9);
        this.playerGrid = new Grid();
        this.computerGrid = new Grid();
        this.hitGrid = new Grid();
        this.ran = new Random();


    }


    /**This method helps to understand if the places hit are empty or if they were hit.
     * if the grid coordinate contain a part of the ship, it will return 1 as success number;
     * if the grid coordinate is empty, it will return 2 as water.
     * if they coordinate were already hit, it will return 2.
     * This method will help me out with the computerRound and playerRound.
     * **/
    public int hit(int x, int y, Grid grid) {
        if (grid.getItem(x, y) ==" D  "  || grid.getItem(x, y) == " B  " && grid.getItem(x, y) != " H  " && grid.getItem(x, y) != " W  ") {
            grid.setItem(x, y, " H  ");
            return 1;
        } else if (x < 10 && y < 10 && grid.getItem(x, y) == null) {
            grid.setItem(x, y, " W  ");
            return 0;

        } else if (grid.getItem(x, y) == " W  ") {

            return 2;
        } else if (grid.getItem(x, y) == " H  ") {

            return 2;
        } else {
            System.out.println("Out of the grid");

        }
        return 2;
    }

/**Simple method which ask the user out the row Coordinate,
 * if the coordinates are wrong it will throw an error handled with try and catch
 * **/
    public int rowCoordinate() {
        int x = 0;
        while (true) {
            try {

                System.out.println("Enter coordinate row: ");

                x = scanner.nextInt();
                if (x <= 9 && x >=0  ) {
                    break;
                } else {
                    throw new Exception();
                }


            } catch (Exception e) {
                System.out.println("Please enter a valid number");
                scanner.nextLine();
            }
        }
        return x;

    }

    /**Simple method which ask the user out the column Coordinate,
     * if the coordinates are wrong it will throw an error handled with try and catch
     * **/
    public int columnCoordinate() {
        int column = 0;
        String y = "";
        while (true) {
            try {
                System.out.println("Enter coordinate column: ");

                y = scanner.next();

                column = translation.get(y.toUpperCase());
                break;
            } catch (Exception e) {
                System.out.println("Invalid letter");
                scanner.nextLine();
            }
        }

        return column;
    }
/**In this method we use the method hit to help
 * we store the output of the method hit into a variable of type int
 * According to the value of hitResult, it will set the grid coordinate with a particular symbol
 * and set the turn to the player/computer according to the result.
 * In the same method, the grid is checked to verify if the user won.
 * **/

    public String playerRound(int x, int column, Grid computerGrid, Grid hits) {
        String turn = "player";
        int hitResult = hit(x, column, computerGrid);
        if (hitResult == 1) {
            hits.setItem(x, column, " H  ");
            System.out.println("Well done! You hit the ship");
            turn = "computer";
        } if (hitResult == 0) {
            hits.setItem(x, column, " W  ");
            System.out.println("You hit the Water");
            turn = "computer";
        }if(hitResult == 2){
            System.out.println("Place already hit, check the Hit board");
            turn = "player";
        }


        if (!Grid.checkWin(this.computerGrid.grid)) {
            System.out.println("Player One Won !!");
            printBoard();
            turn = "quit";
        }
        return turn;
    }
    /**Really similar to the playerRound, here the coordinates are random,
     * **/

    public String computerRound(Grid playerG) {

            String turn ="computer";
            System.out.println("Computer round:");



            while (turn == "computer"){
                int ranRow = ran.nextInt(10);
                int ranColumn = ran.nextInt(10);
                int valueHit = hit(ranRow, ranColumn, playerGrid);
                if (valueHit == 1){
                    System.out.println("The computer hit your battleship!");
                    return "player";

                }
                if (valueHit == 0){
                    System.out.println("The computer hit the water!");
                    return "player";


                } if (valueHit == 2){

                    turn = "computer";

                }


            }





        return turn;
    }
    /**The first part of the method i print some text and randomly place the ships.
     * After the user has input a choice, one to exit the game and two to hit.
     *If the player decide to hit, the above method are called to build the logic of the game.
     * **/

    public void gameOn(){
        System.out.println("Welcome to Giuseppe battleship!!");
        System.out.println("Below some help: ");
        System.out.println("H - hit");
        System.out.println("W - water");
        System.out.println("D - Destroyers");
        System.out.println("B - Battleship");
        System.out.println("null - empty coordinates");
        String turn = "player";
        randomShipPosition(computerGrid);
        randomShipPosition(playerGrid);
        while(turn != "quit"){
            int choice = 0;
            System.out.println("Choose: (1 - Quit) - (2 - hit)");
            while(true) {
                try {
                    System.out.println("Enter your choice");
                    choice = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Please enter a valid integer.");
                    scanner.nextLine();
                }
            }


            switch (choice) {
                case 1:
                    turn = "quit";
                    break;
                case 2:
                    if (turn == "player") {
                        printBoard();
                        int column = columnCoordinate();
                        int row = rowCoordinate();

                        turn = playerRound(row,column, computerGrid, hitGrid);
                        if (turn == "computer") {
                            int count = 0;
                            turn = computerRound(playerGrid);
                            if (!Grid.checkWin(this.playerGrid.grid)) {
                                System.out.println("The computer Won");
                                System.out.println("Hit board");
                                printBoard();
                                turn = "quit";
                            }
                        }

                    }



        }





    }
}

    public void printBoard(){
        System.out.println("-----------------------Hit board---------------------");
        hitGrid.printGrid();
        System.out.println("----------------------Your board---------------------");
        playerGrid.printGrid();


    }


}




















