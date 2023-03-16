import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class CBPMapper extends Mapper<Object, Text, PairWritable, IntWritable> {
	
	@Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		
        String line = value.toString();
        String[] ids = line.split("\\s");
        for(int i=0;i<ids.length-1;i++) {
        	for(String id_window: window(ids, i)) {
        		context.write(new PairWritable(ids[i], "*"), new IntWritable(1));
        		context.write(new PairWritable(ids[i], id_window), new IntWritable(1));
        	}
        }	
    }
	
	private List<String> window(String[] ids, int index) {
		List<String> res = new ArrayList<String>();
		for(int i=index+1;i<ids.length;i++) {
			if(!ids[index].equals(ids[i])) res.add(ids[i]);
			else break;
		}
		return res;
	}

}