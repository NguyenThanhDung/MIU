package lesson10.labs.prob2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static <T extends Comparable> T secondSmallest(List<T> list) {
        T smallest = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(smallest) < 0)
                smallest = list.get(i);
        }
        T secondSmallest = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(secondSmallest) < 0 && list.get(i).compareTo(smallest) > 0)
                secondSmallest = list.get(i);
        }
        return secondSmallest;
    }

    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        intList.addAll(Arrays.asList(35, 74, 91, 36, 49, 6, 10, 15));
        System.out.println(secondSmallest(intList));

        List<String> strList = new ArrayList<>();
        strList.addAll(Arrays.asList("Modesta", "Scot", "Desiderata", "Anka", "Godtfred"));
        System.out.println(secondSmallest(strList));
    }
}
