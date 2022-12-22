package prob2;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> myIntList = Arrays.asList(83, 51, 84, 35, 66);
        IntSummaryStatistics statistics = myIntList.stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("Max: " + statistics.getMax());
        System.out.println("Min: " + statistics.getMin());
    }
}