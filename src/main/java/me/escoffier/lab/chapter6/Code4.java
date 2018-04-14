package me.escoffier.lab.chapter6;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

import static me.escoffier.superheroes.Helpers.log;
import static me.escoffier.superheroes.Helpers.sleep;

public class Code4 {

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
          sleep(30);
          log("Received " + item);
        }, error -> {
          sleep(30);
          log("Error");
        }, () -> {
          sleep(30);
          log("Complete");
        });
    log("---------------- Subscribed");
  }
}
