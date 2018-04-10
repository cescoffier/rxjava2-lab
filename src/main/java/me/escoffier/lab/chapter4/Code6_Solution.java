package me.escoffier.lab.chapter4;


import java.util.HashSet;

import static me.escoffier.superheroes.Helpers.heroes;

public class Code6_Solution {

    public static void main(String[] args) {
        heroes()
            .scan(new HashSet<>(), (set, superstuff) -> {
                set.addAll(superstuff.getSuperpowers());
                return set;
            })
            .doOnNext(System.out::println)
            .count()
            .subscribe(
                number -> System.out.println("Heroes have " + number + " unique super powers")
            );
    }

}
