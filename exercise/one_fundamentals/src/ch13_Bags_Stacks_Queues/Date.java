package ch13_Bags_Stacks_Queues;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

// ex16
public class Date {
    private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private final int month;
    private final int day;
    private final int year;

    public Date(int m, int d, int y) {
        month = m;
        day = d;
        year = y;
    }

    public Date(String date) {
        String[] fields = date.split("/");
        if (fields.length != 3) { throw new IllegalArgumentException("Invalid date"); }
        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
        if (!isValid(month, day, year)) { throw new IllegalArgumentException("Invalid date"); }
    }

    // is the given date valid?
    private static boolean isValid(int m, int d, int y) {
        if (m < 1 || m > 12)      return false;
        if (d < 1 || d > DAYS[m]) return false;
        if (m == 2 && d == 29 && !isLeapYear(y)) return false;
        return true;
    }

    // is y a leap year?
    private static boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
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

    public boolean equals(Object x) {
        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Date that = (Date) x;
        if (this.day != that.day) return false;
        if (this.month != that.month) return false;
        if (this.year != that.year) return false;
        return true;
    }

    public static Date[] readDates(String name) {
        In in = new In(name);
        Queue<Date> q = new Queue<Date>();
        while (!in.isEmpty()) {
            String s = in.readString();
            q.enqueue(new Date(s));
        }

        int N = q.size();
        Date[] a = new Date[N];
        for (int i = 0; i < N; i++) {
            a[i] = q.dequeue();
        }
        return a;
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
