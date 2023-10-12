package edu.princeton.cs.algs4.section_1_4;

import java.util.Arrays;

import edu.princeton.cs.algs4.in_out.In;
import edu.princeton.cs.algs4.in_out.StdOut;
import edu.princeton.cs.algs4.section_1_1.BinarySearch;

public class TwoSumFast {


   /**
    * Count pairs that sum to 0.
    * <p></p>
    * Uses a merge sort and a binary search to create a logarithmic solution for the problem
    * <p></p>
    * because only an integer and the inverse of that integer can be summed up to 0, we will search for each of the
    * numbers, their reversed value in the array.
    * <p></p>
    * case nothing is found binary search will return -1 (no count)
    * <p></p>
    * case the returned index is lower than the index we are searching, we will not count because
    * we don't want double counting
    * */
   public static int count(int[] a) {
      Arrays.sort(a);
      int N = a.length;
      int cnt = 0;
      for (int i = 0; i < N; i++)
         if (BinarySearch.rank(-a[i], a) > i)
            cnt++;
      return cnt;
   }

   public static void main(String[] args) throws Exception {
      In getFileName = new In();
      In in = new In(getFileName.getFileFromResources("8Kints.txt"));
      int[] a = Arrays.stream(in.readAllLines()).map(String::trim).mapToInt(Integer::valueOf).toArray();
      Stopwatch stopwatch = new Stopwatch();
      StdOut.println(count(a));
      StdOut.printf("%e (%.2f seconds)\n", 0.0, stopwatch.elapsedTime());
   }
}
