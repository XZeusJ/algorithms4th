package ch25_Sorting_Applications;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class California {
    public static final Comparator<String> CANDIDATE_ORDER = new CandidateComparator();


    private static class CandidateComparator implements Comparator<String> {

        private static String alphabet = "RWQOJMVAHBSGZXNTCIEKUPDVFL";

        public int compare(String a, String b) {
            for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
                Character s = a.charAt(i);
                Character t = b.charAt(i);

                int sIndex = alphabet.indexOf(s);
                int tIndex = alphabet.indexOf(t);

                if (sIndex != -1 && tIndex != -1) {
                    if (sIndex < tIndex) return -1;
                    else if (sIndex > tIndex) return +1;
                } else {
                    int cmp = s.compareTo(t);
                    if (cmp < 0) return -1;
                    else if (cmp > 0) return +1;
                }
            }
            return a.length() - b.length();
        }
    }


    // test client
    public static void main(String[] args) {

        // read in domain names
        String[] candidates = StdIn.readAllLines();


        // sort
        Arrays.sort(candidates, California.CANDIDATE_ORDER);

        // print results
        for (int i = 0; i < candidates.length; i++) {
            StdOut.println(candidates[i]);
        }
    }
}
