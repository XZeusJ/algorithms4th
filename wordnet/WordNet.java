/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

import java.util.ArrayList;

public class WordNet {
    private final ST<String, Bag<Integer>> nouns; // map nouns to idSET
    private final ArrayList<String> synsets;  // map id to synsets
    private final Digraph hypernym; // describe relationship between each synsets
    private final SAP sap; // a tool that find shortest ancestor path in hypernym relation digraph

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException();

        // generate id to nouns (synsets array)
        In inSynsets = new In(synsets);
        this.synsets = new ArrayList<String>();

        // generate symbol table of nouns to id (key is noun, value is ids contained in Bag)
        this.nouns = new ST<String, Bag<Integer>>();
        String[] fields;
        String[] eachNouns;
        int id;
        while (!inSynsets.isEmpty()) {
            fields = inSynsets.readLine().split(",");
            this.synsets.add(fields[1]);

            eachNouns = fields[1].split(" ");
            id = Integer.parseInt(fields[0]);
            for (int i = 0; i < eachNouns.length; i++) {
                if (nouns.contains(eachNouns[i]))
                    nouns.get(eachNouns[i]).add(id);
                else {
                    Bag<Integer> ids = new Bag<Integer>();
                    ids.add(id);
                    nouns.put(eachNouns[i], ids);
                }
            }
        }

        // generate hypernyms relationship digraph
        In inHypernyms = new In(hypernyms);
        int size = this.synsets.size();
        this.hypernym = new Digraph(size);

        int v, w;
        while (!inHypernyms.isEmpty()) {
            fields = inHypernyms.readLine().split(",");
            v = Integer.parseInt(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                w = Integer.parseInt(fields[i]);
                this.hypernym.addEdge(v, w);
            }
        }

        // generate sap by using hypernyms digraph
        this.sap = new SAP(this.hypernym);

        // check if digraph has invalid roots
        int roots = 0;
        for (int i = 0; i < this.hypernym.V(); i++) {
            if (this.hypernym.outdegree(i) == 0) roots += 1;
        }
        if (roots != 1) throw new IllegalArgumentException();

        // check if digraph has cycle
        DirectedCycle directedCycle = new DirectedCycle(this.hypernym);
        if (directedCycle.hasCycle()) throw new IllegalArgumentException();


    }


    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return nouns.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();

        Bag<Integer> aIDs = nouns.get(nounA);
        Bag<Integer> bIDs = nouns.get(nounB);

        return this.sap.length(aIDs, bIDs);
    }

    // a synset (second field or synsets.txt) that is the common ancester of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new IllegalArgumentException();
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();

        Bag<Integer> aIDs = nouns.get(nounA);
        Bag<Integer> bIDs = nouns.get(nounB);

        int ancestor = this.sap.ancestor(aIDs, bIDs);
        return this.synsets.get(ancestor);
    }

    public static void main(String[] args) {
        WordNet wn = new WordNet("synsets.txt", "hypernyms.txt");

    }
}
