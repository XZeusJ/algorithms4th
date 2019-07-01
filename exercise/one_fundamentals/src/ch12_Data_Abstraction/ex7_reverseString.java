package ch12_Data_Abstraction;

public class ex7_reverseString {
    // reverse the string
    public static String reverse(String s) {
        int N = s.length();
        if (N <= 1) return s;
        String a = s.substring(0, N/2);
        String b = s.substring(N/2, N);
        return reverse(b) + reverse(a);
    }
}
