
package aioffline1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class BreadthFirstSearch {
    public int expandedcount=0,exploreCount=0;
    Queue<State> queue = new LinkedList<>();  //open list	
    Set<State> explored = new HashSet<>();   //close list

    public State exec(State initialState) {
        if (initialState.isGoal()) {
            return initialState;
        }

        queue.add(initialState);
       // exploreCount++;
        while(!queue.isEmpty()) {
            State state = queue.poll(); 
            explored.add(state);    
           
            state.childrenNodesCreate();
            for (State child : state.children) {
               /// if (!((explored.contains(child)==true) || (queue.contains(child)==true))) {
               if((explored.contains(child)==false) && (queue.contains(child)==false)){   
               if (child.isGoal()) {
                   expandedcount=explored.size();
                   exploreCount=expandedcount+queue.size();
                        return child;
                    }
                    queue.add(child);
                    //exploreCount++;
                   
                }
            }
        }
        return null;
    }
}
