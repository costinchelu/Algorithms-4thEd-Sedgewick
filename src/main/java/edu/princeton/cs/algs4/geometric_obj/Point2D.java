package edu.princeton.cs.algs4.geometric_obj;

import edu.princeton.cs.algs4.in_out.StdDraw;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;


/**
 *  The {@code Point} class is an immutable data type to encapsulate a
 *  two-dimensional point with real-value coordinates.
 *  <p>
 *  Note: in order to deal with the difference behavior of double and
 *  Double with respect to -0.0 and +0.0, the Point2D constructor converts
 *  any coordinates that are -0.0 to +0.0.
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
public final class Point2D implements Comparable<Point2D> {

    /**
     * Compares two points by x-coordinate.
     */
    public static final Comparator<Point2D> X_ORDER = new XOrder();

    /**
     * Compares two points by y-coordinate.
     */
    public static final Comparator<Point2D> Y_ORDER = new YOrder();

    /**
     * Compares two points by polar radius.
     */
    public static final Comparator<Point2D> R_ORDER = new ROrder();

    private final double x;    // x coordinate

    private final double y;    // y coordinate

    /**
     * Initializes a new point (x, y).
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @throws IllegalArgumentException if either {@code x} or {@code y}
     *    is {@code Double.NaN}, {@code Double.POSITIVE_INFINITY} or
     *    {@code Double.NEGATIVE_INFINITY}
     */
    public Point2D(double x, double y) {
        if (Double.isInfinite(x) || Double.isInfinite(y))
            throw new IllegalArgumentException("Coordinates must be finite");
        if (Double.isNaN(x) || Double.isNaN(y))
            throw new IllegalArgumentException("Coordinates cannot be NaN");
        if (x == 0.0) this.x = 0.0;  // convert -0.0 to +0.0
        else          this.x = x;

        if (y == 0.0) this.y = 0.0;  // convert -0.0 to +0.0
        else          this.y = y;
    }

    /**
     * Returns the polar radius of this point.
     * @return the polar radius of this point in polar coordinates: sqrt(x*x + y*y)
     */
    public double r() {
        return Math.sqrt(x*x + y*y);
    }

    /**
     * Returns the angle of this point in polar coordinates.
     * @return the angle (in radians) of this point in polar coordinates (between –&pi; and &pi;)
     */
    public double theta() {
        return Math.atan2(y, x);
    }

    /**
     * Returns the angle between this point and that point.
     * @return the angle in radians (between –&pi; and &pi;) between this point and that point (0 if equal)
     */
    private double angleTo(Point2D that) {
        double dx = that.x - this.x;
        double dy = that.y - this.y;
        return Math.atan2(dy, dx);
    }

    /**
     * Returns true if a→b→c is a counterclockwise turn.
     * @param a first point
     * @param b second point
     * @param c third point
     * @return { -1, 0, +1 } if a→b→c is a { clockwise, collinear; counterclockwise } turn.
     */
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
        if      (area2 < 0) return -1;
        else if (area2 > 0) return +1;
        else                return  0;
    }

    /**
     * Returns twice the signed area of the triangle a-b-c.
     * @param a first point
     * @param b second point
     * @param c third point
     * @return twice the signed area of the triangle a-b-c
     */
    public static double area2(Point2D a, Point2D b, Point2D c) {
        return (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
    }

    /**
     * Returns the Euclidean distance between this point and that point.
     * @param that the other point
     * @return the Euclidean distance between this point and that point
     */
    public double distanceTo(Point2D that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * Returns the square of the Euclidean distance between this point and that point.
     * @param that the other point
     * @return the square of the Euclidean distance between this point and that point
     */
    public double distanceSquaredTo(Point2D that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return dx*dx + dy*dy;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point (x1, y1)
     * if and only if either {@code y0 < y1} or if {@code y0 == y1} and {@code x0 < x1}.
     *
     * @param  that the other point
     * @return the value {@code 0} if this string is equal to the argument
     *         string (precisely when {@code equals()} returns {@code true});
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point2D that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    /**
     * Compares two points by polar angle (between 0 and 2&pi;) with respect to this point.
     *
     * @return the comparator
     */
    public Comparator<Point2D> polarOrder() {
        return new PolarOrder();
    }

    /**
     * Compares two points by atan2() angle (between –&pi; and &pi;) with respect to this point.
     *
     * @return the comparator
     */
    public Comparator<Point2D> atan2Order() {
        return new Atan2Order();
    }

    /**
     * Compares two points by distance to this point.
     *
     * @return the comparator
     */
    public Comparator<Point2D> distanceToOrder() {
        return new DistanceToOrder();
    }

    // compare points according to their x-coordinate
    private static class XOrder implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            return Double.compare(p.x, q.x);
        }
    }

    // compare points according to their y-coordinate
    private static class YOrder implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            return Double.compare(p.y, q.y);
        }
    }

    // compare points according to their polar radius
    private static class ROrder implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            double delta = (p.x*p.x + p.y*p.y) - (q.x*q.x + q.y*q.y);
            return Double.compare(delta, 0);
        }
    }

    // compare other points relative to atan2 angle (between -pi/2 and pi/2) they make with this Point
    private class Atan2Order implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double angle1 = angleTo(q1);
            double angle2 = angleTo(q2);
            return Double.compare(angle1, angle2);
        }
    }

    // compare other points relative to polar angle (between 0 and 2pi) they make with this Point
    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double dx1 = q1.x - x;
            double dy1 = q1.y - y;
            double dx2 = q2.x - x;
            double dy2 = q2.y - y;

            if      (dy1 >= 0 && dy2 < 0) return -1;    // q1 above; q2 below
            else if (dy2 >= 0 && dy1 < 0) return +1;    // q1 below; q2 above
            else if (dy1 == 0 && dy2 == 0) {            // 3-collinear and horizontal
                if      (dx1 >= 0 && dx2 < 0) return -1;
                else if (dx2 >= 0 && dx1 < 0) return +1;
                else                          return  0;
            }
            else return -ccw(Point2D.this, q1, q2);     // both above or below

            // Note: ccw() recomputes dx1, dy1, dx2, and dy2
        }
    }

    // compare points according to their distance to this point
    private class DistanceToOrder implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            double dist1 = distanceSquaredTo(p);
            double dist2 = distanceSquaredTo(q);
            return Double.compare(dist1, dist2);
        }
    }

    /**
     * Plot this point using standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Plot a line from this point to that point using standard draw.
     * @param that the other point
     */
    public void drawTo(Point2D that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
}