package edu.princeton.cs.algs4.s_1_4_alg_performance;

import edu.princeton.cs.algs4.in_out.In;
import edu.princeton.cs.algs4.in_out.StdOut;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The {@code TwoSum} class provides static methods for counting and printing
 * the number of doubles in an array of integers that sum to 0 (ignoring integer
 * overflow).
 * <p>
 * This implementation uses a double nested loop and takes proportional to n^2,
 * where n is the number of integers.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/14analysis">Section 1.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TwoSum {

   /**
    * Prints to standard output the (i, j) with {@code i < j} such that
    * {@code a[i] + a[j] == 0}.
    *
    * @param a
    *           the array of integers
    */
   public static void printAll(int[] a) {
      int n = a.length;
      for (int i = 0; i < n; i++) {
         for (int j = i + 1; j < n; j++) {
            if (a[i] + a[j] == 0) {
               StdOut.println(a[i] + " " + a[j]);
            }
         }
      }
   }

   /**
    * Returns the number of doubles (i, j) with {@code i < j} such that
    * {@code a[i] + a[j] == 0}.
    * <p>
    * Algorithm complexity: {@code O(n^2)}
    *
    * @param a
    *           the array of integers
    * @return the number of doubles (i, j) with {@code i < j} such that
    *         {@code a[i] + a[j] == 0}
    */
   public static int count(int[] a) {
      int n = a.length;
      int count = 0;
      for (int i = 0; i < n; i++) {
         for (int j = i + 1; j < n; j++) {
            if (a[i] + a[j] == 0) {
               count++;
            }

         }
      }
      return count;
   }

   /**
    * Reads in a sequence of integers from a file, specified as a command-line
    * argument; counts the number of doubles sum to exactly zero; prints out the
    * time to perform the computation.
    *
    * @param args
    *           the command-line arguments
    */
   public static void main(String[] args) {
      In in = new In(args[0]);
      int[] a = in.readAllInts();

      Stopwatch timer = new Stopwatch();
      int count = count(a);
      StdOut.println("elapsed time = " + timer.elapsedTime());
      StdOut.println(count);
   }
}
