import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;
 
public class CBSReducer extends Reducer<Text, MapWritable, Text, Text> {
 
	public void reduce(Text key, Iterable<MapWritable> values, Context context) throws IOException, InterruptedException {
		Integer count = 0;
		HashMap<String, Integer> H = new HashMap<String, Integer>();
		for (MapWritable entry : values) {
			for (Entry<Writable, Writable> extractData: entry.entrySet()) {
			      String id = ((Text) extractData.getKey()).toString();
			      Integer val = ((IntWritable) extractData.getValue()).get();
			      count += val;
			      if(H.get(id)!=null) H.put(id, H.get(id)+val);
			      else H.put(id, val);
			}                    
		}
		
		String stripe = "[";
    	for (Map.Entry<String, Integer> entry : H.entrySet()) {
    		if(!stripe.equals("[")) stripe += ", ";
    		stripe += "("+entry.getKey()+", "+((double)entry.getValue()/count)+")";
    	}
    	stripe += "]";
    	context.write(key, new Text(stripe));
    }
}