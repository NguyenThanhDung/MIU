package Prob3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public int countWords(List<String> words, char c, char d, int len){
        return (int)words.stream()
                .filter(s -> s.length() == len)
                .filter(s -> s.contains(c + ""))
                .filter(s -> !s.contains(d + ""))
                .count();
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Bill", "Thomas", "Mary");
        Main program = new Main();
        System.out.println(program.countWords(names, 'l', 'm', 4));
    }
}
