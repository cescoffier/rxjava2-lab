package me.escoffier.lab.chapter2;


import io.reactivex.Observable;

public class Code5_Solution {

    public static void main(String... args) {
        Observable<String> stream = Observable.create(subscriber -> {
            // Emit items
            subscriber.onNext("Black Canary");
            subscriber.onNext("Catwoman");
            // Inject an error
            subscriber.onError(new Exception("What a terrible failure"));
            subscriber.onNext("Elektra");
            // Notify the completion
            subscriber.onComplete();
        });

        stream
            .subscribe(
                i -> System.out.println("Received: " + i),
                err -> System.out.println("BOOM"),
                () -> System.out.println("Completion")
            );

    }
}
