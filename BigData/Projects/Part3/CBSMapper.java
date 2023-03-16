import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class CBSMapper extends Mapper<Object, Text, Text, MapWritable> {
	
	@Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		
        String line = value.toString();
        String[] ids = line.split("\\s");
        
        for(int i=0;i<ids.length-1;i++) {
        	HashMap<String, Integer> H = new HashMap<String, Integer>();
        	for(String id_window: window(ids, i)) {
        		if(H.get(id_window)!=null) H.put(id_window, H.get(id_window)+1);
        		else H.put(id_window, 1);
        	}
        	MapWritable mapWritable = new MapWritable();
        	for (Map.Entry<String, Integer> entry : H.entrySet()) {
        	    mapWritable.put(new Text(entry.getKey()), new IntWritable(entry.getValue()));
        	}
        	context.write(new Text(ids[i]), mapWritable);
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