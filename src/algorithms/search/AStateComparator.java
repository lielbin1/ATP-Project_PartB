package algorithms.search;

import java.util.Comparator;

public class AStateComparator implements Comparator<AState> {
    AStateComparator(){}
    public int compare(AState s1, AState s2) {
        return s1.getCost() - s2.getCost();
    }
}




