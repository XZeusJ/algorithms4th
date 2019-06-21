package basic_programming_model;

import edu.princeton.cs.algs4.StdOut;

public class ex35 {
    private static int SIDES = 6;

    private static double[] stdProb(){
        double[] dist = new double[2 * SIDES + 1];
        for (int i = 1; i <= SIDES; i++)
            for (int j = 1; j <= SIDES; j++)
                dist[i + j] += 1.0;

        dist = genProb(dist,36);
        return dist;
    }

    private static void show(double[] dist){
        for (int i = 0; i < dist.length; i++) {
            StdOut.println(i + "==>" + dist[i]);
        }
        StdOut.println();
    }


    private static double[] genProb(double[] a, int N){
        for (int i = 2; i <= 2*SIDES; i++) {
            a[i] /= N;
        }
        return a;
    }

    private static boolean isEqual(double[] prob, double[] stdprob){
        for (int i = 0; i < prob.length; i++) {
//            StdOut.println(i+" diff is:"+ Math.abs(prob[i] - stdprob[i]));
            if (Math.abs(prob[i] - stdprob[i]) > 1.0E-1)
                return false;
        }
        return true;
    }

    private static double[] doTrial(double[] a){
        int firDie = (int)(Math.random()*SIDES);
        int secDie = (int)(Math.random()*SIDES);
        a[firDie+secDie] += 1;
        return a;
    }

    public static void main(String[] args) {
        double[] stdprob = stdProb();

        double[] record = new double[2*SIDES+1];
        double[] prob = new double[2*SIDES+1];

        int N = 1;
        int cut = 0;
        while (!isEqual(prob, stdprob) && cut < 3){
            record = doTrial(record);
            show(record);
            prob = genProb(record, N);
            N++;
            cut++;
        }

//        while (N <= 2){
//            record = doTrial(record);
//            prob = genProb(record, N);
////            show(record);
////            show(prob);
//            N++;
//        }

        StdOut.println("use "+N+" times to get the equal prob!");
    }
}
