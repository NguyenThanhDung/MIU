import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;


public class PairWritable implements WritableComparable<PairWritable> {

    protected Integer key1;
    protected Integer key2;

    public Integer getKey1() {
        return key1;
    }

    public void setKey1(Integer key1) {
        this.key1 = key1;
    }

    public Integer getKey2() {
        return key2;
    }

    public void setKey2(Integer key2) {
        this.key2 = key2;
    }

    PairWritable(Integer key1, Integer key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    PairWritable() {
    }

    @Override
    public void write(DataOutput d) throws IOException {
        d.writeInt(key1);
        d.writeInt(key2);

    }

    @Override
    public void readFields(DataInput di) throws IOException {
        key1 = di.readInt();
        key2 = di.readInt();
    }

    @Override
    public String toString() {
        return key1.toString().concat(key2.toString());
    }

    @Override
    public int compareTo(PairWritable t) {
        Integer thiskey1 = this.key1;
        Integer thatkey1 = t.key1;

        Integer thiskey2 = this.getKey2();
        Integer thatkey2 = t.getKey2();
        
        return thiskey1.compareTo(thatkey1) != 0 ? thiskey1.compareTo(thatkey1)
                : thiskey2.compareTo(thatkey2);
    }
}