package com.star.sorting.section04;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Heap {

    public static void sort(Comparable[] priorityQueue) {

        int n = priorityQueue.length;

        for (int k = n / 2; k >= 1; k--) {
            sink(priorityQueue, k, n);
        }

        while (n > 1) {

            exchange(priorityQueue, 1, n--);
            sink(priorityQueue, 1, n);
        }
    }

    private static void sink(Comparable[] priorityQueue, int k, int n) {

        while (2 * k <= n) {

            int j = 2 * k;

            if (j < n && less(priorityQueue, j, j + 1)) {
                j++;
            }

            if (!less(priorityQueue, k, j)) {
                break;
            }

            exchange(priorityQueue, k, j);

            k = j;
        }
    }

    private static boolean less(Comparable[] priorityQueue, int i, int j) {
        return priorityQueue[i - 1].compareTo(priorityQueue[j - 1]) < 0;
    }


    private static void exchange(Comparable[] priorityQueue, int i, int j) {

        Comparable temp = priorityQueue[i - 1];
        priorityQueue[i - 1] = priorityQueue[j - 1];
        priorityQueue[j - 1] = temp;
    }

    private static void show(Comparable[] priorityQueue) {

        for (int i = 0; i < priorityQueue.length; i++) {
            StdOut.print(priorityQueue[i] + " ");
        }

        StdOut.println();
    }

    private static boolean sorted(Comparable[] priorityQueue) {

        for (int i = 1; i < priorityQueue.length; i++) {
            if (less(priorityQueue, i + 1, i - 1 + 1)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        String[] array = new In(args[0]).readAllStrings();

        sort(array);

        assert sorted(array);

        show(array);
    }
}
