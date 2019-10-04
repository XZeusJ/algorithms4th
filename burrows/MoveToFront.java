/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] sequence = createSequence();

        while (!BinaryStdIn.isEmpty()) {
            // reading a character from the input message
            char c = BinaryStdIn.readChar();

            int i = 0;
            while (i < sequence.length) {
                if (sequence[i] == c) break;
                i++;
            }

            // printing the position in the sequence in which that character appears
            BinaryStdOut.write((char) i);

            // moving that character to the front of the sequence
            sequence = moveToFront(sequence, c, i);
        }

        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] sequence = createSequence();

        while (!BinaryStdIn.isEmpty()) {
            // read each 8-bit character i from standard input one at a time
            int i = BinaryStdIn.readChar();
            char c = sequence[i];
            // write the ith character in the sequence
            BinaryStdOut.write(c);

            // mvoe that character to the front
            sequence = moveToFront(sequence, c, i);
        }

        BinaryStdIn.close();
        BinaryStdOut.close();
    }

    private static char[] createSequence() {
        char[] sequence = new char[R];
        for (int i = 0; i < R; i++) {
            sequence[i] = (char) i;
        }
        return sequence;
    }

    private static char[] moveToFront(char[] sequence, char c, int i) {
        for (int j = i; j > 0; j--) {
            sequence[j] = sequence[j - 1];
        }
        sequence[0] = c;
        return sequence;
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        // StdOut.print(1);
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException();
    }
}
