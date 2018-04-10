package me.escoffier.lab.chapter4;


import me.escoffier.superheroes.SuperStuff;

import static me.escoffier.superheroes.Helpers.villains;

public class Code8 {

    public static void main(String[] args) {
        villains()
            .map(SuperStuff::getName)
            .toList()
            .subscribe(list -> System.out.println("Collected " + list.size() + " names"));
    }

}
