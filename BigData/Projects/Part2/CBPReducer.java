import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;
 
public class CBPReducer extends Reducer<PairWritable, IntWritable, PairWritable, DoubleWritable> {
	
	Integer count = 0;
 
	public void reduce(PairWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

		Integer sum = 0;
        for (IntWritable i : values) {
            sum += i.get();
        }
        if(key.getKey2().equals("*")) count = sum;
        else context.write(key, new DoubleWritable((double) sum/count));
    }
}