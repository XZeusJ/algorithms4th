package ch13_Bags_Stacks_Queues;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// ex1.3.9
public class CompleteParentheses {
    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static void main(String[] args) {
        String str = "1+2)*3-4)*5-6)))";

        Stack<String> ops = new Stack<String>();
        Stack<String> vals = new Stack<String>();

        for (int i = 0; i < str.length(); i++) {
            // deal with vals
            char c = str.charAt(i);
            if (isDigit(c)) {
                vals.push(String.valueOf(c));
            }
            // deal with operator
            else if (isOperator(c)) {
                ops.push(String.valueOf(c));
            }
            // deal with right-parentheses
            else {
                String val2 = vals.pop();
                String val1 = vals.pop();
                String op = ops.pop();
                String exp = "(" + val1 + op + val2 + ")";
                vals.push(exp);
            }
        }

        String cplStr = vals.pop();
        StdOut.println(cplStr);
    }


}
