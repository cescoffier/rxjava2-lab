package me.escoffier.lab.chapter2;


import io.reactivex.Observable;

public class Code3_Solution {

    public static void main(String... args) {
        Observable.just("Black Canary", "Catwoman", "Elektra")
            .subscribe(
                name -> System.out.println(">> " + name),
                Throwable::printStackTrace,
                () -> System.out.println("Completion")
            );
    }
}
