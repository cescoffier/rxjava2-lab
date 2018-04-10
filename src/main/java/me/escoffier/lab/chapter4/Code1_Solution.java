package me.escoffier.lab.chapter4;

import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.villains_names;

public class Code1_Solution {

    public static void main(String[] args) {
        SuperHeroesService.run();

        villains_names()
            .filter(name -> name.contains("Queen"))
            .subscribe(System.out::println);

    }

}
