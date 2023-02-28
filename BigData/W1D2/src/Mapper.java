import java.util.ArrayList;

public class Mapper {
    public ArrayList<Pair> list;

    public Mapper() {
        this.list = new ArrayList<Pair>();
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

    public String toString() {
        String output = "";
        for (Pair pair : this.list) {
            output += pair.toString() + "\n";
        }
        return output;
    }
}
