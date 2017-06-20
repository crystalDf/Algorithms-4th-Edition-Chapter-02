package com.star.sorting.section04;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<Key extends Comparable<Key>> implements Iterable<Key> {

    private int n;
    private Key[] priorityQueue;

    public MaxPQ(int initCapacity) {

        n = 0;
        priorityQueue = (Key[]) new Comparable[initCapacity + 1];
    }

    public MaxPQ() {

        this(1);
    }

    public MaxPQ(Key[] keys) {

        n = keys.length;
        priorityQueue = (Key[]) new Comparable[keys.length + 1];

        for (int i = 0; i < n; i++) {
            priorityQueue[i + 1] = keys[i];
        }

        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }

        assert isMaxHeap();
    }

    public boolean isEmpty() {

        return n == 0;
    }

    public int getSize() {

        return n;
    }

    private void resize(int capacity) {

        Key[] temp = (Key[]) new Comparable[capacity];

        for (int i = 1; i <= n; i++) {
            temp[i] = priorityQueue[i];
        }

        priorityQueue = temp;
    }

    public Key getMax() {

        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }

        return priorityQueue[1];
    }

    public void insert(Key v) {

        if (n == (priorityQueue.length - 1)) {
            resize(2 * priorityQueue.length);
        }

        priorityQueue[++n] = v;
        swim(n);

        assert isMaxHeap();
    }

    public Key deleteMax() {

        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }

        Key max = priorityQueue[1];

        exchange(1, n--);
        sink(1);
        priorityQueue[n + 1] = null;

        if ((n > 0) && (n == ((priorityQueue.length - 1)/ 4))) {
            resize(priorityQueue.length / 2);
        }

        assert isMaxHeap();

        return max;
    }

    private boolean less(int i, int j) {
        return priorityQueue[i].compareTo(priorityQueue[j]) < 0;
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

    private boolean isMaxHeap() {

        return isMaxHeap(1);
    }

    private boolean isMaxHeap(int k) {

        if (k > n) {
            return true;
        }

        int left = 2 * k;
        int right = 2 * k + 1;

        if (left <= n && less(k, left)) {
            return false;
        }

        if (right <= n && less(k, right)) {
            return false;
        }

        return isMaxHeap(left) && isMaxHeap(right);
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        private MaxPQ<Key> copy;

        public HeapIterator() {

            copy = new MaxPQ<>(getSize());

            for (int i = 1; i <= n; i++) {
                copy.insert(priorityQueue[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Priority queue underflow");
            }

            return copy.deleteMax();
        }
    }

    public static void main(String[] args) {
        MaxPQ<String> priorityQueue =
                new MaxPQ<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!"-".equals(item)) {
                priorityQueue.insert(item);
            } else if (!priorityQueue.isEmpty()) {
                StdOut.print(priorityQueue.deleteMax() + " ");
            }
        }

        StdOut.println("(" + priorityQueue.getSize() + " left on priorityQueue)");
    }
}
