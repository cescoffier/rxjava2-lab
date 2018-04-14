package me.escoffier.lab.chapter7;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import java.util.concurrent.TimeUnit;

public class Code05 {

    public static void main(String[] args) {
        Observable<String> strings = Observable.just("a", "b", "c", "d");
        Observable<Long> ticks = Observable.interval(500, TimeUnit.MILLISECONDS);
        Observable<String> stream = Observable.zip(ticks, strings, (t, s) -> s);

        TestObserver<String> testObserver = stream.test();
        if (testObserver.awaitTerminalEvent(3, TimeUnit.SECONDS)) {
            testObserver
                    .assertNoErrors()
                    .assertComplete()
                    .assertValues("a", "b", "c", "d");
            System.out.println("Cool!");
        } else {
            System.out.println("It did not finish on time");
        }
    }
}
