/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aioffline2;

import static aioffline2.Node1.SIZE;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Faiza
 */
public class BreadthFirstSearch {

    ArrayList<Node1> neighbours;

    PriorityQueue<Node1> queue;      //open list	
    //Queue<Node1> explored = new LinkedList<>();
    HashSet<Node1> explored=new HashSet<>();  //close list
    //Set<Node1> explored = new HashSet<>();  //close list
    public BreadthFirstSearch() {
        this.queue = new PriorityQueue<>();
    }

    public Node1 exec(Node1 initialState) {
        if (initialState.isGoal()) {
            return initialState;
        }

        queue.add(initialState);

        while (!queue.isEmpty()) {
            Node1 state = queue.poll();
            explored.add(state);
            int o = 0;
            neighbours = state.generateNeighbours();

//            System.out.println("Neighbours:");
//            System.out.println();
            for (Node1 child : neighbours) {
                if (explored.contains(child) == false){
                //if ((explored.contains(child) == false) && (queue.contains(child) == false)) {
                    if (child.isGoal()) {
                        System.out.println("sizee : "+(queue.size()+explored.size()));
                        return child;
                    }
                    int k = 0;
                    child.level = state.level + 1;

                    queue.add(child);
                }
            }
        }
        return null;
    }
}
