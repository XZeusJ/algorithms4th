package ch12_Data_Abstraction;

import edu.princeton.cs.algs4.StdOut;

public class Date {
    private final int month;
    private final int day;
    private final int year;

    public Date(int m, int d, int y) {
        month = m;
        day = d;
        year = y;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    public String toString() {
        return month() + "/" + day() + "/" + year();
    }

    public static void main(String[] args) {
        int m;
        int d;
        int y;
        if (args.length != 0) {
            m = Integer.parseInt(args[0]);
            d = Integer.parseInt(args[1]);
            y = Integer.parseInt(args[2]);
        } else {
            m = 04; d = 22; y = 1994;
        }
        Date date = new Date(m, d, y);
        StdOut.println(date);
    }
}
