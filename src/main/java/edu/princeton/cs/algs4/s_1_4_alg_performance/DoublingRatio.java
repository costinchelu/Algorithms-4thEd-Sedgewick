package edu.princeton.cs.algs4.s_1_4_alg_performance;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.in_out.StdOut;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The {@code DoublingRatio} class provides a client for measuring
 * the running time of a method using a doubling ratio test.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/14analysis">Section 1.4</a>
 * of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DoublingRatio {

    private static final int MAXIMUM_INTEGER = 1000000;

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
     * random 6-digit integers.
     *
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()}
     * with <em>n</em> random 6-digit integers
     */
    public static double timeTrial(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
        Stopwatch timer = new Stopwatch();
        int ignore = ThreeSum.count(a);
        return timer.elapsedTime();
    }

    /**
     * Prints table of running times to call {@code ThreeSum.count()}
     * for arrays of size 250, 500, 1000, 2000, and so forth, along
     * with ratios of running times between successive array sizes.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        double prev = timeTrial(125);
        for (int n = 250; true; n += n) {
            double time = timeTrial(n);
            StdOut.printf("%7d %7.1f %5.1f\n", n, time, time / prev);
            prev = time;
        }
    }
}