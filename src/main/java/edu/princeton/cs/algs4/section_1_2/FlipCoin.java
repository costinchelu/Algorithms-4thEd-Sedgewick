package edu.princeton.cs.algs4.section_1_2;

import edu.princeton.cs.algs4.StdRandom;

public class FlipCoin {

    public static void main(String[] args) {
        flipACoin(1_000_000);
    }

    public static void flipACoin(int times) {
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");

        for (int i = 0; i < times; i++) {
            if (StdRandom.bernoulli(0.5)) heads.increment();
            else tails.increment();
        }

        System.out.println(heads);
        System.out.println(tails);

        int d = heads.tally() - tails.tally();
        System.out.println("delta: " + Math.abs(d));
    }
}
