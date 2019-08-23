/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wordnet;

    public Outcast(WordNet wordnet) {       // constructor takes a WordNet object
        this.wordnet = wordnet;
    }

    public String outcast(String[] nouns) {  // given an array of WordNet nouns, return an outcast
        int maxDist = 0;
        String outcast = nouns[0];

        for (String noun : nouns) {
            int dist = distance(noun, nouns);

            if (dist > maxDist) {
                maxDist = dist;
                outcast = noun;
            }
        }
        return outcast;
    }

    private int distance(String noun, String[] nouns) {
        int dist = 0;
        for (int i = 0; i < nouns.length; i++)
            dist += this.wordnet.distance(noun, nouns[i]);
        return dist;
    }

    public static void main(String[] args) { // see test client below
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
