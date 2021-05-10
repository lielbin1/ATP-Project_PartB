package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Collections;

public class Solution implements Serializable {

    private ArrayList<AState> solutionPath ;

    public Solution(ArrayList<AState> path){this.solutionPath = path;}
    public ArrayList<AState> getSolutionPath() {return this.solutionPath;}


}
