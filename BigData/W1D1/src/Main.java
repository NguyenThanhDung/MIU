import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Mapper mapper = new Mapper("testDataForW1D1.txt");
        System.out.println(mapper);
    }
}