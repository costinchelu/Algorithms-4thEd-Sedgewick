package edu.princeton.cs.algs4.s_1_2_simple_algs;

import edu.princeton.cs.algs4.in_out.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class CounterDemo {

    /**
     * Reads two command-line integers n and trials; creates n counters;
     * increments trials counters at random; and prints results.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = 10;
        int trials = 100;

        // create n counters
        Counter[] hits = new Counter[n];
        for (int i = 0; i < n; i++) {
            hits[i] = new Counter("counter" + i);
        }

        // increment trials counters at random
        for (int t = 0; t < trials; t++) {
            hits[StdRandom.uniformInt(n)].increment();
        }

        // print results
        for (int i = 0; i < n; i++) {
            StdOut.println(hits[i]);
        }
    }
}
