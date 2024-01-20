package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

import java.lang.reflect.Array;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int[] a = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        for (int k : a) {
            Stopwatch sw = new Stopwatch();
            AList<Integer> t = new AList<>();
            for (int j = 0; j < k; j++) {
                t.addLast(j);
            }
            Ns.addLast(k);
            times.addLast(sw.elapsedTime());
            opCounts.addLast(k);
        }
        printTimingTable(Ns, times, opCounts);
    }
}
