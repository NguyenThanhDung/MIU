public class WordCount {

    private Mapper[] mappers;
    private Reducer[] reducers;

    public WordCount(int m, int r) {
        System.out.println("Number of Input-Splits: " + m);
        System.out.println("Number of Reducers: " + r);

        this.mappers = new Mapper[m];

        for(int i = 0; i < this.mappers.length; i++) {
            this.mappers[i] = new Mapper("inputs/mapper" + i + "_input.txt");

            System.out.println("Mapper " + i + " Output:");
            System.out.println(this.mappers[i]);
        }

        this.reducers = new Reducer[r];
        for(int i = 0; i < this.reducers.length; i++) {
            this.reducers[i] = new Reducer();
        }
    }

    public void Run() {
        for(int i = 0; i < this.mappers.length; i++) {
            for(int j = 0; j < this.mappers[i].Output().size(); j++) {
                Pair outputPair = this.mappers[i].Output().get(j);
                int reducerIndex = getPartition(outputPair.key);
                this.reducers[reducerIndex].Add(outputPair);
            }
        }

        for(int i = 0; i < this.reducers.length; i++) {
            System.out.println("Pairs sent to Reducer " + i);
            System.out.println(this.reducers[i].GetInputString());
        }
    }

    public int getPartition(String key){
        int hash = key.hashCode();
        hash = Math.abs(hash);
        int result = hash % this.reducers.length;
		return (int) result;
	}
}
