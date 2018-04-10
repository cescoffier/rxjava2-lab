package me.escoffier.lab.chapter4;

import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.villains;

public class Code3_Solution {

    public static void main(String[] args) {
        SuperHeroesService.run();

        villains()
            .distinct()
            .subscribe(System.out::println);
    }

}
