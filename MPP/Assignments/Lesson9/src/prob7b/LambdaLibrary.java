package prob7b;

import java.util.List;
import java.util.stream.Collectors;

public class LambdaLibrary {
    public static final TriFunction<List<Employee>, Integer, Character, String> NAMES_EMPLOYEES = (list, minSalary, startChar) ->
            list.stream()
                    .filter(e -> e.getSalary() > minSalary)
                    .filter(e -> e.getLastName().compareTo(startChar + "") >= 0)
                    .map(e -> e.getFirstName() + " " + e.getLastName())
                    .sorted()
                    .collect(Collectors.joining(", "));
}
