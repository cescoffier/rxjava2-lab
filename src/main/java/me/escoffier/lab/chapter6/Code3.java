package me.escoffier.lab.chapter6;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

import static me.escoffier.superheroes.Helpers.log;

public class Code3 {

  private static List<String> SUPER_HEROS = Arrays.asList(
      "Superman",
      "Batman",
      "Aquaman",
      "Asterix",
      "Captain America"
  );

  public static void main(String[] args) {

    // Synchronous emission
    Observable<Object> observable = Observable.create(emitter -> {
      Thread thread = new Thread(() -> {
        for (String superHero : SUPER_HEROS) {
          log("Emitting: " + superHero);
          emitter.onNext(superHero);
        }
        log("Completing");
        emitter.onComplete();
      });
      thread.start();
    });

    log("---------------- Subscribing");
    observable.subscribe(
        item -> {
          log("Received " + item);
        }, error -> {
          log("Error");
        }, () -> {
          log("Complete");
        });
    log("---------------- Subscribed");
  }
}
