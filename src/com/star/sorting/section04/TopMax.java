package com.star.sorting.section04;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;


public class TopMax {

    public static void main(String[] args) {

        int maxTopN = Integer.parseInt(args[0]);

        MinPQ<Transaction> priorityQueue = new MinPQ<>();

        In in = new In(args[1]);

        while (in.hasNextLine()) {

            priorityQueue.insert(new Transaction(in.readLine()));

            if (priorityQueue.getSize() > maxTopN) {

                priorityQueue.deleteMin();
            }
        }

        Stack<Transaction> stack = new Stack<>();

        while (!priorityQueue.isEmpty()) {

            stack.push(priorityQueue.deleteMin());
        }

        for (Transaction transaction : stack) {

            StdOut.println(transaction);
        }
    }
}
