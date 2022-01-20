package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game extends ShipDeployment {
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

    public int hit(int x, int y, Grid grid) {
        if (grid.getItem(x, y) ==" D  "  || grid.getItem(x, y) == " B  " && grid.getItem(x, y) != " H  " && grid.getItem(x, y) != " W  ") {
            grid.setItem(x, y, " H  ");
            return 1;
        } else if (x < 10 && y < 10 && grid.getItem(x, y) == null) {
            grid.setItem(x, y, " W  ");
            return 0;

        } else if (grid.getItem(x, y) == " W  ") {
            //System.out.println("Place already try again");
            return 2;
        } else if (grid.getItem(x, y) == " H  ") {
            //System.out.println("Place already hit, try again");
            return 2;
        } else {
            System.out.println("Out of the grid");

        }
        return 2;

    }


    public int rowCoordinate() {
        int x = 0;
        while (true) {
            try {

                System.out.println("Enter coordinate row: ");

                x = scanner.nextInt();
                if (x <= 9) {
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


    public String playerRound(int x, int column, Grid computerGrid, Grid hits) {
        String turn = "player";
        int hitResult = hit(x, column, computerGrid);
        if (hitResult == 1) {
            hits.setItem(x, column, " H  ");
            System.out.println("Well done! You hit the boat");
            turn = "computer";
        } else if (hitResult == 0) {
            hits.setItem(x, column, " W  ");
            System.out.println("Water");
            turn = "computer";
        }else {
            System.out.println("Place already hit, check the hit grid");
            turn = "player";
        }


        if (!Grid.checkWin(this.computerGrid.grid)) {
            System.out.println("Player One Won !!");
            turn = "quit";
        }
        return turn;
    }


    public String computerRound(Grid playerG) {
            String turn ="computer";
            System.out.println("I am here");
            int ranRow = ran.nextInt(10);
            int ranColumn = ran.nextInt(10);

            while (hit(ranRow, ranColumn, playerGrid) == 1 || hit(ranRow, ranColumn, playerGrid) == 0) ;


            turn = "player";


            if (!Grid.checkWin(playerGrid.grid)) {
                System.out.println("The computer Won");
                turn = "quit";
            }



        return turn;
    }


    public void gameOn(){
        String game = "start";
        String turn = "player";
        randomShipPosition(computerGrid);
        randomShipPosition(playerGrid);
        while(game != "quit"){
            int choice = 0;
            System.out.println("(1 - Quit) - (2 - hit)");
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
                    game = "quit";
                    break;
                case 2:
                    if (turn == "player") {
                        System.out.println("Hit board");
                        hitGrid.printGrid();
                        System.out.println("Computer board");
                        computerGrid.printGrid();
                        System.out.println("Your board");
                        playerGrid.printGrid();
                        int column = columnCoordinate();
                        int row = rowCoordinate();

                        turn = playerRound(row,column, computerGrid, hitGrid);
                        if (turn == "computer") {
                            turn = computerRound(playerGrid);

                        }

                    }



        }





    }
}
}




















