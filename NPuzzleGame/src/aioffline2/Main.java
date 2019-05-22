/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aioffline2;

import static aioffline2.Node1.SIZE;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Faiza
 */
class MyClass {

    public static int boardsize;
}

class Huristic {

    public static int option;
}

public class Main {

    public static void main(String[] args) {

        System.out.println("============= N Puzzle =====================");
        Scanner sc = new Scanner(System.in);
        int flag = 0;
        System.out.println("Enter N X N Grid number : ");
        // int npuzzle = sc.nextInt();
        MyClass.boardsize = sc.nextInt();

  
        System.out.println("Enter N X N Grid matrix : ");
        int[] tiles = new int[MyClass.boardsize];
        for (int i = 0; i < MyClass.boardsize; i++) {
            tiles[i] = sc.nextInt();
        }
        if (MyClass.boardsize % 2 != 0) {
            if (isSolvableOdd(tiles) == false) {
                System.out.println(" Not Solvable");
            } else {
                flag = 1;
            }
        }
       // else flag=1;
        else{
           // System.out.println(isSolvableEven(tiles));
            if (isSolvableEven(tiles) == true) {
                System.out.println(" Not Solvable");
            } else {
                flag = 1;
            }
        
        }
         if (flag == 1) {
        Node1 start = new Node1(tiles);
        BreadthFirstSearch bfssearch = new BreadthFirstSearch();

        //hamming 
//                Huristic.option=1;
//                Node1 solution1 = bfssearch.exec(start);
//                printSolution(solution1);

        //Manhatan
//        Huristic.option = 2;
//        Node1 solution2 = bfssearch.exec(start);
//        printSolution(solution2);

        //Linear 
        Huristic.option = 3;
        Node1 solution3 = bfssearch.exec(start);
        printSolution(solution3);

         }
    }

    private static void printSolution(Node1 solution) {
        if (solution == null) {
            System.out.print("\nNo solution found.");
        } else {
            System.out.println("\nSolution: ");
            List<Node1> sequence = new ArrayList<>();
            Node1 state = solution;
            while (state != null) {
                sequence.add(state);
                state = state.getParent();
            }
            int depth = sequence.size() - 1;
            for (int i = sequence.size() - 1; i >= 0; i--) {
                state = sequence.get(i);
                String s = "";
                for (int k = 0; k < SIZE; k++) {
                    if (k % (int) Math.sqrt(SIZE * 1.0) == 0) {
                        s += "\n";
                    }
                    s += "| ";
                    s += Integer.toString(state.state[k]);
                    if ((state.state[k] / 10) > 0) {
                        s += " |";
                    } else {
                        s += "  |";
                    }

                }
                System.out.print(s);
                System.out.println();

            }
            System.out.println("\nDepth: " + depth);
        }
    }

    public static boolean isSolvableOdd(int[] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i] == 0) {
                    continue;
                }
                if (matrix[i] != 0 && matrix[j] != 0 && matrix[i] > matrix[j]) {
                    //  System.out.println(matrix[i]+" "+matrix[j]);
                    count++;
                }
            }
        }
        //System.out.println(count);

        return count % 2 == 0;

    }
    public static boolean isSolvableEven(int[] matrix) {
        int count = 0, rowBlank = 0;
        for (int i = 0; i < matrix.length ; i++) {
             if (matrix[i] == 0) {
                  rowBlank=i/(int) Math.sqrt(SIZE * 1.0);
                    continue;
                    
                }
            for (int j = i + 1; j < matrix.length; j++) {
               
                if (matrix[i] != 0 && matrix[j] != 0 && matrix[i] > matrix[j]) {
                    //  System.out.println(matrix[i]+" "+matrix[j]);
                    count++;
                }
            }
        }
       // System.out.println(" ccc "+count+"rr"+rowBlank);
        return (count+rowBlank)%2==0;
    }

}
