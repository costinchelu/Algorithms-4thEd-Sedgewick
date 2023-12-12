import edu.princeton.cs.algs4.in_out.In;
import edu.princeton.cs.algs4.in_out.StdOut;
import edu.princeton.cs.algs4.s_1_4_alg_performance.Stopwatch;

public class LoadSampleFile {
    public static void main(String[] args) throws Exception {
        In in = new In();
        In input = new In(in.getFileFromResources("file-name"));

//        int[] ints = Arrays.stream(in.readAllLines()).map(String::trim).mapToInt(Integer::valueOf).toArray();
        String[] lines = input.readAllLines();

        Stopwatch stopwatch = new Stopwatch();
        StdOut.println(count(lines));
        StdOut.printf("%e (%.4f seconds)\n", 0.0, stopwatch.elapsedTime());
    }
}