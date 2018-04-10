package me.escoffier.lab.chapter4;

import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.villains;

public class Code2_Solution {

    public static void main(String[] args) {
        SuperHeroesService.run();

        villains()
            .skip(20)
            .take(10)
            .subscribe(System.out::println);
    }

}
