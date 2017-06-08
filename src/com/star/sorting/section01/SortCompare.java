package com.star.sorting.section01;


import com.star.sorting.section02.Merge;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class SortCompare {

    public static double time(String algorithm, Double[] array) {

        StopWatch timer = new StopWatch();

        switch (algorithm) {
            case "Selection":
                Selection.sort(array);
                break;
            case "Insertion":
                Insertion.sort(array);
                break;
            case "Shell":
                Shell.sort(array);
                break;
            case "Merge":
                Merge.sort(array);
                break;
        }

        return timer.elapsedTime();
    }

    public static double timeRandomInput(String algorithm, int n, int times) {

        double total = 0;

        Double[] array = new Double[n];

        for (int i = 0; i < times; i++) {

            for (int j = 0; j < n; j++) {
                array[j] = StdRandom.uniform();
            }

            total += time(algorithm, array);
        }

        return total;
    }

    public static void main(String[] args) {

        String firstAlgorithm = args[0];
        String secondAlgorithm = args[1];

        int n = Integer.parseInt(args[2]);
        int times = Integer.parseInt(args[3]);

        double firstAlgorithmTotal = timeRandomInput(firstAlgorithm, n, times);
        double secondAlgorithmTotal = timeRandomInput(secondAlgorithm, n, times);

        StdOut.println(firstAlgorithmTotal + " " + secondAlgorithmTotal);

        StdOut.printf("For %d random Doubles\n%s is ",
                n, firstAlgorithm);
        StdOut.printf("%.2f times faster than %s\n",
                secondAlgorithmTotal / firstAlgorithmTotal, secondAlgorithm);

    }
}
