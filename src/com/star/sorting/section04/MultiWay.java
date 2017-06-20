package com.star.sorting.section04;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MultiWay {

    public static void merge(In[] streams) {

        int n = streams.length;

//        IndexMinPQ<String> priorityQueue = new IndexMinPQ<>();
        IndexMaxPQ<String> priorityQueue = new IndexMaxPQ<>();

        for (int index = 0; index < n; index++) {

            if (!streams[index].isEmpty()) {
                priorityQueue.insert(index, streams[index].readString());
            }
        }

        while (!priorityQueue.isEmpty()) {

//            StdOut.print(priorityQueue.getMin() + " ");
            StdOut.print(priorityQueue.getMax() + " ");

//            int index = priorityQueue.deleteMin();
            int index = priorityQueue.deleteMax();

            if (!streams[index].isEmpty()) {
                priorityQueue.insert(index, streams[index].readString());
            }
        }
    }

    public static void main(String[] args) {

        int n = args.length;

        In[] streams = new In[n];

        for (int i = 0; i < n; i++) {
            streams[i] = new In(args[i]);
        }

        merge(streams);
    }
}
