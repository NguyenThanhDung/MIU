import java.util.ArrayList;

public class Reducer {
    ArrayList<GroupByPair> list;

    public Reducer(ArrayList<Pair> input) {
        this.list = new ArrayList<GroupByPair>();
        for (Pair pair : input) {
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

    public String toString() {
        String output = "";
        for(GroupByPair groupByPair : this.list) {
            output += "(" + groupByPair.key + ",[";
            for(int i = 0; i < groupByPair.values.size(); i++) {
                output += groupByPair.values.get(i);
                if(i < groupByPair.values.size() - 1)
                    output += ",";
            }
            output += "])\n";
        }
        return output;
    }
}
