import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Mapper<T, U> {

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

        for (String record : this.records) {
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
            word = word.toLowerCase();

            boolean found = false;
            for (Pair pair : this.data) {
                if (pair.key.equals(word.substring(0, 1))) {
                    ((Pair) pair.value).key = (int) ((Pair) pair.value).key + word.length();
                    ((Pair) pair.value).value = (int) ((Pair) pair.value).value + 1;
                    found = true;
                    break;
                }
            }

            if (!found) {
                String key = word.substring(0, 1);
                Pair value = new Pair(word.length(), 1);
                Pair pair = new Pair(key, value);
                this.data.add(pair);
            }
        }
    }

    private List<Pair> Close() {
        return this.data;
    }

    public Pair[] Output(int reducerIndex) {
        Pair[] output = new Pair[this.suffleMap[reducerIndex].size()];
        for (int i = 0; i < this.suffleMap[reducerIndex].size(); i++) {
            int pairIndex = this.suffleMap[reducerIndex].get(i);
            output[i] = this.data.get(pairIndex);
        }
        return output;
    }

    public void Suffle(WordCount wordCount) {
        this.suffleMap = new ArrayList[wordCount.numberOfReducer()];
        for (int i = 0; i < this.suffleMap.length; i++)
            this.suffleMap[i] = new ArrayList<>();

        for (int i = 0; i < this.data.size(); i++) {
            int reducerIndex = wordCount.getPartition(this.data.get(i).key);
            this.suffleMap[reducerIndex].add(i);
        }
    }
}
