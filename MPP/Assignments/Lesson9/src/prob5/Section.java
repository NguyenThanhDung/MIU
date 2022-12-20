package Prob5;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Section {
    public static Stream<String> streamSection(Stream<String> stream, int m, int n) {
        // Implement the code
        return stream.skip(m).limit(n - m + 1);
    }

    public static void main(String[] args) {
        // Make three calls for the streamSection() method with different inputs
        // use nextStream() method to supply the Stream input as a first argument in streamSection() method
        System.out.println(streamSection(nextStream(), 2, 4).collect(Collectors.toList()));
        System.out.println(streamSection(nextStream(), 0, 4).collect(Collectors.toList()));
        System.out.println(streamSection(nextStream(), 3, 8).collect(Collectors.toList()));
        System.out.println(streamSection(nextStream(), 0, 8).collect(Collectors.toList()));
        System.out.println(streamSection(nextStream(), 3, 10).collect(Collectors.toList()));
        System.out.println(streamSection(nextStream(), 10, 12).collect(Collectors.toList()));
    }

    //support method for the main method -- for testing
    private static Stream<String> nextStream() {
        return Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh", "iii").stream();
    }
}