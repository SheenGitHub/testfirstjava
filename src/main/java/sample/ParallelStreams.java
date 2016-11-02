package sample;

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2016/7/8.
 */
public class ParallelStreams {
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {
        long result = 0L;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long sideEffectSum(long n) {

        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(0,n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static long measureSumPerf(Function<Long, Long> adder, Long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
//            System.out.println("Result: " + sum);
            if(duration < fastest) fastest = duration;
        }
        return fastest;
    }


    public static void main(String[] args) {
        System.out.println("Sequtial sum done in :" +
                measureSumPerf(ParallelStreams::sequentialSum, 10_000_000L) + "msecs");
        System.out.println("Interative sum done in :" +
                measureSumPerf(ParallelStreams::iterativeSum, 10_000_000L) + "msecs");
        System.out.println("Parrallel sum done in :" +
                measureSumPerf(ParallelStreams::parallelSum, 10_000_000L) + "msecs");
        System.out.println("Ranged sum done in :" +
                measureSumPerf(ParallelStreams::rangedSum, 10_000_000L) + "msecs");
        System.out.println("Parallel ranged sum done in :" +
                measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000L) + "msecs");
        System.out.println("Side effect ranged sum done in :" +
                measureSumPerf(ParallelStreams::sideEffectSum, 10_000_000L) + "msecs");
        System.out.println("Parallel side effect ranged sum done in :" +
                measureSumPerf(ParallelStreams::sideEffectParallelSum, 10_000_000L) + "msecs");
        System.out.println("ForkJoin sum done in :"
                + measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000L) + " msecs");
    }

    public static class Accumulator {
        public long total = 0;

        public void add(long value) {
            total += value;
        }
    }
}
