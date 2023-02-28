import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Mapper {
    private ArrayList<Pair> list;

    public Mapper(String inputFile) {
        this.list = new ArrayList<Pair>();
        try {
            Path path = Paths.get(System.getProperty("user.dir"), inputFile);
            FileReader fileReader = new FileReader(path.toString());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Insert(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Insert(String text) {
        String[] words = text.split("\\W");
        for (String word : words) {
            if (word.isEmpty())
                continue;
            Pair pair = new Pair(word.toLowerCase(), 1);
            this.list.add(pair);
        }
    }

    public ArrayList<Pair> Output() {
        return this.list;
    }

    public String toString() {
        String output = "";
        for (Pair pair : this.list) {
            output += pair.toString() + "\n";
        }
        return output;
    }
}
