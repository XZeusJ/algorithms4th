package ch12_Data_Abstraction;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Transaction {
    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public String who() {
        return who;
    }

    public Date when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    public String toString() {
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }

    public static void main(String[] args) {
        String who = "xzj";
        Date when = new Date(4, 22, 1994);
        int amount = 3000;
        Transaction xzj = new Transaction(who, when, amount);
        StdOut.println(xzj);
    }
}
