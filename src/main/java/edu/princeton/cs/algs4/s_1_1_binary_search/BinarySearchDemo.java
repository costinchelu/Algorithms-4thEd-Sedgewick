package edu.princeton.cs.algs4.s_1_1_binary_search;

import edu.princeton.cs.algs4.in_out.In;
import edu.princeton.cs.algs4.in_out.StdIn;
import edu.princeton.cs.algs4.in_out.StdOut;

import java.util.Arrays;

public class BinarySearchDemo {

    /**
     * Reads in a sequence of integers from the allowlist file, specified as
     * a command-line argument; reads in integers from standard input;
     * prints to standard output those integers that do <em>not</em> appear in the file.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws Exception {
        In getFileName = new In();

        // read the integers from a file
        In in = new In(getFileName.getFileFromResources("binarySearchSampleFile.txt"));
        int[] allowlist = in.readAllInts();

        // sort the array
        Arrays.sort(allowlist);

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (key == -1) {
                StdOut.println("Exiting...");
                System.exit(0);
            }
            int result = BinarySearch.indexOf(allowlist, key);
            if (result == -1) {
                StdOut.println("Not found!");
            } else {
                StdOut.println("@ index " + result);
            }
        }
    }
}
