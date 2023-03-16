import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class ACComparator extends WritableComparator {
	
	protected ACComparator() {
        super(Text.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
    	Integer a_int0 = Integer.parseInt(((Text) a).toString().split("\\.")[0]);
    	Integer b_int0 = Integer.parseInt(((Text) b).toString().split("\\.")[0]);
    	
    	Integer a_int1 = Integer.parseInt(((Text) a).toString().split("\\.")[1]);
    	Integer b_int1 = Integer.parseInt(((Text) b).toString().split("\\.")[1]);
    	
    	Integer a_int2 = Integer.parseInt(((Text) a).toString().split("\\.")[2]);
    	Integer b_int2 = Integer.parseInt(((Text) b).toString().split("\\.")[2]);
    	
    	Integer a_int3 = Integer.parseInt(((Text) a).toString().split("\\.")[3]);
    	Integer b_int3 = Integer.parseInt(((Text) b).toString().split("\\.")[3]);
    	
    	int k = a_int0.compareTo(b_int0);
    	if(k!=0) return k;
    	
    	k = a_int1.compareTo(b_int1);
    	if(k!=0) return k;
    	
    	k = a_int2.compareTo(b_int2);
    	if(k!=0) return k;
    	
    	return a_int3.compareTo(b_int3);
    }

}