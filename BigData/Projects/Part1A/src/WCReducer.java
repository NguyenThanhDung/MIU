// Importing libraries
import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WCReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	// Reduce function
	public void reduce(Text key, Iterator<IntWritable> value, Context context)
			throws IOException, InterruptedException {
		context.write(new Text("NtdCount"), new IntWritable(1));

		int count = 0;

		// Counting the frequency of each words
		while (value.hasNext()) {
			IntWritable i = value.next();
			count += i.get();
		}

		context.write(key, new IntWritable(count));
	}
}