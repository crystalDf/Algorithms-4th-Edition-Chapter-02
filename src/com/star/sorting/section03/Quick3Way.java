package com.star.sorting.section03;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3Way {

    public static void sort(Comparable[] array) {

        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }

    private static void sort(Comparable[] array, int low, int high) {

        if (high <= low) {
            return;
        }

        int lt = low;
        int i = low + 1;
        int gt = high;

        Comparable v = array[low];

        while (i <= gt) {

            int cmp = array[i].compareTo(v);

            if (cmp < 0) {
                exchange(array, lt++, i++);
            } else if (cmp > 0) {
                exchange(array, i, gt--);
            } else {
                i++;
            }
        }

        sort(array, low, lt - 1);
        sort(array, gt + 1, high);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exchange(Comparable[] array, int i, int j) {

        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void show(Comparable[] array) {

        for (int i = 0; i < array.length; i++) {
            StdOut.print(array[i] + " ");
        }

        StdOut.println();
    }

    private static boolean sorted(Comparable[] array) {

        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1])) {
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
