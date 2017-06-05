package com.star.sorting.section01;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Selection {

    public static void sort(Comparable[] array) {

        int n = array.length;

        for (int i = 0; i < n; i++) {

            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (less(array[j], array[minIndex])) {
                    minIndex = j;
                }
            }

            exchange(array, i, minIndex);
        }
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
