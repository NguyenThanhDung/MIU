import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;


public class PairWritable implements WritableComparable<PairWritable> {

    protected String key1;
    protected String key2;

    PairWritable(String key1, String key2) {
		this.key1 = key1;
		this.key2 = key2;
	}
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key) {
		this.key1 = key;
	}
	public String getKey2() {
		return key2;
	}
	public void setKey2(String key) {
		this.key2 = key;
	}

    PairWritable() {
    }

    @Override
    public void write(DataOutput d) throws IOException {
        d.writeUTF(key1);
        d.writeUTF(key2);

    }

    @Override
    public void readFields(DataInput di) throws IOException {
        key1 = di.readUTF();
        key2 = di.readUTF();
    }

    @Override
    public String toString() {
        return "("+key1+", "+key2+")";
    }

    @Override
    public int compareTo(PairWritable t) {
        String thiskey1 = this.key1;
        String thatkey1 = t.key1;

        String thiskey2 = this.getKey2();
        String thatkey2 = t.getKey2();
        
        return thiskey1.compareTo(thatkey1) != 0 ? thiskey1.compareTo(thatkey1)
                : thiskey2.compareTo(thatkey2);
    }
}
