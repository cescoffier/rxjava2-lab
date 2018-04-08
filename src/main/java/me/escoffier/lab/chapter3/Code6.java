package me.escoffier.lab.chapter3;


import io.reactivex.Completable;

public class Code6 {

    public static void main(String[] args) {
        Completable.fromAction(() -> System.out.println("Hello"))
            .subscribe(
                () -> System.out.println("OK"),
                err -> System.out.println("KO")
            );
    }

}
