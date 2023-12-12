package edu.princeton.cs.algs4.section_1_5.union_find;

import edu.princeton.cs.algs4.in_out.In;
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
    public static void main(String[] args) throws Exception {
//        int n = StdIn.readInt();


        In in = new In();
        In input = new In(in.getFileFromResources("mediumUF.txt"));
        String[] lines = input.readAllLines();
        int n = Integer.parseInt(lines[0]);

//        QuickFindUF uf = new QuickFindUF(n);
//        QuickUnionUF uf = new QuickUnionUF(n);
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
//        UnionFindUF uf = new UnionFindUF(n);

        for (int i = 1; i < lines.length; i++) {
            String[] line = lines[i].split(" ");
            int p = Integer.parseInt(line[0]);
            int q = Integer.parseInt(line[1]);

            try {
                if (uf.find(p) == uf.find(q)) continue;
                uf.union(p, q);
                System.out.println(p + " " + q);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
//        System.out.println(uf.count() + " components");

//        while (!StdIn.isEmpty()) {
//            int p = StdIn.readInt();
//            int q = StdIn.readInt();
//
//            try {
//                if (uf.find(p) == uf.find(q)) continue;
//                uf.union(p, q);
//                StdOut.println(p + " " + q);
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        StdOut.println(uf.count() + " components");
    }
}
