package com.example;

import java.util.Arrays;
import java.util.List;

public class Grid {
    private final static int ROW_NUMBER = 10;
    private final static int COLUMN_NUMBER = 10;
    String[][] grid;

    public Grid() {
         grid = new String[ROW_NUMBER][COLUMN_NUMBER];
    }





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


    public void setItem(int x, int y, String item){
        grid[x][y] = item;
    }

    public String getItem(int x, int y){
        return grid[x][y];
    }


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
