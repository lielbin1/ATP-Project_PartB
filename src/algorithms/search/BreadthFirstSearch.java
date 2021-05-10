package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    public BreadthFirstSearch() {}
    public String getName() {
        return "BreadthFirstSearch";
    }
    @Override
    public Solution solve(ISearchable domain) {
        Queue<AState> queue = new LinkedList();
        Map<Integer,AState> visited = new HashMap();
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        visited.put(start.hashCode(),start);
        queue.add(start);

        while (queue.size()!=0){
            AState current = (AState)queue.poll();
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
                    queue.add(adj);
                }

            }

        }
        this.solution = new Solution(new ArrayList());
        return this.solution;
    }

}
