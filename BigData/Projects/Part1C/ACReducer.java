import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
 
public class ACReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {
 
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

		Integer sum = 0;
        Integer count = 0;
        for (IntWritable i : values) {
            sum += i.get();
            count++;
        }
 
        context.write(key, new DoubleWritable((double) sum/count));
    }
}