import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class IMCACMapper extends Mapper<Object, Text, Text, PairWritable> {
	
	HashMap<String, Pair> H;
	
	@Override
    public void setup(Context context) throws IOException, InterruptedException {
		H = new HashMap<String, Pair>();
	}
	
	@Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		
        String line = value.toString();
        if(line.matches("(\\d{1,3}\\.){3}\\d{1,3}\\s.+")) {
        	String ip = line.split(" ")[0];
        	try{
        		Integer comp = Integer.parseInt(line.split(" ")[line.split(" ").length-1]);
        		if(H.get(ip) != null) {
        			H.get(ip).setSum(H.get(ip).getSum()+comp);
        			H.get(ip).setCount(H.get(ip).getCount()+1);
        		}
            	else H.put(ip, new Pair(comp, 1));
            }
            catch(NumberFormatException e){}
        	
        }
    }
	
	@Override
    public void cleanup(Context context) throws IOException, InterruptedException {
    	for(Map.Entry<String, Pair> set :H.entrySet()) {
    		context.write(new Text(set.getKey()), new PairWritable(set.getValue().getSum(), set.getValue().getCount()));
    	}
	}

}