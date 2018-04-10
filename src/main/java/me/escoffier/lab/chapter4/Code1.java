package me.escoffier.lab.chapter4;

import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.villains_names;

public class Code1 {

    public static void main(String[] args) {
        SuperHeroesService.run();

        villains_names()
            // Accept only super villains containing the word "Queen"
            .subscribe(System.out::println);

    }

}
