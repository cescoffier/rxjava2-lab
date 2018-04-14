package me.escoffier.lab.chapter4;


import me.escoffier.superheroes.Character;

import static me.escoffier.superheroes.Helpers.villains;

public class Code8 {

    public static void main(String[] args) {
        villains()
            .map(Character::getName)
            .toList()
            .subscribe(list -> System.out.println("Collected " + list.size() + " names"));
    }

}
