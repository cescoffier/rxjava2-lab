package me.escoffier.lab.chapter3;


import io.reactivex.Single;

public class Code1 {


    public static void main(String[] args) {
        Single.just("Superman")
            .doOnSuccess(s -> System.out.println("Hello " + s))
            .subscribe();
    }
}
