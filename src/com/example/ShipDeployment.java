package com.example;

import java.util.Random;

public class ShipDeployment {


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

