package me.escoffier.lab.chapter2;


import io.reactivex.Observable;

import java.util.Scanner;

public class Code7 {

    public static void main(String... args) {
        Observable<String> stream = Observable.just("Black Canary", "Catwoman", "Elektra");

        stream
            .subscribe(
                i -> System.out.println("[A] Received: " + i),
                err -> System.out.println("[A] BOOM"),
                () -> System.out.println("[A] Completion")
            );

        stream
            .subscribe(
                i -> System.out.println("[B] Received: " + i),
                err -> System.out.println("[B] BOOM"),
                () -> System.out.println("[B] Completion")
            );

    }
}
