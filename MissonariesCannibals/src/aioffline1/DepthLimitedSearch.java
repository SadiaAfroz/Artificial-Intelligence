/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aioffline1;
import java.util.List;

// based on the depth-limited search algorithm present on the 3o Edition of the
// "Artificial Intelligence A Modern Approach".

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class DepthLimitedSearch {
    
    public State exec(State initialState) {
	
		return recursiveDLS(initialState);
	}
    
    private State recursiveDLS(State state) {
		if (state.isGoal()) {
			return state;
		} else {
			state.childrenNodesCreate();
			for (State child : state.children) {
				State result = recursiveDLS(child);
				if (null != result) {
					return result;
				}
			}
			return null;
		}
	}
    
}
