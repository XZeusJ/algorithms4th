/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

import java.util.ArrayList;

public class WordNet {
    private ST<String, Bag<Integer>> st;
    private Digraph hypernym;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException();

        // generate symbol table (key is noun, value is ids contained in Bag)
        In inSynsets = new In(synsets);
        int id;
        String[] fields;
        String[] nouns;
        while (!inSynsets.isEmpty()) {
            fields = inSynsets.readLine().split(",");
            id = Integer.parseInt(fields[0]);
            nouns = fields[1].split(" ");
            for (int i = 0; i < nouns.length; i++) {
                if (st.contains(nouns[i]))
                    st.get(nouns[i]).add(id);
                else {
                    Bag ids = new Bag();
                    ids.add(id);
                    st.put(nouns[i], ids);
                }
            }
        }

        // generate hypernyms relationship digraph
        int size = st.size();
        hypernym = new Digraph(size);
        In inHypernyms = new In(hypernyms);

        int v, w;
        while (!inHypernyms.isEmpty()) {
            fields = inHypernyms.readLine().split(",");
            v = Integer.parseInt(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                w = Integer.parseInt(fields[i]);
                hypernym.addEdge(v, w);
            }
        }

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return st.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return st.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();


    }

    // a synset (second field or synsets.txt) that is the common ancester of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();


    }

    public static void main(String[] args) {

    }
}
