package me.escoffier.lab.chapter6;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

import static me.escoffier.superheroes.Helpers.log;

public class Code1 {

    private static List<String> SUPER_HEROES = Arrays.asList(
        "Superman",
        "Batman",
        "Aquaman",
        "Asterix",
        "Captain America"
    );

    public static void main(String[] args) {
        Observable<Object> observable = Observable.create(emitter -> {
            for (String superHero : SUPER_HEROES) {
                log("Emitting: " + superHero);
                emitter.onNext(superHero);
            }
            log("Completing");
            emitter.onComplete();
        });

        log("---------------- Subscribing");
        observable.subscribe(
            item -> log("Received " + item),
            error -> log("Error"),
            () -> log("Complete"));
        log("---------------- Subscribed");
    }
}
