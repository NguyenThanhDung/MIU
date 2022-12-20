import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Apple", "Banana","Orange","Cherries","blums");
        fruits.forEach(s -> System.out.println(s)); // Print the list using Lambda
        fruits.forEach(System.out::println);        // Print the list using method reference
    }
}