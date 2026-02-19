import javacutils.Pair;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args){
        Set<Pair<Integer, Integer>> setOfPairs = new HashSet<Pair<Integer, Integer>>();
        for(int i=0; i<5; i++){
            setOfPairs.add(Pair.of(1,2));
        }

        System.out.println(setOfPairs);
    }


}
