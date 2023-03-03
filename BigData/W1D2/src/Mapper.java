import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Mapper {

    private List<String> records;
    private List<Pair> data;
    private List<Integer>[] suffleMap;

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

    public void Suffle(WordCount wordCount) {
        this.suffleMap = new ArrayList[wordCount.numberOfReducer()];
        for(int i = 0; i < this.data.size(); i++) {
            int reducerIndex = wordCount.getPartition(this.data.get(i).key);

            if(this.suffleMap[reducerIndex] == null)
                this.suffleMap[reducerIndex] = new ArrayList<>();
            this.suffleMap[reducerIndex].add(i);
        }
    }
}
