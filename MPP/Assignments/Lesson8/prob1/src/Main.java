import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        // Yes. The expression can be typed as a BiFunction:
        BiFunction<Double, Double, List> myLambda = (x, y) -> {
            List<Double> list = new ArrayList<>();
            list.add(Math.pow(x, y));
            list.add(x * y);
            return list;
        };
        System.out.println(myLambda.apply(2.0, 3.0));
    }
}