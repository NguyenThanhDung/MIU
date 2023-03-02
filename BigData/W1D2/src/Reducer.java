import java.util.ArrayList;
import java.util.List;

public class Reducer {
    List<Pair> inputs;
    List<GroupByPair> list;
    List<Pair> outputs;

    public Reducer() {
        this.inputs = new ArrayList<>();
        this.list = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public Reducer(List<Pair> input) {
        this.inputs = new ArrayList<>();
        this.list = new ArrayList<>();
        this.outputs = new ArrayList<>();
        for (Pair pair : input) {
            Add(pair);
        }
    }

    public void Add(Pair pair) {
        this.inputs.add(pair);
    }

    public void Grouping() {
        for(Pair pair : this.inputs) {
            boolean found = false;
            for (GroupByPair groupByPair : this.list) {
                if (groupByPair.key.equals(pair.key)) {
                    groupByPair.values.add(pair.value);
                    found = true;
                    break;
                }
            }
            if (!found) {
                GroupByPair groupByPair = new GroupByPair(pair.key, pair.value);
                this.list.add(groupByPair);
            }
        }
    }

    public void GenerateOutput() {
        for(GroupByPair groupByPairair : this.list) {
            int sum = 0;
            for(int value : groupByPairair.values)
                sum += value;
            Pair pair = new Pair(groupByPairair.key, sum);
            this.outputs.add(pair);
        }
    }

    public String GetInputString() {
        String output = "";
        for (Pair pair : this.inputs) {
            output += pair.toString() + "\n";
        }
        return output;
    }

    public String GetGroupedResultString() {
        String output = "";
        for(GroupByPair groupByPair : this.list) {
            output += "< " + groupByPair.key + " , [";
            for(int i = 0; i < groupByPair.values.size(); i++) {
                output += groupByPair.values.get(i);
                if(i < groupByPair.values.size() - 1)
                    output += ", ";
            }
            output += "] >\n";
        }
        return output;
    }

    public String GetOutputString() {
        String output = "";
        for(Pair pair : this.outputs) {
            output += pair.toString() + "\n";
        }
        return output;
    }
}
