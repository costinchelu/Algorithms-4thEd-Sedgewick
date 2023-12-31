package edu.princeton.cs.algs4.geometric_obj;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;

/**
 *  The {@code Interval1D} class represents a one-dimensional interval.
 *  The interval is <em>closed</em>—it contains both endpoints.
 *  Intervals are immutable: their values cannot be changed after they are created.
 *  The class {@code Interval1D} includes methods for checking whether
 *  an interval contains a point and determining whether two intervals intersect.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
@Getter
@EqualsAndHashCode
@ToString
public class Interval1D {

    /**
     * Compares two intervals by min endpoint.
     */
    public static final Comparator<Interval1D> MIN_ENDPOINT_ORDER  = new MinEndpointComparator();

    /**
     * Compares two intervals by max endpoint.
     */
    public static final Comparator<Interval1D> MAX_ENDPOINT_ORDER = new MaxEndpointComparator();

    /**
     * Compares two intervals by length.
     */
    public static final Comparator<Interval1D> LENGTH_ORDER = new LengthComparator();

    /**
     * Returns the min endpoint of this interval.
     */
    private final double min;

    /**
     * Returns the max endpoint of this interval.
     */
    private final double max;

    /**
     * Initializes a closed interval [min, max].
     *
     * @param  min the smaller endpoint
     * @param  max the larger endpoint
     * @throws IllegalArgumentException if the min endpoint is greater than the max endpoint
     * @throws IllegalArgumentException if either {@code min} or {@code max}
     *         is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY} or
     *         {@code Double.NEGATIVE_INFINITY}

     */
    public Interval1D(double min, double max) {
        if (Double.isInfinite(min) || Double.isInfinite(max))
            throw new IllegalArgumentException("Endpoints must be finite");
        if (Double.isNaN(min) || Double.isNaN(max))
            throw new IllegalArgumentException("Endpoints cannot be NaN");

        // convert -0.0 to +0.0
        if (min == 0.0) min = 0.0;
        if (max == 0.0) max = 0.0;

        if (min <= max) {
            this.min = min;
            this.max = max;
        }
        else throw new IllegalArgumentException("Illegal interval");
    }

    /**
     * Returns true if this interval intersects the specified interval.
     *
     * @param  that the other interval
     * @return {@code true} if this interval intersects the argument interval;
     *         {@code false} otherwise
     */
    public boolean intersects(Interval1D that) {
        if (this.max < that.min) return false;
        if (that.max < this.min) return false;
        return true;
    }

    /**
     * Returns true if this interval contains the specified value.
     *
     * @param x the value
     * @return {@code true} if this interval contains the value {@code x};
     *         {@code false} otherwise
     */
    public boolean contains(double x) {
        return (min <= x) && (x <= max);
    }

    /**
     * Returns the length of this interval.
     *
     * @return the length of this interval (max - min)
     */
    public double length() {
        return max - min;
    }

    /**
     * Returns the left endpoint of this interval.
     *
     * @return the left endpoint of this interval
     * @deprecated Replaced by {@link #min()}.
     */
    @Deprecated
    public double left() {
        return min;
    }

    /**
     * Returns the right endpoint of this interval.
     * @return the right endpoint of this interval
     * @deprecated Replaced by {@link #max()}.
     */
    @Deprecated
    public double right() {
        return max;
    }

    // ascending order of min endpoint, breaking ties by max endpoint
    private static class MinEndpointComparator implements Comparator<Interval1D> {
        public int compare(Interval1D a, Interval1D b) {
            if      (a.min < b.min) return -1;
            else if (a.min > b.min) return +1;
            else if (a.max < b.max) return -1;
            else if (a.max > b.max) return +1;
            else                    return  0;
        }
    }

    // ascending order of max endpoint, breaking ties by min endpoint
    private static class MaxEndpointComparator implements Comparator<Interval1D> {
        public int compare(Interval1D a, Interval1D b) {
            if      (a.max < b.max) return -1;
            else if (a.max > b.max) return +1;
            else if (a.min < b.min) return -1;
            else if (a.min > b.min) return +1;
            else                    return  0;
        }
    }

    // ascending order of length
    private static class LengthComparator implements Comparator<Interval1D> {
        public int compare(Interval1D a, Interval1D b) {
            double alen = a.length();
            double blen = b.length();
            if      (alen < blen) return -1;
            else if (alen > blen) return +1;
            else                  return  0;
        }
    }
}