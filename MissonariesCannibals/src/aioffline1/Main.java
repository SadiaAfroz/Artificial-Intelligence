package aioffline1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MyClass {

    public static int boatsize;
}

public class Main {

    public static void main(String[] args) {
        /// Left to Right
        /// initial_state=(3,3,0,0,1) final_state=(0,0,3,3,0)
        System.out.println("============= Missionaries and Cannibals =====================");
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Cannibals number : ");
        int cannibals = sc.nextInt();

        System.out.println("Enter Missonaries number : ");
        int missionaries = sc.nextInt();

        System.out.println("Enter Boat Capacity : ");
        MyClass.boatsize = sc.nextInt();
        // int option = sc.nextInt();

        //System.out.println(MyClass.boatsize);
        State initialState = new State(cannibals, missionaries, 0, 0, 1);
        System.out.println("\t\t 1. Breadth-first search");
        System.out.println("\t --------------------------------------");
        BreadthFirstSearch bfssearch = new BreadthFirstSearch();
        long start = System.currentTimeMillis();
        State solution = bfssearch.exec(initialState);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Time taken in milisecond : "+timeElapsed);
        System.out.println("Nodes expanded : " + bfssearch.expandedcount);
        System.out.println("Nodes explored : " + bfssearch.expandedcount);

        printSolution(solution);
        System.out.println("\n");
        System.out.println("\t\t 2. DepthFirst search");
        System.out.println("\t --------------------------------------");
        DepthFirstSearch dfssearch = new DepthFirstSearch();
        long start2 = System.currentTimeMillis();
        State solutiondfs = dfssearch.exec(initialState);
        long finish2 = System.currentTimeMillis();
        long timeElapsed2 = finish2 - start2;
        System.out.println("Time taken in milisecond : "+timeElapsed);
        System.out.println("Nodes expanded : " + dfssearch.expandedcount);
        System.out.println("Nodes explored : " + dfssearch.expandedcount);
        printSolution(solutiondfs);
       
    }

    private static void printSolution(State solution) {
        if (null == solution) {
            System.out.print("\nNo solution found.");
        } else {
            System.out.println("\nSolution (cannibalLeft,missionaryLeft,boat,cannibalRight,missionaryRight): ");
            List<State> sequence = new ArrayList<State>();
            State state = solution;
            while (state != null) {
                sequence.add(state);
                state = state.getParent();
            }
            int depth = sequence.size() - 1;
            for (int i = sequence.size() - 1; i >= 0; i--) {
                state = sequence.get(i);
                String boat = null;
                if (state.getB() == 1) {
                    boat = ",Left,";
                } else {
                    boat = ",Right,";
                }
                if (state.isGoal()) {
                    System.out.print("(" + state.getCl() + "," + state.getMl() + boat + state.getCr() + "," + state.getMr() + ")");
                } else {
                    System.out.print("(" + state.getCl() + "," + state.getMl() + boat + state.getCr() + "," + state.getMr() + ")" + " -> ");
                }
            }
            System.out.println("\nDepth: " + depth);
        }
    }
}
