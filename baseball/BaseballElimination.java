/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class BaseballElimination {
    private final int N;
    private final int[] wins, losses, remaining;
    private final int[][] against;
    private final ArrayList<String> teams;
    private ArrayList<String>[] coe;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In in = new In(filename);
        N = in.readInt();
        teams = new ArrayList<>(N);
        wins = new int[N];
        losses = new int[N];
        remaining = new int[N];
        against = new int[N][N];
        coe = (ArrayList<String>[]) new ArrayList[N]; // for store team

        byte[] opponents = new byte[N];
        int best = 0, winner = 0; // compute current leader team for trivial elimination
        for (int i = 0; i < N; i++) {
            teams.add(in.readString());
            wins[i] = in.readInt();
            losses[i] = in.readInt();
            remaining[i] = in.readInt();
            for (int j = 0; j < N; j++) {
                against[i][j] = in.readInt();
                if (j > i && against[i][j] > 0) {
                    opponents[i]++;
                    opponents[j]++;
                }
            }
            if (wins[i] > best) {
                best = wins[i];
                winner = i;
            }
        }

        calcElimination(opponents, best, winner);
    }

    private void calcElimination(byte[] opponents, int best, int winner) {
        // compute total matches left
        int matches = 0;
        for (int i = 0; i < N; i++) matches += opponents[i];
        matches /= 2;

        for (int i = 0; i < N; i++) {
            // handle trivial elimination
            int ideal = wins[i] + remaining[i];
            if (ideal < best) {
                coe[i] = new ArrayList<String>(N - 1);
                coe[i].add(teams.get(winner));
                continue;
            }

            // handle Nontrivial elimination
            // we need 3 process
            // 1. build flow-network
            // 2. use ford-fulkerson algorithm to compute max flow
            // 3. if max flow == all games, the team is not eliminated

            int cMatches = matches
                    - opponents[i]; // to build flow-network, we first compute vertices
            FlowNetwork fn = new FlowNetwork(cMatches + N + 2);
            int fullFlow = 0, round = 1;
            // map each vertix to Integer and build the flow network
            for (int m = 0; m < N; m++) {
                for (int n = m + 1; n < N; n++) {
                    if (m == i || n == i || against[m][n] == 0) continue;
                    fullFlow += against[m][n]; // flow capacity from source
                    fn.addEdge(new FlowEdge(0, round, against[m][n]));
                    fn.addEdge(new FlowEdge(round, cMatches + m + 1, Double.POSITIVE_INFINITY));
                    fn.addEdge(new FlowEdge(round, cMatches + n + 1, Double.POSITIVE_INFINITY));
                    round++;
                }
            }
            for (int j = 0; j < N; j++) {
                if (j == i) continue;
                fn.addEdge(new FlowEdge(cMatches + j + 1, cMatches + N + 1, ideal - wins[j]));
            }
            FordFulkerson ff = new FordFulkerson(fn, 0, cMatches + N + 1);
            if ((int) ff.value() == fullFlow) continue;
            coe[i] = new ArrayList<String>(N - 1);
            for (int j = 0; j < N; j++) {
                if (ff.inCut(cMatches + j + 1)) coe[i].add(teams.get(j));
            }

        }
    }

    // number of teams
    public int numberOfTeams() {
        return teams.size();
    }

    // all teams
    public Iterable<String> teams() {
        return teams;
    }

    // number of wins for given team
    public int wins(String team) {
        if (teams.indexOf(team) < 0) throw new IllegalArgumentException();
        return wins[teams.indexOf(team)];
    }

    // number of losses for given team
    public int losses(String team) {
        if (teams.indexOf(team) < 0) throw new IllegalArgumentException();
        return losses[teams.indexOf(team)];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        if (teams.indexOf(team) < 0) throw new IllegalArgumentException();
        return remaining[teams.indexOf(team)];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if (teams.indexOf(team1) < 0) throw new IllegalArgumentException();
        if (teams.indexOf(team2) < 0) throw new IllegalArgumentException();
        return against[teams.indexOf(team1)][teams.indexOf(team2)];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        if (teams.indexOf(team) < 0) throw new IllegalArgumentException();
        return coe[teams.indexOf(team)] != null;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (teams.indexOf(team) < 0) throw new IllegalArgumentException();
        return coe[teams.indexOf(team)];
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
