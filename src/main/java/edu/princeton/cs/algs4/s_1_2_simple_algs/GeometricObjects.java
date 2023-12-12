package edu.princeton.cs.algs4.s_1_2_simple_algs;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.geometric_obj.Interval1D;
import edu.princeton.cs.algs4.geometric_obj.Interval2D;
import edu.princeton.cs.algs4.geometric_obj.Point2D;
import edu.princeton.cs.algs4.in_out.StdOut;

public class GeometricObjects {

   public static void main(String[] args) {
      double xlo = 0.2;
      double xhi = 0.5;
      double ylo = 0.5;
      double yhi = 0.6;
      int T = 1000;

      Interval1D xInt = new Interval1D(xlo, xhi);
      Interval1D yInt = new Interval1D(ylo, yhi);
      Interval2D box = new Interval2D(xInt, yInt);
      box.draw();
      Counter c = new Counter("hits");
      for (int t = 0; t < T; t++) {
         double x = StdRandom.random();
         double y = StdRandom.random();
         Point2D p = new Point2D(x, y);
         if (box.contains(p))
            c.increment();
         else
            p.draw();
      }
      StdOut.println(c);
      StdOut.printf("area = %.2f\n", box.area());
   }
}
