package ch25_Sorting_Applications;

public class Record implements Comparable<Record> {
    private final int freq;
    private final String word;

    public Record(String word, int freq) {
        this.freq = freq;
        this.word = word;
    }


    public int compareTo(Record that) {
        if (this.freq < that.freq) return -1;
        if (this.freq > that.freq) return +1;
        return 0;
    }

    public String toString() {
        return freq + " " + word;
    }
}
