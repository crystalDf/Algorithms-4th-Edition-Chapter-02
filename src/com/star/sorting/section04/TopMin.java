package com.star.sorting.section04;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;


public class TopMin {

    public static void main(String[] args) {

        int minTopN = Integer.parseInt(args[0]);

        MaxPQ<Transaction> priorityQueue = new MaxPQ<>();

        In in = new In(args[1]);

        while (in.hasNextLine()) {

            priorityQueue.insert(new Transaction(in.readLine()));

            if (priorityQueue.size() > minTopN) {

                priorityQueue.deleteMax();
            }
        }

        Stack<Transaction> stack = new Stack<>();

        while (!priorityQueue.isEmpty()) {

            stack.push(priorityQueue.deleteMax());
        }

        for (Transaction transaction : stack) {

            StdOut.println(transaction);
        }
    }
}
