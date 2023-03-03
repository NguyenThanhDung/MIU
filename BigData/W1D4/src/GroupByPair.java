import java.util.ArrayList;

public class GroupByPair {
    String key;
    ArrayList<Integer> values;

    public GroupByPair(String key, Integer value) {
        this.key = key;
        this.values = new ArrayList<Integer>();
        this.values.add(value);
    }
}
