import java.util.ArrayList;

public class GroupByPair<T, U> {
    T key;
    ArrayList<U> values;

    public GroupByPair(T key, U value) {
        this.key = key;
        this.values = new ArrayList<>();
        this.values.add(value);
    }
}
