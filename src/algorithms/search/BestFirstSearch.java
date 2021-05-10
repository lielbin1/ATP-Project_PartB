package algorithms.search;

import java.util.*;

public class BestFirstSearch extends ASearchingAlgorithm{
    public  BestFirstSearch(){}
    public String getName() {
        return "BestFirstSearch";
    }

    @Override
    public Solution solve(ISearchable domain) {
        PriorityQueue<AState> open = new PriorityQueue(new AStateComparator());
        Map<Integer,AState> close = new HashMap();
        Map<Integer,AState> adjMap = new HashMap();
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        close.put(start.hashCode(),start);
        open.add(start);
        while (open.size()!=0){
            AState current = (AState)open.poll();
            close.put(current.hashCode(),current);
            ArrayList<AState> AllSuccessorsStates = domain.getAllPossibleStates(current);
            if (current.equals(goal))
            {
                this.solution = new Solution(this.getPath(start, current));
                return this.solution;
            }

            Iterator var5 = AllSuccessorsStates.iterator();

            while (var5.hasNext()){
                AState adj = (AState)var5.next();
                if(!close.containsKey(adj.hashCode())){
                    if(!adjMap.containsKey(adj.hashCode())){
                        adj.setCost(adj.getCost()+current.getCost());
                        adj.set_my_parent(current);
                        open.add(adj);
                        adjMap.put(adj.hashCode(),adj);
                    } else if (((AState)adjMap.get(adj.hashCode())).getCost()>adj.getCost() + current.getCost()) {
                        ((AState)adjMap.get(adj.hashCode())).setCost(adj.getCost() + current.getCost());
                        ((AState)adjMap.get(adj.hashCode())).set_my_parent(current);
                    }
                }

            }

        }
        this.solution = new Solution(new ArrayList());
        return this.solution;
    }

    }



