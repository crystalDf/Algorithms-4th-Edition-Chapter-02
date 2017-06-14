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

    private void swim(int n) {
    }

    private void exchange(int i, int i1) {
    }

    private void sink(int i) {
    }
}
