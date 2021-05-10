package algorithms.search;

import java.io.Serializable;
import java.sql.PseudoColumnUsage;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState implements Serializable {


    public MazeState(int cost ,Object state) {super(cost,state);}

    public int hashCode() {
        return ((Position)getState()).toString().hashCode();
    }

    public void set_my_parent(AState state) {
        super.set_my_parent(state);
    }

}