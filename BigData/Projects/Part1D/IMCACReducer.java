import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
 
public class IMCACReducer extends Reducer<Text, PairWritable, Text, DoubleWritable> {
 
	public void reduce(Text key, Iterable<PairWritable> values, Context context) throws IOException, InterruptedException {

		Integer sum = 0;
        Integer count = 0;
        for (PairWritable i : values) {
            sum += i.getKey1();
            count += i.getKey2();
        }
 
        context.write(key, new DoubleWritable((double) sum/count));
    }
}