package com.star.sorting.section04;

public class MinPQ<Key extends Comparable<Key>> {

    private Key[] priorityQueue;
    private int n;

    public MinPQ() {

        priorityQueue = (Key[]) new Comparable[2];
    }

    public MinPQ(int capacity) {

        priorityQueue = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {

        return n == 0;
    }

    public int size() {

        return n;
    }

    private void resize(int max) {

        Key[] temp = (Key[]) new Comparable[max];

        for (int i = 1; i <= n; i++) {
            temp[i] = priorityQueue[i];
        }

        priorityQueue = temp;
    }

    public void insert(Key v) {

        if ((n + 1) == priorityQueue.length) {
            resize(2 * priorityQueue.length);
        }

        priorityQueue[++n] = v;
        swim(n);
    }

    public Key deleteMin() {

        Key min = priorityQueue[1];

        exchange(1, n--);
        priorityQueue[n + 1] = null;
        sink(1);

        if ((n > 0) && ((n + 1) == (priorityQueue.length / 4))) {
            resize(priorityQueue.length / 2);
        }

        return min;
    }

    private boolean less(int i, int j) {
        return priorityQueue[i].compareTo(priorityQueue[j]) > 0;
    }

    private void exchange(int i, int j) {

        Key temp = priorityQueue[i];
        priorityQueue[i] = priorityQueue[j];
        priorityQueue[j] = temp;
    }

    private void swim(int k) {

        while (k > 1 && less(k / 2, k)) {

            exchange(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {

        while (2 * k <= n) {

            int j = 2 * k;

            if (j < n && less(j, j + 1)) {
                j++;
            }

            if (!less(k, j)) {
                break;
            }

            exchange(k, j);

            k = j;
        }
    }
}
