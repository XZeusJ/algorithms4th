/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private final boolean solvable;
    private final int moves;
    private final SearchNode current;

    private class SearchNode implements Comparable<SearchNode> {
        Board board;
        SearchNode previous;
        int moves;
        int priority; // optimization for caching the Manhattan priorites

        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            // OPTIMIZATION: caching the manhattan priorites
            this.priority = this.moves + this.board.manhattan();
        }

        @Override
        public int compareTo(SearchNode that) {
            if (this.priority == that.priority)
                return this.board.manhattan() - this.board.manhattan();
            else
                return this.priority - that.priority;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> pqTwin = new MinPQ<>(); // Detecting unsolvable boards.

        // insert the "initial search node" into a priority queue.
        pq.insert(new SearchNode(initial, 0, null));
        pqTwin.insert(new SearchNode(initial.twin(), 0, null));

        SearchNode node, nodeTwin;

        // repeat this procedure until the "search node dequeued" corresponds to the goal board.
        while (!(pq.min().board.isGoal() || pqTwin.min().board.isGoal())) {

            // delete "the search node" with min priority
            node = pq.delMin();
            // insert onto the priority queue all "neighboring search nodes"
            for (Board neighbor : node.board.neighbors()) {
                // OPTIMIZATION: don't enqueue a neighbor if its board is the same as the board of the previous search node.
                if (node.previous == null || !neighbor.equals(node.previous.board))
                    pq.insert(new SearchNode(neighbor, node.moves + 1, node));
            }

            nodeTwin = pqTwin.delMin();
            for (Board neighbor : nodeTwin.board.neighbors()) {
                if (nodeTwin.previous == null || !neighbor.equals(nodeTwin.previous.board))
                    pqTwin.insert(new SearchNode(neighbor, nodeTwin.moves + 1, nodeTwin));
            }
        }

        // pq.min() stores the final result
        solvable = pq.min().board.isGoal();
        current = pq.min();
        moves = pq.min().moves;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (!isSolvable()) return -1;
        else return moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> boards = new Stack<>();
        SearchNode node = current;

        while (node != null) {
            boards.push(node.board);
            node = node.previous;
        }

        return boards;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        // In in = new In(args[0]);
        In in = new In("puzzle2x2-unsolvable1.txt");
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
