package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AState implements Serializable {

    protected int cost;
    private AState m_my_parent = null;
    private Object state;

    public AState(int cost, Object state) {
        this.cost = cost;
        this.state = state;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public AState get_my_parent() {
        return m_my_parent;
    }

    public void set_my_parent(AState parent) {
        this.m_my_parent = parent;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public String toString() {
        return this.state.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            AState aState = (AState)o;
            return this.state.equals(aState.state);
        } else {
            return false;
        }
    }
}
