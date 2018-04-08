package me.escoffier.lab.chapter3;


import io.reactivex.Maybe;

public class Code4 {


    public static void main(String[] args) {
        Maybe.just("Superman")
            .subscribe(
                name -> System.out.println("[A] Received " + name),
                Throwable::printStackTrace,
                () -> System.out.println("[A] Completed")
            );

        Maybe.empty()
            .subscribe(
                name -> System.out.println("[B] Received " + name + " (not called)"),
                Throwable::printStackTrace,
                () -> System.out.println("[B] Completed")
            );
    }
}
