package me.escoffier.lab.chapter2;


import io.reactivex.Observable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class HotStream {

    static Observable<Integer> create() {
        AtomicInteger subscribers = new AtomicInteger();
        AtomicInteger counter = new AtomicInteger();
        return Observable.<Integer>create(subscriber ->
            new Thread(() -> {
                while (subscribers.get() > 0) {
                    subscriber.onNext(counter.getAndIncrement());
                    nap();
                }
            }).start()
        ).publish().autoConnect()
            .doOnSubscribe(s -> subscribers.getAndIncrement())
            .doOnDispose(() -> {
                System.out.println("A subscriber is leaving");
                subscribers.decrementAndGet();
            });
    }

    public static void nap() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignore me.
        }
    }

    public static void nap(int sec) {
        try {
            Thread.sleep(sec *1000);
        } catch (InterruptedException e) {
            // Ignore me.
        }
    }
}
