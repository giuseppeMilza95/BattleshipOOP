package com.example;

import java.util.Random;

public class ShipDeployment {
    /**In this Class I have created two method, the first one to place the ship in the grid
     * The second Method to place randomly 1 battleship and 2 Destroyers
     * **/



    /** shipDeployment takes 4 parameters row number, column number, the grid where it has to be deployed and a boolean
     * value which indicates if the ship has to be place vertically or horizontally and the ship type.
     * In the first part we check if the ship is a battleship or destroyers ship, according their type,
     * I assign a value of the place that they have to cover(4/5 squares).
     * between line 26 and 80 I am checking firstly if the battleship has to be placed vertically or horizontally.
     * after that, I am checking if coordinate row and column are empty, and if so, I check if the next places are null, according to the dimension of the ship.
     * Everything is between try and catch, if everything goes well, the method returns a true else false, this will help me for the method randomShipPosition
     * **/
    public  boolean shipDeployment(int row, int column, Grid grid, boolean isVertical, String shipType) {
            int places = 0;
            if (shipType.toUpperCase().equals("D")){
                    places=4;
            }
            else{
                places= 5;
            }
            if (!isVertical) {
                try {
                    if (places == 5) {

                        if ((grid.getItem(row, column) == null) && (grid.getItem(row, column + 1) == null) && (grid.getItem(row, column + 2) == null) && (grid.getItem(row, column + 3) == null) && (grid.getItem(row, column + 4) == null)) {
                            for (int i = 0; i < places; i++) {
                                grid.setItem(row, column + i, " B  ");

                            }
                            return true;
                        }
                    }
                    if (places == 4) {
                        if ((grid.getItem(row, column) == null) && (grid.getItem(row, column + 1) == null) && (grid.getItem(row, column + 2) == null) && (grid.getItem(row, column + 3) == null)) {
                            for (int i = 0; i < places; i++) {
                                grid.setItem(row, column + i, " D  ");

                            }
                            return true;
                        }
                    }
                }catch (Exception e){
                    return false;
                }
                }

            if (isVertical) {

                try{
                if (places == 5){

                    if ((grid.getItem(column, row) == null) && (grid.getItem(column + 1, row) == null) && (grid.getItem(column + 2, row) == null) && (grid.getItem(column + 3, row) == null) && (grid.getItem(column + 4, row) == null)) {
                        for (int i = 0; i < places; i++) {
                            grid.setItem(column + i, row," B  ");

                        }
                        return true;
                    }
                }


                if (places == 4){

                    if ((grid.getItem(column, row) == null) && (grid.getItem(column + 1, row) == null) && (grid.getItem(column + 2, row) == null) && (grid.getItem(column + 3, row) == null)) {
                        for (int i = 0; i < places; i++) {
                            grid.setItem(column + i, row," D  ");

                        }
                        return true;
                    }
                }
                }catch (Exception e){
                    return false;
                }


            }

        return false;
    }

    /**This method, tkaes as parameter the grid where th ship has to be places,
     * After, I am placing 1 battleship and 2 destroyers.
     * If the shipDeployment return true, the count Battleship is decreased.
     * This method does not return nothing.
     * **/

    public  void randomShipPosition(Grid grid){
        Random ran = new Random();
        int countBattleShip = 3;
        while (countBattleShip > 0) {
            if (countBattleShip >= 2) {
                if (shipDeployment(ran.nextInt(10), ran.nextInt(10), grid, ran.nextBoolean(), "D")) {
                    countBattleShip--;
                }

            }
            if (countBattleShip < 2) {
                if (shipDeployment(ran.nextInt(10), ran.nextInt(10), grid, ran.nextBoolean(), "B")) {
                    countBattleShip--;
                }
            }
        }

    }

}

