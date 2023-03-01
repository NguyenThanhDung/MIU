import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
//        Mapper mapper = new Mapper("testDataForW1D1.txt");
//        System.out.println("Mapper Output");
//        System.out.println(mapper);
//
//        Reducer reducer = new Reducer(mapper.Output());
//        System.out.println("Reducer Input");
//        System.out.println(reducer);
//
//        System.out.println("Reducer Output");
//        System.out.println(reducer.Sum());

        WordCount wordCount = new WordCount(3, 4);
        wordCount.Run();
    }
}