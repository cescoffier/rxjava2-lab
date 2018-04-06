package me.escoffier.lab.chapter1;

import java.util.Arrays;
import java.util.List;

import io.reactivex.*;

public class Code2 {

    private static List<String> SUPER_HEROS = Arrays.asList(
        "Superman",
        "Batman",
        "Aquaman",
        "Asterix",
        "Captain America"
    );

    public static void main(String... args) {
        Observable
            .fromIterable(SUPER_HEROS)
            .map(n -> n.toUpperCase())
            .filter(name -> name.startsWith("A"))
            .subscribe(
            name -> System.out.println(name)
        );
    }
}