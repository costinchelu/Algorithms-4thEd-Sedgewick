package edu.princeton.cs.algs4.s_1_3_fundamental_ds;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class JavaStackQueueDequeBlockingQueue {

    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        String pop1 = stack.pop();
        String pop2 = stack.pop();


        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6");
        queue.add("7");
        System.out.println();
        String p1 = queue.poll();
        String p2 = queue.poll();
        String p3 = queue.poll();


        Deque<String> deque = new LinkedList<>();
        deque.add("1");
        deque.add("2");
        deque.add("3");
        deque.add("4");
        deque.add("5");
        String poll = deque.poll();
        String first = deque.pollFirst();
        String last = deque.pollLast();


        BlockingQueue<String> boundedQueue = new ArrayBlockingQueue<>(5);

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                String take = boundedQueue.take();
                System.out.println("Thread t took the head of the queue: " + take);
            } catch (InterruptedException e) {
                log.error(Arrays.toString(e.getStackTrace()));
            }
        }).start();

        try {
            boundedQueue.put("Element 1");
            boundedQueue.put("Element 2");
            boundedQueue.put("Element 3");
            boundedQueue.put("Element 4");
            boundedQueue.put("Element 5");
            boundedQueue.put("Element 6"); // this is blocking the thread (parking).
            // The current thread will be unblocked if another thread is making some space in the blocking queue
            // by taking elements from it
        } catch (InterruptedException e) {
            log.error(Arrays.toString(e.getStackTrace()));
        }

        // Try adding an element when the queue is full
        // (offer will not throw exception, but will only return false)
        boolean added = boundedQueue.offer("Extra Element");
        System.out.println("Extra Element added? " + added);

        // Removing elements from the blocking queue
        for (int i = 0; i < 5; i++) {
            try {
                String element = boundedQueue.take();
                System.out.println("Removed: " + element);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }
}
