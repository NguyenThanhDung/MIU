import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reducer {
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
        this.receivedData = this.receivedData.stream().sorted((a, b) -> a.key.compareTo(b.key)).collect(Collectors.toList());
    }

    public List<Pair> Run() {
        Grouping();
        return Reduce();
    }

    public void Grouping() {
        for(Pair pair : this.receivedData) {
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
        for(GroupByPair groupByPair : this.inputs) {
            int sum = 0;
            for(int value : groupByPair.values)
                sum += value;
            Pair pair = new Pair(groupByPair.key, sum);
            this.outputs.add(pair);
        }
        return this.outputs;
    }
}
