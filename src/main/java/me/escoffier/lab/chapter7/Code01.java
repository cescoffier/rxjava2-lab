package me.escoffier.lab.chapter7;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

public class Code01 {

    public static void main(String[] args) {
        TestObserver<Integer> testObserver = Observable.range(1, 10)
                .filter(n -> n % 2 == 0)
                .test();

        testObserver
                .assertSubscribed()
                .assertNever(n -> n % 2 == 1)
                .assertComplete()
                .assertValueCount(5)
                .assertValues(2, 4, 6, 8, 10);
    }
}
