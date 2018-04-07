package me.escoffier.lab.chapter2;


import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class Code1_Solution {

    private static List<String> SUPER_HEROS = Arrays.asList(
        "Superman",
        "Batman",
        "Aquaman",
        "Asterix",
        "Captain America"
    );

    public static void main(String... args) {
        Observable.fromIterable(SUPER_HEROS)
            .doOnNext(s -> System.out.println("Next >> " + s))
            .doOnComplete(() -> System.out.println("Completion"))
            .subscribe();
    }
}
