import java.util.List;

public class WordCount<T, U> {

    private Mapper[] mappers;
    private Reducer[] reducers;

    public WordCount(int m, int r) {
        this.mappers = new Mapper[m];

        for (int i = 0; i < this.mappers.length; i++) {
            this.mappers[i] = new Mapper("inputs/mapper" + i + "_input.txt");
        }

        this.reducers = new Reducer[r];
        for (int i = 0; i < this.reducers.length; i++) {
            this.reducers[i] = new Reducer();
        }
    }

    public void Run() {
        for (int i = 0; i < this.mappers.length; i++) {
            this.mappers[i].Run();
        }

        for (int i = 0; i < this.mappers.length; i++) {
            this.mappers[i].Suffle(this);
        }

        for (int i = 0; i < this.reducers.length; i++) {
            for (int j = 0; j < this.mappers.length; j++) {
                this.reducers[i].Receive(this.mappers[j].Output(i));
            }
        }

        for (int i = 0; i < this.reducers.length; i++)
            this.reducers[i].Sort();

        for (int i = 0; i < this.reducers.length; i++) {
            List<Pair> result = this.reducers[i].Run();
            System.out.println("Reducer " + i + " output:");
            for (Pair pair : result)
                System.out.println(pair);
        }
    }

    public int getPartition(T key) {
        int hash = key.hashCode();
        hash = Math.abs(hash);
        int result = hash % this.reducers.length;
        return (int) result;
    }

    public int numberOfReducer() {
        return this.reducers.length;
    }
}
