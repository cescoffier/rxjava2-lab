package me.escoffier.lab.chapter7;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

public class Code02 {

    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.range(1, 10)
                .filter(n -> n % 2 == 0);

        TestSubscriber<Integer> testSubscriber = flowable.test(0);

        testSubscriber
                .assertNever(n -> n % 2 == 1)
                .requestMore(2)
                .assertValues(2, 4)
                .assertNotComplete()
                .requestMore(3)
                .assertValues(2, 4, 6, 8, 10)
                .assertComplete();

        testSubscriber = flowable.test(0);

        testSubscriber
                .assertNotComplete()
                .requestMore(2)
                .assertValues(2, 4)
                .cancel();

        testSubscriber
                .assertNotComplete();
    }
}
