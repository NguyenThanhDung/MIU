package lesson10.labs.prob3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static boolean contain1(List<String> list, String s) {
        for (String x : list) {
            if (x == null && s == null) return true;
            if (x == null || s == null) continue;
            if (x.equals(s)) return true;
        }
        return  false;
    }

    public static <T extends Comparable<T>> boolean contain2(List<T> list, T o) {
        for (T i : list) {
            if (i == null && o == null) return true;
            if (i == null || o == null) continue;
            if (i.compareTo(o) == 0) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Bob", "Joe", "Tom");
        boolean result = Main.contain1(list, "Tom");
        System.out.println(result);
        result = Main.contain1(list, "Mary");
        System.out.println(result);

        result = Main.contain2(list, "Tom");
        System.out.println(result);
        result = Main.contain2(list, "Mary");
        System.out.println(result);

        List<Integer> iList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        result = Main.contain2(iList, 3);
        System.out.println(result);
        result = Main.contain2(iList, 8);
        System.out.println(result);

        List<Employee> eList = new ArrayList<>();
        eList.add(new Employee("Chester", "Baird", 41));
        eList.add(new Employee("Edwin", "Jefferson", 23));
        eList.add(new Employee("Lori", "Beach", 42));
        eList.add(new Employee("Diane", "Bryan", 27));
        eList.add(new Employee("Jodie", "Huynh", 30));

        result = Main.contain2(eList, new Employee("Edwin", "Jefferson", 23));
        System.out.println(result);
        result = Main.contain2(eList, new Employee("Josh", "McMillan", 44));
        System.out.println(result);
    }
}
