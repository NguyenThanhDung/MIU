import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class ACMapper extends Mapper<Object, Text, Text, IntWritable> {
	
	@Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		
        String line = value.toString();
        if(line.matches("(\\d{1,3}\\.){3}\\d{1,3}\\s.+")) {
        	String ip = line.split(" ")[0];
        	try{
        		Integer comp = Integer.parseInt(line.split(" ")[line.split(" ").length-1]);
            	context.write(new Text(ip), new IntWritable(comp));
            }
            catch(NumberFormatException e){}
        	
        }
    }

}