package me.escoffier.lab.chapter4;

import io.reactivex.Observable;
import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.heroes_names;

public class Code3 {

    public static void main(String[] args) {
        SuperHeroesService.run();

        heroes_names()
            .filter(s -> s.equals("Asterix"))
            .switchIfEmpty(Observable.just("Oh", "no", "...", "Asterix", "is", "not", "a", "super", "hero"))
            .subscribe(System.out::println);
    }

}
