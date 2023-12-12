package edu.princeton.cs.algs4.s_1_2_simple_algs;

import edu.princeton.cs.algs4.StdRandom;

public class DiceRolls {

    public static void main(String[] args) {
        rolls(1_000_000);
    }

    public static void rolls(int noOfThrows) {
        int SIDES = 6;

        Counter[] rolls = new Counter[SIDES + 1];

        for (int i = 1; i <= SIDES; i++) {
            rolls[i] = new Counter(i + "'s");
        }

        for (int t = 0; t < noOfThrows; t++) {
            int result = StdRandom.uniform(1, SIDES + 1);
            rolls[result].increment();
        }

        for (int i = 1; i <= SIDES; i++) {
            System.out.println(rolls[i]);
        }
    }
}
