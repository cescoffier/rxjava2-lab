package me.escoffier.lab.chapter4;


import java.util.HashSet;

import static me.escoffier.superheroes.Helpers.villains;

public class Code7_Solution {

    public static void main(String[] args) {
        villains()
            .reduce(new HashSet<>(), (set, superstuff) -> {
                set.addAll(superstuff.getSuperpowers());
                return set;
            })
            .doOnSuccess(System.out::println)
            .subscribe(
                set -> System.out.println("Villains have " + set.size() + " unique super powers")
            );
    }

}
