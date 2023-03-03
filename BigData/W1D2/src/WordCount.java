public class WordCount {

    private Mapper[] mappers;
    private Reducer[] reducers;

    public WordCount(int m, int r) {
        this.mappers = new Mapper[m];

        for(int i = 0; i < this.mappers.length; i++) {
            this.mappers[i] = new Mapper("inputs/mapper" + i + "_input.txt");
        }

        this.reducers = new Reducer[r];
        for(int i = 0; i < this.reducers.length; i++) {
            this.reducers[i] = new Reducer();
        }
    }

    public void Run() {
        for(int i = 0; i < this.mappers.length; i++) {
            this.mappers[i].Run();
        }

        for(int i = 0; i < this.mappers.length; i++) {
            this.mappers[i].Suffle(this);
        }

//        for(int i = 0; i < this.reducers.length; i++) {
//            for(int j = 0; j < this.mappers.length; j++) {
//                this.reducers[i].Receive(this.mappers.Output(i));
//            }
//        }
//
//        for(int i = 0; i < this.reducers.length; i++) {
//            this.reducers[i].Reduce();
//        }
    }

    public int getPartition(String key){
        int hash = key.hashCode();
        hash = Math.abs(hash);
        int result = hash % this.reducers.length;
		return (int) result;
	}

    public int numberOfReducer() {
        return this.reducers.length;
    }
}
