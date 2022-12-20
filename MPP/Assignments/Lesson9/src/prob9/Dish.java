package prob9;
import java.util.*;

public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    public enum Type { MEAT, FISH, OTHER }

    @Override
    public String toString() {
        return name;
    }

    public static final List<Dish> menu =
            Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
                           new Dish("beef", false, 700, Dish.Type.MEAT),
                           new Dish("chicken", false, 400, Dish.Type.MEAT),
                           new Dish("french fries", true, 530, Dish.Type.OTHER),
                           new Dish("rice", true, 350, Dish.Type.OTHER),
                           new Dish("season fruit", true, 120, Dish.Type.OTHER),
                           new Dish("pizza", true, 550, Dish.Type.OTHER),
                           new Dish("prawns", false, 400, Dish.Type.FISH),
                           new Dish("salmon", false, 450, Dish.Type.FISH));

    public static boolean hasVegetarianMeal() {
        return menu.stream().anyMatch(d -> d.isVegetarian());
    }

    public static boolean hasHealthyMeal() {
        return menu.stream().anyMatch(d -> d.getCalories() < 1000);
    }

    public static boolean hasUnhealthyMeal() {
        return menu.stream().anyMatch(d -> d.getCalories() > 1000);
    }

    public static Optional<Dish> firstItemTypeOfMeat() {
        return menu.stream()
                .filter(d -> d.getType() == Type.MEAT)
                .findFirst();
    }

    public static int totalCalories() {
        return menu.stream()
                .map(d -> d.getCalories())
                .reduce(0, (d1, d2) -> d1 + d2);
    }

    public static int totalCaloriesMethodReference() {
        return menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        System.out.println("a. " + hasVegetarianMeal());
        System.out.println("b. " + hasHealthyMeal());
        System.out.println("c. " + hasUnhealthyMeal());
        System.out.println("d. " + firstItemTypeOfMeat().orElse(null));
        System.out.println("e. " + totalCalories());
        System.out.println("f. " + totalCaloriesMethodReference());
    }
}