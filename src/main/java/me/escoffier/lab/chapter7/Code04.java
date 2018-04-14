package me.escoffier.lab.chapter7;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import java.util.concurrent.TimeUnit;

public class Code04 {

    public static void main(String[] args) throws Throwable {
        TestScheduler scheduler = new TestScheduler();

        Single<Long> s1 = Single.timer(1, TimeUnit.SECONDS, scheduler);
        Single<String> s2 = Single.just("Hello");
        Single<String> r = Single.zip(s1, s2, (t, s) -> t + " -> " + s);

        TestObserver<String> testObserver = r.test();

        testObserver.assertNoValues();

        scheduler.advanceTimeBy(500, TimeUnit.MILLISECONDS);
        testObserver.assertNoValues();

        scheduler.advanceTimeBy(600, TimeUnit.MILLISECONDS);
        testObserver
                .assertNoErrors()
                .assertValue("0 -> Hello");
    }
}
