import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Mapper mapper = new Mapper("input.txt");
        System.out.println("MAPPER:");
        System.out.println(mapper);

        Reducer reducer = new Reducer(mapper.Output());
        System.out.println("REDUCER:");
        System.out.println(reducer);

        System.out.println("SUM:");
        System.out.println(reducer.Sum());
    }
}