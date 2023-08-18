package edu.princeton.cs.algs4.section_1_3;

import edu.princeton.cs.algs4.in_out.In;
import edu.princeton.cs.algs4.in_out.StdOut;

public class Evaluate {

   public static void main(String[] args) throws Exception {
      In getFileName = new In();
      In in = new In(getFileName.getFileFromResources("evaluate.txt"));
      String[] read = in.readAllLines();

      Stack<String> ops = new Stack<>();
      Stack<Double> vals = new Stack<>();

      for (String s : read) {
         if (s.equals("(")) ;
         else if (s.equals("+")) ops.push(s);
         else if (s.equals("-")) ops.push(s);
         else if (s.equals("*")) ops.push(s);
         else if (s.equals("/")) ops.push(s);
         else if (s.equals("sqrt")) ops.push(s);
         else if (s.equals(")")) { // Pop, evaluate, and push result if token is ")".
            String op = ops.pop();
            double v = vals.pop();
            if (op.equals("+")) v = vals.pop() + v;
            else if (op.equals("-")) v = vals.pop() - v;
            else if (op.equals("*")) v = vals.pop() * v;
            else if (op.equals("/")) v = vals.pop() / v;
            else if (op.equals("sqrt")) v = Math.sqrt(v);
            vals.push(v);
         } // Token not operator or paren: push double value.
         else vals.push(Double.parseDouble(s));
      }
      StdOut.println(vals.pop());
   }
}
