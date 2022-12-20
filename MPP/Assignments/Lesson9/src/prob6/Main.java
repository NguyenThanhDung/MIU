package Prob6;

import java.util.*;

public class Main {
    public static Set<String> union(List<Set<String>> sets) {
        return sets.stream().reduce(new HashSet<>(), (a, b) -> {
            a.addAll(b);
            return a;
        });
    }

    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        set1.addAll(Arrays.asList(new String[]{"A", "B"}));
        Set<String> set2 = new HashSet<>();
        set2.add("D");
        Set<String> set3 = new HashSet<>();
        set3.addAll(Arrays.asList(new String[]{"1", "3", "5"}));

        List<Set<String>> sets = new ArrayList<>();
        sets.addAll(Arrays.asList(set1, set2, set3));
        System.out.println(sets);

        Set<String> result = union(sets);
        System.out.println(result);
    }
}
