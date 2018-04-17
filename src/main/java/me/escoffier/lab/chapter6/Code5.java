package me.escoffier.lab.chapter6;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static me.escoffier.superheroes.Helpers.log;
import static me.escoffier.superheroes.Helpers.threadFactory;

public class Code5 {

    private static List<String> SUPER_HEROES = Arrays.asList(
        "Superman",
        "Batman",
        "Aquaman",
        "Asterix",
        "Captain America"
    );

    public static void main(String[] args) throws Exception {

        Scheduler scheduler = Schedulers.from(newFixedThreadPool(10, threadFactory));

        CountDownLatch latch = new CountDownLatch(1);

        // Synchronous emission
        Observable<Object> observable = Observable.create(emitter -> {
            for (String superHero : SUPER_HEROES) {
                log("Emitting: " + superHero);
                emitter.onNext(superHero);
            }
            log("Completing");
            emitter.onComplete();
        });

        log("---------------- Subscribing");
        observable
            .subscribeOn(scheduler)
            .subscribe(
                item -> log("Received " + item),
                error -> log("Error"),
                () -> {
                    log("Complete");
                    latch.countDown();
                });
        log("---------------- Subscribed");

        latch.await();
    }
}
