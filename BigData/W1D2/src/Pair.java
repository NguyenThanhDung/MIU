public class Pair {
    public String key;
    public int value;

    public Pair(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return "(" + this.key + "," + this.value + ")";
    }
}
