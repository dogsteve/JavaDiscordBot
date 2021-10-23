package ultis;

import entities.Stonks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StonksUltis {


    // sau mot hoi su dung .sort thì bubble sort is da best =))
    public static Stonks[] sortByDate (Stonks[] stk) {
        for (int i = 0; i < stk.length; i++) {
            for (int j = i; j < stk.length; j++) {
                if (stk[i].getDate().compareTo(stk[j].getDate()) > 0) {
                    Stonks temp = stk[i];
                    stk[i] = stk[j];
                    stk[j] = temp;
                }
            }
        }
        return stk;
    }
}
