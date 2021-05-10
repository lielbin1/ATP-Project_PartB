package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected Solution solution;
    protected ISearchable domain;


    public ASearchingAlgorithm(){}

    public String getName() {
        return this.getClass().getName();
    }

    public abstract Solution solve(ISearchable domain);


public int getNumberOfNodesEvaluated() {
    return this.solution != null ? this.solution.getSolutionPath().size() : 0;
}

    public ArrayList<AState> getPath(AState start , AState goal){
        ArrayList<AState> path = new ArrayList();
        AState current = goal;
        path.add(current);

        while (!current.equals(start)){
            current = current.get_my_parent();
            path.add(current);
        }
        return path;
    }
}

