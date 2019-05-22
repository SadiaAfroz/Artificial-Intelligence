/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aioffline1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Faiza
 */
public class DepthFirstSearch {
    public int count=0;
     public int expandedcount=0,exploreCount=0;
    Stack<State> stack = new Stack<>();
    Queue<State> explored = new LinkedList<>();

    public State exec(State initialState) {
        stack.push(initialState);
        return DFS();
    }

    private State DFS() {
        while (!stack.empty()) {
            State state = stack.pop();
            explored.add(state);
            count++;
            if (state.isGoal()) {
                 expandedcount=explored.size();
                   exploreCount=expandedcount+stack.size();
                return state;
            } else {
                state.childrenNodesCreate();
                // System.out.println("Parent"+state.getCl()+" "+state.getMl()+" "+state.getCr()+" "+state.getMr()+" "+state.getB());
                for (State child : state.children) {
                    if (!((stack.contains(child)==true) || (explored.contains(child)==true))) {
                     //   System.out.print("not contains :");
                     //   System.out.println("(" + child.getCl() + "," + child.getMl() + "," + child.getCr() + "," + child.getMr() + "," + child.getB() + ")");
                        stack.push(child);
                    }
                }

            }

        }

        return null;
    }

}
