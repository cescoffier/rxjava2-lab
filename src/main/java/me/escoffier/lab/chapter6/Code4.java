package me.escoffier.lab.chapter6;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

import static me.escoffier.superheroes.Helpers.log;
import static me.escoffier.superheroes.Helpers.sleep;

public class Code4 {

    private static List<String> SUPER_HEROES = Arrays.asList(
        "Superman",
        "Batman",
        "Aquaman",
        "Asterix",
        "Captain America"
    );

    public static void main(String[] args) {
        Observable<Object> observable = Observable.create(emitter -> {
            Thread thread = new Thread(() -> {
                for (String superHero : SUPER_HEROES) {
                    log("Emitting: " + superHero);
                    emitter.onNext(superHero);
                }
                log("Completing");
                emitter.onComplete();
            });
            thread.start();
        });

        log("---------------- Subscribing");
        observable
            // Blocking the emission thread
            .doOnNext(x -> sleep(30))
            .subscribe(
                item -> log("Received " + item),
                error -> log("Error"),
                () -> log("Complete"));
        log("---------------- Subscribed");
    }
}
