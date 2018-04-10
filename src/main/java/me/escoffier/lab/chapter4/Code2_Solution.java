package me.escoffier.lab.chapter4;

import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.villains_names;

public class Code2_Solution {

    public static void main(String[] args) {
        SuperHeroesService.run();

        villains_names()
            .skip(20)
            .take(10)
            .subscribe(System.out::println);
    }

}
