package com.star.sorting.section04;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;


public class TopMax {

    public static void main(String[] args) {

        int max = Integer.parseInt(args[0]);

        MaxPQ<Transaction> pq = new MaxPQ<>(max + 1);

        In in = new In(args[1]);

        while (in.hasNextLine()) {

            pq.insert(new Transaction(in.readLine()));

            if (pq.size() > max) {

                pq.deleteMax();
            }
        }

        Stack<Transaction> stack = new Stack<>();

        while (!pq.isEmpty()) {

            stack.push(pq.deleteMax());
        }

        for (Transaction transaction : stack) {

            StdOut.println(transaction);
        }
    }
}
