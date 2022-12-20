import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String[] names = {"Alexis", "Tim", "Kyleen", "KRISTY"};
        Arrays.sort(names, String::compareToIgnoreCase);
        Stream.of(names).forEach(System.out::println);
    }
}