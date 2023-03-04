import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Reducer<T, U> {
    List<Pair> receivedData;
    List<GroupByPair> inputs;
    List<Pair> outputs;

    public Reducer() {
        this.receivedData = new ArrayList<>();
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public void Receive(Pair[] input) {
        for (Pair pair : input) {
            this.receivedData.add(pair);
        }
    }

    public void Sort() {
        this.receivedData = this.receivedData.stream().sorted(GetComparator()).collect(Collectors.toList());
    }

    public Comparator<Pair> GetComparator() {
        return new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return ((String) p1.key).compareTo((String) p2.key);
            }
        };
    }

    public List<Pair> Run() {
        Grouping();
        return Reduce();
    }

    public void Grouping() {
        for (Pair pair : this.receivedData) {
            boolean found = false;
            for (GroupByPair groupByPair : this.inputs) {
                if (groupByPair.key.equals(pair.key)) {
                    groupByPair.values.add(pair.value);
                    found = true;
                    break;
                }
            }
            if (!found) {
                GroupByPair groupByPair = new GroupByPair(pair.key, pair.value);
                this.inputs.add(groupByPair);
            }
        }
    }

    public List<Pair> Reduce() {
        for (GroupByPair groupByPair : this.inputs) {
            int sum = 0;
            int count = 0;
            for (Object value : groupByPair.values) {
                Pair pair = (Pair) value;
                sum += (int) pair.key;
                count += (int) pair.value;
            }
            double average = (double) sum / count;
            Pair pair = new Pair(groupByPair.key, average);
            this.outputs.add(pair);
        }
        return this.outputs;
    }
}
