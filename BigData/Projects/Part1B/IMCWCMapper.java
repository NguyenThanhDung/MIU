import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


 
public class IMCWCMapper extends Mapper<Object, Text, Text, IntWritable> {
 
	HashMap<String, Integer> H;
	
	@Override
    public void setup(Context context) throws IOException, InterruptedException {
		H = new HashMap<String, Integer>();
	}
	
	@Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
      
 
        String line = value.toString();
 
        // Splitting the line on spaces
        for (String word : line.split(" "))
        {
            if (word.length() > 0)
            {
            	if(H.get(word) != null) H.put(word, H.get(word)+1);
            	else H.put(word, 1);
            }
        }
    }
    
	@Override
    public void cleanup(Context context) throws IOException, InterruptedException {
    	for(Map.Entry<String, Integer> set :H.entrySet()) {
    		context.write(new Text(set.getKey()), new IntWritable(set.getValue()));
    	}
	}
}