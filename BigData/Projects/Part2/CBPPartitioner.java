import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;


public class CBPPartitioner extends Partitioner<PairWritable, IntWritable> {

    @Override
    public int getPartition(PairWritable key, IntWritable value, int i) {

        return Math.abs(key.getKey1().hashCode()) % i;
    }

}