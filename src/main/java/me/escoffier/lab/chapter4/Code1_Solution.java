package me.escoffier.lab.chapter4;

import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.villains;

public class Code1 {

    public static void main(String[] args) {
        SuperHeroesService.run();

        villains()
            .filter(name -> name.contains("Queen"))
            .subscribe(System.out::println);

    }

}
