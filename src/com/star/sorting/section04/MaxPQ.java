package com.star.sorting.section04;

public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int n;

    public MaxPQ(int maxN) {

        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {

        return n == 0;
    }

    public int size() {

        return n;
    }

    public void insert(Key v) {

        pq[++n] = v;
        swim(n);
    }

    public Key deleteMax() {

        Key max = pq[1];
        exchange(1, n--);
        pq[n + 1] = null;
        sink(1);

        return max;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private void exchange(int i, int j) {

        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void swim(int n) {
    }

    private void sink(int i) {
    }
}
