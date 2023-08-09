package edu.princeton.cs.algs4.section_1_2;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 *  The {@code Counter} class is a mutable data type to encapsulate a counter.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
@RequiredArgsConstructor
@ToString
public class Counter implements Comparable<Counter> {

    private final String name;     // counter name

    private int count = 0;         // current value

    /**
     * Increments the counter by 1.
     */
    public void increment() {
        count++;
    }

    /**
     * Returns the current value of this counter.
     *
     * @return the current value of this counter
     */
    public int tally() {
        return count;
    }

    /**
     * Compares this counter to the specified counter.
     *
     * @param  that the other counter
     * @return {@code 0} if the value of this counter equals
     *         the value of that counter; a negative integer if
     *         the value of this counter is less than the value of
     *         that counter; and a positive integer if the value
     *         of this counter is greater than the value of that
     *         counter
     */
    @Override
    public int compareTo(Counter that) {
        return Integer.compare(this.count, that.count);
    }
}