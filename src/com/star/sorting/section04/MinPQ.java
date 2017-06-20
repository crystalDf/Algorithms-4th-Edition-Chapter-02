package com.star.sorting.section04;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<Key extends Comparable<Key>> implements Iterable<Key> {

    private int n;
    private Key[] priorityQueue;

    public MinPQ(int initCapacity) {

        n = 0;
        priorityQueue = (Key[]) new Comparable[initCapacity + 1];
    }

    public MinPQ() {

        this(1);
    }

    public MinPQ(Key[] keys) {

        n = keys.length;
        priorityQueue = (Key[]) new Comparable[keys.length + 1];

        for (int i = 0; i < n; i++) {
            priorityQueue[i + 1] = keys[i];
        }

        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }

        assert isMinHeap();
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

    public Key getMin() {

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

        assert isMinHeap();
    }

    public Key deleteMin() {

        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }

        Key min = priorityQueue[1];

        exchange(1, n--);
        sink(1);
        priorityQueue[n + 1] = null;

        if ((n > 0) && (n == ((priorityQueue.length - 1)/ 4))) {
            resize(priorityQueue.length / 2);
        }

        assert isMinHeap();

        return min;
    }

    private boolean greater(int i, int j) {
        return priorityQueue[i].compareTo(priorityQueue[j]) > 0;
    }

    private void exchange(int i, int j) {

        Key temp = priorityQueue[i];
        priorityQueue[i] = priorityQueue[j];
        priorityQueue[j] = temp;
    }

    private void swim(int k) {

        while (k > 1 && greater(k / 2, k)) {

            exchange(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {

        while (2 * k <= n) {

            int j = 2 * k;

            if (j < n && greater(j, j + 1)) {
                j++;
            }

            if (!greater(k, j)) {
                break;
            }

            exchange(k, j);

            k = j;
        }
    }

    private boolean isMinHeap() {

        return isMinHeap(1);
    }

    private boolean isMinHeap(int k) {

        if (k > n) {
            return true;
        }

        int left = 2 * k;
        int right = 2 * k + 1;

        if (left <= n && greater(k, left)) {
            return false;
        }

        if (right <= n && greater(k, right)) {
            return false;
        }

        return isMinHeap(left) && isMinHeap(right);
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        private MinPQ<Key> copy;

        public HeapIterator() {

            copy = new MinPQ<>(getSize());

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

            return copy.deleteMin();
        }
    }

    public static void main(String[] args) {
        MinPQ<String> priorityQueue =
                new MinPQ<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!"-".equals(item)) {
                priorityQueue.insert(item);
            } else if (!priorityQueue.isEmpty()) {
                StdOut.print(priorityQueue.deleteMin() + " ");
            }
        }

        StdOut.println("(" + priorityQueue.getSize() + " left on priorityQueue)");
    }
}
