package me.escoffier.lab.chapter6;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

import static me.escoffier.superheroes.Helpers.log;
import static me.escoffier.superheroes.Helpers.sleep;

public class Code2 {

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
        sleep(30); // Introduce fake latency
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
