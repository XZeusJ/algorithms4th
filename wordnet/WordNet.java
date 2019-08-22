/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {
    private ST<String, Bag<Integer>> st;
    private Digraph hypernym;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException();

        // generate symbol table (key is noun, value is ids contained in Bag)
        In inSynsets = new In(synsets);
        st = new ST<String, Bag<Integer>>();
        String[] fields;
        String[] nouns;
        int id;
        while (!inSynsets.isEmpty()) {
            fields = inSynsets.readLine().split(",");
            nouns = fields[1].split(" ");

            id = Integer.parseInt(fields[0]);
            for (int i = 0; i < nouns.length; i++) {
                if (st.contains(nouns[i]))
                    st.get(nouns[i]).add(id);
                else {
                    Bag<Integer> ids = new Bag<Integer>();
                    ids.add(id);
                    st.put(nouns[i], ids);
                }
            }
        }

        // generate hypernyms relationship digraph
        In inHypernyms = new In(hypernyms);
        int size = st.size();
        hypernym = new Digraph(size);

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

    // test digraph
    public int root() {
        int v = 0;
        int count = 0;
        while (hypernym.outdegree(v) != 0) {
            for (int i : hypernym.adj(v)) {
                v = i;
                count++;
                break;
            }
        }
        StdOut.println(count);
        return v;
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

        return -1;
    }

    // a synset (second field or synsets.txt) that is the common ancester of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        return "not finished";

    }

    public static void main(String[] args) {
        // test digraph
        String hypernyms = "hypernyms.txt";
        String synsets = "synsets.txt";
        WordNet wn = new WordNet(synsets, hypernyms);

        StdOut.println(wn.root());

    }
}
