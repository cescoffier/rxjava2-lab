package me.escoffier.lab.chapter4;

import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.heroes_names;

public class Code4 {

    public static void main(String[] args) {
        SuperHeroesService.run();

        heroes_names()
            .filter(s -> s.equals("Asterix"))
            .defaultIfEmpty("Oh no... Asterix is not a super hero")
            .subscribe(System.out::println);
    }

}
