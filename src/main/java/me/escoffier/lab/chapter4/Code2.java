package me.escoffier.lab.chapter4;

import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.villains_names;

public class Code2 {

    public static void main(String[] args) {
        SuperHeroesService.run();

        // Extract 10 villains that are in the position 21 -> 31 in the stream
        villains_names()
            .subscribe(System.out::println);
    }

}
