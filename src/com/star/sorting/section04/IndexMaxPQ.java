package com.star.sorting.section04;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMaxPQ<Key extends Comparable<Key>> implements Iterable<Integer> {

    private int n;
    private Key[] priorityQueueGetValueFromIndex;
    private int[] priorityQueueGetIndexFromPosition;
    private int[] priorityQueueGetPositionFromIndex;

    public IndexMaxPQ(int initCapacity) {

        n = 0;
        priorityQueueGetValueFromIndex = (Key[]) new Comparable[initCapacity + 1];
        priorityQueueGetIndexFromPosition = new int[initCapacity + 1];
        priorityQueueGetPositionFromIndex = new int[initCapacity + 1];
    }

    public IndexMaxPQ() {

        this(1);
    }

    public boolean isEmpty() {

        return n == 0;
    }

    public int getSize() {

        return n;
    }

    private void resize(int capacity) {

        Key[] tempValue = (Key[]) new Comparable[capacity];
        int[] tempIndex = new int[capacity];
        int[] tempPosition = new int[capacity];

        for (int i = 0; i <= n; i++) {
            tempValue[i] = priorityQueueGetValueFromIndex[i];
            tempIndex[i] = priorityQueueGetIndexFromPosition[i];
            tempPosition[i] = priorityQueueGetPositionFromIndex[i];
        }

        priorityQueueGetValueFromIndex = tempValue;
        priorityQueueGetIndexFromPosition = tempIndex;
        priorityQueueGetPositionFromIndex = tempPosition;
    }

    public int getMaxIndex() {

        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }

        return priorityQueueGetIndexFromPosition[1];
    }

    public Key getMax() {

        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }

        return priorityQueueGetValueFromIndex[priorityQueueGetIndexFromPosition[1]];
    }

    public void insert(int index, Key v) {

        if (n == (priorityQueueGetValueFromIndex.length - 1)) {
            resize(2 * priorityQueueGetValueFromIndex.length);
        }

        n++;

        priorityQueueGetValueFromIndex[index] = v;
        priorityQueueGetIndexFromPosition[n] = index;
        priorityQueueGetPositionFromIndex[index] = n;

        swim(n);

        assert isMaxHeap();
    }

    public int deleteMax() {

        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }

        int maxIndex = priorityQueueGetIndexFromPosition[1];
        Key value = priorityQueueGetValueFromIndex[maxIndex];

        exchange(1, n--);
        sink(1);
        priorityQueueGetValueFromIndex[maxIndex] = null;

        if ((n > 0) && (n == ((priorityQueueGetValueFromIndex.length - 1)/ 4))) {
//            resize(priorityQueueGetValueFromIndex.length / 2);
        }

        assert isMaxHeap();

        return maxIndex;
    }

    public void deleteIndex(int index) {

        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }

        Key value = priorityQueueGetValueFromIndex[index];
        int position = priorityQueueGetPositionFromIndex[index];

        exchange(position, n--);
        swim(position);
        sink(position);
        priorityQueueGetValueFromIndex[index] = null;

        if ((n > 0) && (n == ((priorityQueueGetValueFromIndex.length - 1)/ 4))) {
//            resize(priorityQueueGetValueFromIndex.length / 2);
        }

        assert isMaxHeap();
    }

    private boolean less(int i, int j) {
        return priorityQueueGetValueFromIndex[priorityQueueGetIndexFromPosition[i]]
                .compareTo(priorityQueueGetValueFromIndex[priorityQueueGetIndexFromPosition[j]]) < 0;
    }

    private void exchange(int i, int j) {

        int temp = priorityQueueGetIndexFromPosition[i];
        priorityQueueGetIndexFromPosition[i] = priorityQueueGetIndexFromPosition[j];
        priorityQueueGetIndexFromPosition[j] = temp;

        priorityQueueGetPositionFromIndex[priorityQueueGetIndexFromPosition[i]] = i;
        priorityQueueGetPositionFromIndex[priorityQueueGetIndexFromPosition[j]] = j;
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
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {

        private IndexMaxPQ<Key> copy;

        public HeapIterator() {

            copy = new IndexMaxPQ<>(getSize());

            for (int i = 1; i <= n; i++) {
                copy.insert(priorityQueueGetIndexFromPosition[i],
                        priorityQueueGetValueFromIndex[priorityQueueGetIndexFromPosition[i]]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Priority queue underflow");
            }

            return copy.deleteMax();
        }
    }

    public static void main(String[] args) {

        String[] strings = { "it", "was", "the", "best", "of", "times",
                "it", "was", "the", "worst" };

        IndexMaxPQ<String> priorityQueue = new IndexMaxPQ<>();

        for (int index = 0; index < strings.length; index++) {

            priorityQueue.insert(index, strings[index]);
        }

        while (!priorityQueue.isEmpty()) {

            int index = priorityQueue.deleteMax();
            StdOut.println(index + " " + strings[index]);
        }
        StdOut.println();

        for (int index = 0; index < strings.length; index++) {

            priorityQueue.insert(index, strings[index]);
        }

        for (int index : priorityQueue) {

            StdOut.println(index + " " + strings[index]);
        }
        StdOut.println();

        priorityQueue.deleteIndex(0);

        while (!priorityQueue.isEmpty()) {

            int index = priorityQueue.deleteMax();
            StdOut.println(index + " " + strings[index]);
        }
        StdOut.println();
    }
}
