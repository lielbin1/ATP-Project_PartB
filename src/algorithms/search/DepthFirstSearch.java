package algorithms.search;

import java.util.*;


public class DepthFirstSearch extends ASearchingAlgorithm {

    public DepthFirstSearch() {}
    public String getName() {
        return "DepthFirstSearch";
    }

    @Override
    public Solution solve(ISearchable domain) {
        Stack<AState> stack = new Stack();
        Map<Integer,AState> visited = new HashMap();
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        visited.put(start.hashCode(),start);
        stack.push(start);
        while (stack.size()!=0){
            AState current = (AState)stack.pop();
            ArrayList<AState> AllSuccessorsStates = domain.getAllPossibleStates(current);
            if (current.equals(goal))
            {
                this.solution = new Solution(this.getPath((AState) visited.get(start.hashCode()), current));
                return this.solution;
            }
            Iterator var5 = AllSuccessorsStates.iterator();

            while (var5.hasNext()){
                AState adj = (AState)var5.next();
                if(!visited.containsKey(adj.hashCode())){
                    adj.set_my_parent(current);
                    visited.put(adj.hashCode(),adj);
                    stack.push(adj);
                }

            }

        }
        this.solution = new Solution(new ArrayList());
        return this.solution;
    }
    }
