package algorithms.maze3D;

import algorithms.search.AState;

import java.io.Serializable;

public class Maze3DState extends AState implements Serializable {


    public Maze3DState(int cost ,Object state) {super(cost,state);}

    public int hashCode(){return ((Position3D)this.getState()).toString().hashCode();}

    public void set_my_parent(AState state) {
        super.set_my_parent(state);
    }

}