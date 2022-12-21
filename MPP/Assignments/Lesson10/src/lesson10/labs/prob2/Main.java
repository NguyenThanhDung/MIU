package lesson10.labs.prob2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static <T extends Comparable> T secondSmallestImperative(List<T> list) {
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

    public static <T extends Comparable> T secondSmallestDeclarative(List<T> list) {
        Optional<T> optional = list.stream()
                .sorted()
                .distinct()
                .skip(1)
                .findFirst();
        return optional.orElse(null);
    }

    public static void main(String[] args) {
        List<Integer> intList1 = Arrays.asList(35, 74, 91, 36, 49, 6, 10, 15);
        System.out.println(secondSmallestImperative(intList1));

        List<Integer> intList2 = Arrays.asList(1, 1);
        System.out.println(secondSmallestImperative(intList2));

        List<Integer> intList3 = Arrays.asList(1);
        System.out.println(secondSmallestImperative(intList3));

        List<String> strList = Arrays.asList("Modesta", "Scot", "Desiderata", "Anka", "Godtfred");
        System.out.println(secondSmallestImperative(strList));

        List<Integer> intList4 = Arrays.asList(35, 74, 91, 36, 49, 6, 10, 15);
        System.out.println(secondSmallestDeclarative(intList4));

        List<Integer> intList5 = Arrays.asList(1, 1);
        System.out.println(secondSmallestDeclarative(intList5));

        List<Integer>intList6 = Arrays.asList(1);
        System.out.println(secondSmallestDeclarative(intList6));

        List<String> strList2 = Arrays.asList("Modesta", "Scot", "Desiderata", "Anka", "Godtfred");
        System.out.println(secondSmallestDeclarative(strList2));
    }
}
