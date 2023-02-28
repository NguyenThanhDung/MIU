import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Mapper mapper = new Mapper();
        try {
            Path path = Paths.get(System.getProperty("user.dir"), "input.txt");
            FileReader fileReader = new FileReader(path.toString());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                mapper.Insert(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("MAPPER:");
        System.out.println(mapper);

        Reducer reducer = new Reducer(mapper.list);
        System.out.println("REDUCER:");
        System.out.println(reducer);
    }
}