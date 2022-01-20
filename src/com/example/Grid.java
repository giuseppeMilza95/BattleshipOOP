package com.example;

import java.util.Arrays;
import java.util.List;

public class Grid {
    /**I have declared two int final variable because the dimention of the greed does not change

     * **/
    private final static int ROW_NUMBER = 10;
    private final static int COLUMN_NUMBER = 10;
    String[][] grid;

    public Grid() {
         grid = new String[ROW_NUMBER][COLUMN_NUMBER];
    }



/**Simple method to help to print out the grid**/

    public void printGrid() {
        int a = 0;

        System.out.println("    A    B    C    D    E    F    G    H    I    L");
        for (int i = 0; i < ROW_NUMBER; ++i) {

            System.out.print(a + ") ");
            a++;
            for (int j = 0; j < COLUMN_NUMBER; ++j) {

                System.out.print(grid[i][j] + "|");
            }
            System.out.println("");
        }
    }

    /**seyItem to get the items of my grid**/
    public void setItem(int x, int y, String item){
        grid[x][y] = item;
    }
    /**getItem to get the items of my grid**/
    public String getItem(int x, int y){
        return grid[x][y];
    }

    /**This method check if in the grid there are still battleship(B) or Destroyers(D)
     * if there are still ships return true, else false.
     * **/
    public static boolean checkWin(String[][] grid){
        List<String[]> list = Arrays.asList(grid);
        for(String[] arr: list){
            boolean checkD =  Arrays.asList(arr).contains(" D  ");
            boolean checkB =  Arrays.asList(arr).contains(" B  ");
            if (checkD || checkB){
                return true;
            }

        }

        return false;
    }




}
