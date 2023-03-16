import java.io.IOException;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
 

public class CBSDriver extends Configured implements Tool {
 
    public int run(String args[]) throws IOException, ClassNotFoundException, InterruptedException
    {
        if (args.length < 2)
        {
            System.out.println("Please give valid inputs");
            return -1;
        }
        
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "crystal ball pairs");
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setJarByClass(CBSDriver.class);
        job.setMapperClass(CBSMapper.class);
        job.setReducerClass(CBSReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(MapWritable.class);
        return job.waitForCompletion(true) ? 0 : 1;
    }
 
    // Main Method
    public static void main(String args[]) throws Exception
    {
        int exitCode = ToolRunner.run(new CBSDriver(), args);
        System.out.println(exitCode);
    }
}