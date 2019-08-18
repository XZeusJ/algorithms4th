/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Board {
    private final int[] tiles1D;
    private final int len;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        len = tiles.length;
        this.tiles1D = new int[len * len];

        for (int i = 0; i < len; i++)
            for (int j = 0; j < len; j++)
                tiles1D[i * len + j] = tiles[i][j];
    }

    // helper constructor
    private Board(int[] tiles1D) {
        len = (int) Math.sqrt(tiles1D.length);
        this.tiles1D = Arrays.copyOf(tiles1D, tiles1D.length);
    }


    // string representation of this board
    public String toString() {
        StringBuilder tiles = new StringBuilder();

        tiles.append(len + "\n");
        for (int i = 0; i < tiles1D.length; i++) {
            tiles.append(String.format("%2d ", tiles1D[i]));
            if ((i + 1) % len == 0) tiles.append("\n");
        }
        return tiles.toString();
    }

    // board dimension n
    public int dimension() {
        return len;
    }

    // number of tiles out of place
    public int hamming() {
        int out = 0;
        for (int i = 0; i < tiles1D.length; i++) {
            if (tiles1D[i] != i + 1 && tiles1D[i] != 0) out++;
        }
        return out;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        int temp, rowDist, colDist;
        for (int i = 0; i < tiles1D.length; i++) {
            temp = tiles1D[i];
            if (temp != i + 1 && temp != 0) {
                rowDist = Math.abs((temp - 1) / len - i / len);
                colDist = Math.abs((temp - 1) % len - i % len);
                sum += rowDist + colDist;
            }
        }
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null || y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return this.dimension() == that.dimension() && Arrays.equals(this.tiles1D, that.tiles1D);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>();
        int[] neighborBoard;
        int blankIndex = 0;
        int neighborIndex;

        // StdOut.println(this);

        while (tiles1D[blankIndex] != 0) blankIndex++;    // find blank index

        int[] diffX = { -1, 1, 0, 0 };
        int[] diffY = { 0, 0, -1, 1 };

        for (int i = 0; i < 4; i++) {
            int neighborRow = blankIndex / len + diffX[i];
            int neighborCol = blankIndex % len + diffY[i];

            if (neighborRow >= 0 && neighborRow < len && neighborCol >= 0 && neighborCol < len) {
                neighborIndex = neighborRow * len + neighborCol;

                neighborBoard = swapTiles(blankIndex, neighborIndex);
                neighbors.push(new Board(neighborBoard));
            }
        }
        return neighbors;
    }

    private int[] swapTiles(int ptileIndex, int qtileIndex) {
        int[] tiles = Arrays.copyOf(tiles1D, tiles1D.length);

        int swap = tiles[ptileIndex];
        tiles[ptileIndex] = tiles[qtileIndex];
        tiles[qtileIndex] = swap;

        return tiles;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[] twinTiles;

        if (tiles1D[0] != 0 && tiles1D[1] != 0) twinTiles = swapTiles(0, 1);
        else twinTiles = swapTiles(len * len - 2, len * len - 1);

        return new Board(twinTiles);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        In in = new In("puzzle3x3-04.txt");
        int len = in.readInt();
        int[][] tiles = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board board = new Board(tiles);

        // test string
        StdOut.println(board);
    }
}
