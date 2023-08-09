package edu.princeton.cs.algs4.union_find;

import edu.princeton.cs.algs4.in_out.StdIn;
import edu.princeton.cs.algs4.in_out.StdOut;

public class TestUF {

    /**
     * Reads an integer {@code n} and a sequence of pairs of integers
     * (between {@code 0} and {@code n-1}) from standard input, where each integer
     * in the pair represents some element;
     * if the elements are in different sets, merge the two sets
     * and print the pair to standard output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();

//        QuickFindUF uf = new QuickFindUF(n);
//        QuickUnionUF uf = new QuickUnionUF(n);
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
//        UnionFindUF uf = new UnionFindUF(n);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            try {
                if (uf.find(p) == uf.find(q)) continue;
                uf.union(p, q);
                StdOut.println(p + " " + q);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        StdOut.println(uf.count() + " components");
    }
}
