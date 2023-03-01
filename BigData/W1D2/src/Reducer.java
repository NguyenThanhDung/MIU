import java.util.ArrayList;
import java.util.List;

public class Reducer {
    ArrayList<GroupByPair> list;

    public Reducer() {
        this.list = new ArrayList<GroupByPair>();
    }

    public Reducer(List<Pair> input) {
        this.list = new ArrayList<GroupByPair>();
        for (Pair pair : input) {
            Add(pair);
        }
    }

    public void Add(Pair pair) {
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

    public String toString() {
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

    public String Sum() {
        String output = "";
        for(GroupByPair groupByPairair : this.list) {
            output += "< " + groupByPairair.key + " , ";

            int sum = 0;
            for(int value : groupByPairair.values)
                sum += value;
            output += sum + " >\n";
        }
        return output;
    }
}
