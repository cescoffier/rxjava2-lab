package me.escoffier.lab.chapter2;


import io.reactivex.Observable;

public class Code5 {

    public static void main(String... args) {
        Observable<String> stream = Observable.create(subscriber -> {
            // Emit items
            subscriber.onNext("Black Canary");
            subscriber.onNext("Catwoman");
            // Inject an error using the onError
            // method
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
