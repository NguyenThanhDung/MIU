import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Mapper mapper = new Mapper("testDataForW1D1.txt");
        List<Pair> output = mapper.Run();
        for(Pair pair : output) {
            System.out.println(pair);
        }
    }
}