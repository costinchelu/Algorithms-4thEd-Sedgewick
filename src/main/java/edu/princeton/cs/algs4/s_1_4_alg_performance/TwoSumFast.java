package edu.princeton.cs.algs4.s_1_4_alg_performance;

import java.util.Arrays;
import java.util.HashSet;

import edu.princeton.cs.algs4.in_out.In;
import edu.princeton.cs.algs4.in_out.StdOut;
import edu.princeton.cs.algs4.s_1_1_binary_search.BinarySearch;

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
    * <p></p>
    * Algorithm complexity: {@code O(n log(n))}
    * */
   public static int count(int[] a) {
      Arrays.sort(a);                                    // O(n log(n))
      int N = a.length;
      int cnt = 0;
      for (int i = 0; i < N; i++) {
         if (BinarySearch.indexOf(a, -a[i]) > i) {        // O(log(n))
            cnt++;
         }
      }
      return cnt;
   }

   // trading time complexity for space complexity
   // O(N) = linear time:
   public static int twoSumEvenFaster(int[] a) {
      HashSet<Integer> numberSet = new HashSet<>();
      int count = 0;

      for(int num : a){
         if(numberSet.contains(-num)) {            // Check if set contains negative of num
            count++;
         } else {
            numberSet.add(num);                    // If not, add element to set
         }
      }

      return count;
   }

   public static void main(String[] args) throws Exception {
      In getFileName = new In();
      In in = new In(getFileName.getFileFromResources("8Kints.txt"));
      int[] a = Arrays.stream(in.readAllLines()).map(String::trim).mapToInt(Integer::valueOf).toArray();
      Stopwatch stopwatch = new Stopwatch();
      StdOut.println(count(a));
      StdOut.printf("%e (%.4f seconds)\n", 0.0, stopwatch.elapsedTime());
   }
}
