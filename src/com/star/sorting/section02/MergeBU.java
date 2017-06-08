package com.star.sorting.section02;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MergeBU {

    private static Comparable[] aux;

    public static void sort(Comparable[] array) {

        int n = array.length;
        aux = new Comparable[n];

        for (int size = 1; size < n; size += size) {

            for (int low = 0; low < n - size; low += size + size) {

                merge(array, low, low + size - 1, Math.min(low + size + size - 1, n-1));
            }
        }
    }

    private static void merge(Comparable[] array, int low, int middle, int high) {

        int i = low;
        int j = middle + 1;

        for (int k = low; k <= high; k++) {
            aux[k] = array[k];
        }

        for (int k = low; k <= high; k++) {
            if (i > middle) {
                array[k] = aux[j++];
            } else if (j > high) {
                array[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                array[k] = aux[j++];
            } else {
                array[k] = aux[i++];
            }
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
