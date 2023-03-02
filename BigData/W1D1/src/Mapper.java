import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private List<String> records;
    private List<Pair> data;

    public Mapper(String inputFile) {
        this.records = new ArrayList<>();
        try {
            Path path = Paths.get(System.getProperty("user.dir"), inputFile);
            FileReader fileReader = new FileReader(path.toString());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.records.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Pair> Run() {
        Initialize();

        for(String record : this.records) {
            Map(record);
        }

        return Close();
    }

    private void Initialize() {
        this.data = new ArrayList<>();
    }

    private void Map(String record) {
        String[] words = record.split("\\W");
        for (String word : words) {
            if (word.isEmpty())
                continue;
            Pair pair = new Pair(word.toLowerCase(), 1);
            this.data.add(pair);
        }
    }

    private List<Pair> Close() {
        return this.data;
    }
}
