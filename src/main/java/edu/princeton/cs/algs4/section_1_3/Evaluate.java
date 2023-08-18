package edu.princeton.cs.algs4.section_1_3;

import edu.princeton.cs.algs4.in_out.In;
import edu.princeton.cs.algs4.in_out.StdOut;

/**
 <b>Dijkstra’s two-Stack Algorithm for expression evaluation</b>
 <p></p>
 Evaluate math expressions like:
<p>
 ( ( ( 2 + 3 ) * ( 4 * 5 ) ) + ( sqrt (5.0) / 1.5))
 <p>
 Only working with one operation per bracket
 <br>
 The whole expression should be enclosed into another bracket
 <p>
 Cannot use spaces or other chars than numeric or ()+-*. slash and sqrt
</p>
<p>
 This code is a
 simple example of an interpreter: a program that interprets the computation specified
 by a given string and performs the computation to arrive at the result.
 </p>
 */
public class Evaluate {

   public static void main(String[] args) throws Exception {
      In getFileName = new In();
      In in = new In(getFileName.getFileFromResources("evaluate.txt"));
      String[] read = in.readAllLines();

      Stack<String> ops = new Stack<>();
      Stack<Double> values = new Stack<>();

      for (String s : read) {
         if (s.equals("(")) ; // do nothing
         else if (s.equals("+"))    ops.push(s);
         else if (s.equals("-"))    ops.push(s);
         else if (s.equals("*"))    ops.push(s);
         else if (s.equals("/"))    ops.push(s);
         else if (s.equals("sqrt")) ops.push(s);
         else if (s.equals(")")) {
            String op = ops.pop();
            double v = values.pop();
            if (op.equals("+"))         v = values.pop() + v;
            else if (op.equals("-"))    v = values.pop() - v;
            else if (op.equals("*"))    v = values.pop() * v;
            else if (op.equals("/"))    v = values.pop() / v;
            else if (op.equals("sqrt")) v = Math.sqrt(v);

            values.push(v);
         }
         else values.push(Double.parseDouble(s));
      }

      StdOut.println(values.pop());
   }
}
