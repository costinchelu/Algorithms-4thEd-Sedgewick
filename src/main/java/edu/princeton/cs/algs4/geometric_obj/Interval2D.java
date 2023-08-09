package edu.princeton.cs.algs4.geometric_obj;

import edu.princeton.cs.algs4.in_out.StdDraw;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 *  The {@code Interval2D} class represents a closed two-dimensional interval,
 *  which represents all points (x, y) with both {@code xmin <= x <= xmax} and
 *  {@code ymin <= y <= ymax}.
 *  Two-dimensional intervals are immutable: their values cannot be changed
 *  after they are created.
 *  The class {@code Interval2D} includes methods for checking whether
 *  a two-dimensional interval contains a point and determining whether
 *  two two-dimensional intervals intersect.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/12oop">Section 1.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class Interval2D {

    private final Interval1D x;

    private final Interval1D y;

    /**
     * Does this two-dimensional interval intersect that two-dimensional interval?
     * @param that the other two-dimensional interval
     * @return true if this two-dimensional interval intersects
     *    that two-dimensional interval; false otherwise
     */
    public boolean intersects(Interval2D that) {
        if (!this.x.intersects(that.x)) return false;
        if (!this.y.intersects(that.y)) return false;
        return true;
    }

    /**
     * Does this two-dimensional interval contain the point p?
     * @param p the two-dimensional point
     * @return true if this two-dimensional interval contains the point p; false otherwise
     */
    public boolean contains(Point2D p) {
        return x.contains(p.getX())  && y.contains(p.getY());
    }

    /**
     * Returns the area of this two-dimensional interval.
     * @return the area of this two-dimensional interval
     */
    public double area() {
        return x.length() * y.length();
    }

    /**
     * Draws this two-dimensional interval to standard draw.
     */
    public void draw() {
        double xc = (x.getMin() + x.getMax()) / 2.0;
        double yc = (y.getMin() + y.getMax()) / 2.0;
        StdDraw.rectangle(xc, yc, x.length() / 2.0, y.length() / 2.0);
    }
}
