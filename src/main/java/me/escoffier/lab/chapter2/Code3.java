package me.escoffier.lab.chapter2;


import io.reactivex.Observable;

public class Code3 {

    public static void main(String... args) {
        Observable.just("Black Canary", "Catwoman", "Elektra")
            // Use the `subscribe` method using the 3 parameters
            // to receive (and print):
            // 1. the item
            // 2. the error
            // 3. the completion event
        ;
    }
}
