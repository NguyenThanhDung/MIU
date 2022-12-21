package prob10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Human {
    String name;
    int age;
    String gender;

    public Human(String name) {
        this.name = name;
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Human(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Human [name=" + name + ", age=" + age + ", gender=" + gender + "]";
    }
}

public class ConstructorReference {
    public static void main(String args[]) {
        Human[] list = {new Human("Joe", 35, "Male"),
                new Human("Jane", 45, "Female"),
                new Human("John", 30, "Male")};
        List<Human> humanList = Arrays.asList(list);

        // Query 1  : Print only Female candidates names
        String femaleNames = humanList.stream()
                .filter(h -> h.getGender().equals("Female"))
                .map(h -> h.getName())
                .collect(Collectors.joining(", "));
        System.out.println(femaleNames);

        // Query 2 : Create an object by choosing suitable Interface to the specified constructors(Totally 3 constructors)
        // using fourth type of Method Reference ClassName::new. Then print the object status
        Function<String, Human> constructor1 = Human::new;
        Human human1 = constructor1.apply("Human1");
        System.out.println(human1);

        BiFunction<String, Integer, Human> constructor2 = Human::new;
        Human human2 = constructor2.apply("Human2", 20);
        System.out.println(human2);

        TriFunction<String, Integer, String, Human> constructor3 = Human::new;
        Human human3 = constructor3.apply("Human3", 40, "Male");
        System.out.println(human3);

        // Query 3 : Count the male candidates whose age is more than 30
        long count = humanList.stream()
                .filter(h -> h.getGender().equals("Male"))
                .filter(h -> h.getAge() > 30)
                .count();
        System.out.println(count);
    }


}
