/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class CircularSuffixArray {
    private final String string;
    private final Integer[] indices;

    // circular suffic array of s
    public CircularSuffixArray(String s) {
        // ** space -> n+R
        // ** time -> nLogn
        if (s == null) throw new IllegalArgumentException();
        this.string = s;

        Integer[] indices = new Integer[string.length()];
        for (int i = 0; i < string.length(); i++)
            indices[i] = i;

        Arrays.sort(indices, suffixOrder());
        this.indices = indices;
    }

    // length of s
    public int length() {
        return this.string.length();
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (!(i >= 0 || i <= length() - 1)) throw new IllegalArgumentException();
        return this.indices[i];
    }

    private Comparator<Integer> suffixOrder() {
        return new Comparator<Integer>() {
            @Override
            public int compare(Integer first, Integer second) {
                for (int i = 0; i < string.length(); i++) {
                    int index1 = (first + i) % string.length();
                    int index2 = (second + i) % string.length();
                    int result = string.charAt(index1) - string.charAt(index2);
                    if (result != 0) { return result; }
                }
                return 0;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray suffixArray = new CircularSuffixArray(args[0]);
        for (int i = 0; i < suffixArray.length(); i++) {
            StdOut.print(suffixArray.index(i) + " ");
        }

        StdOut.println("");
    }
}
