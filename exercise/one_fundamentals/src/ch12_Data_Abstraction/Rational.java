package ch12_Data_Abstraction;

import edu.princeton.cs.algs4.StdOut;

public class Rational {
    private static Rational zero = new Rational(0, 1);

    private long num;
    private long den;

    public Rational(long numerator, long denominator) {
        if (Double.isInfinite(numerator) || Double.isInfinite(denominator))
            throw new IllegalArgumentException("Coordinates must be finite");
        if (Double.isNaN(numerator) || Double.isNaN(denominator))
            throw new IllegalArgumentException("Coordinates cannot be NaN");
        // deal with x/0
        if (denominator == 0) {
            throw new ArithmeticException("denominator is zero");
        }

        // reduce fraction
        long g = gcd(numerator, denominator);
        num = numerator / g;
        den = denominator / g;
    }

    // return gcd(|m|, |n|)
    private static long gcd(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if (0 == n) return m;
        else return gcd(n, m % n);
    }

    // sum of this number and b
    public Rational plus(Rational that) {
        // special cases
        if (this.compareTo(zero) == 0) return that;
        if (that.compareTo(zero) == 0) return this;

        // Find gcd of numerators and denominators
        long f = gcd(this.num, that.num);
        long g = gcd(this.den, that.den);
        // add cross-product terms for numerator
        Rational s = new Rational((this.num / f) * (that.den / g)
                + (that.num / f) * (this.den / g),
                this.den * (that.den / g));

        // multiply back in
        s.num *= f;
        return s;
    }

    // return -this
    public Rational negate() {
        return new Rational(-num, den);
    }

    // return |this|
    public Rational abs() {
        if (num >= 0) return this;
        else return negate();
    }

    // return this - that
    public Rational minus(Rational that) {
        return this.plus(that.negate());
    }


    public  Rational times(Rational that) {
        // reduce p1/q2 and p2/q1, then multiply, where a = p1/q1 and b = p2/q2
        Rational c = new Rational(this.num, that.den);
        Rational d = new Rational(that.num, this.den);
        return new Rational(c.num * d.num, c.den * d.den);
    }

    public Rational reciprocal() { return new Rational(den, num);  }

    // return this / that
    public Rational dividedBy(Rational that) {
        return this.times(that.reciprocal());
    }

    // return { -1, 0, +1 } if this < that, this = that, or this > that
    public int compareTo(Rational that) {
        long lhs = this.num * that.den;
        long rhs = this.den * that.num;
        if (lhs < rhs) return -1;
        if (lhs > rhs) return +1;
        return 0;
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Rational that = (Rational) other;
        return this.compareTo(that) == 0;
    }

    public String toString() {
        if (den == 1) return num + "";
        else  return num + "/" + den;
    }

    // test client
    public static void main(String[] args) {
        Rational x, y, z;

        // 1/2 + 1/3 = 5/6
        x = new Rational(1, 2);
        y = new Rational(1, 3);
        z = x.plus(y);
        StdOut.println(z);

        // 8/9 + 1/9 = 1
        x = new Rational(8, 9);
        y = new Rational(1, 9);
        z = x.plus(y);
        StdOut.println(z);

        // 1/200000000 + 1/300000000 = 1/120000000
        x = new Rational(1, 200000000);
        y = new Rational(1, 300000000);
        z = x.plus(y);
        StdOut.println(z);

        // 1073741789/20 + 1073741789/30 = 1073741789/12
        x = new Rational(1073741789, 20);
        y = new Rational(1073741789, 30);
        z = x.plus(y);
        StdOut.println(z);

        //  4/17 * 17/4 = 1
        x = new Rational(4, 17);
        y = new Rational(17, 4);
        z = x.times(y);
        StdOut.println(z);

        // 3037141/3247033 * 3037547/3246599 = 841/961
        x = new Rational(3037141, 3247033);
        y = new Rational(3037547, 3246599);
        z = x.times(y);
        StdOut.println(z);

        // 1/6 - -4/-8 = -1/3
        x = new Rational(1, 6);
        y = new Rational(-4, -8);
        z = x.minus(y);
        StdOut.println(z);
    }
}
