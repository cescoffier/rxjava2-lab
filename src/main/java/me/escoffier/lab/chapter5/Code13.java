package me.escoffier.lab.chapter5;

import me.escoffier.superheroes.Character;

import java.util.Arrays;

public class Code13 {

    public static void main(String[] args) {
        System.out.println("Before operation");
        Character character = getBlockingSuperVillain();
        System.out.println("After operation: " + character);
    }

    private static Character getBlockingSuperVillain() {
        System.out.println("Operation starting");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignore me.
        }
        System.out.println("Operation done");
        return new Character("Frog-Man",
            Arrays.asList("super strength", "leaping", "mega agility", "French"),
            false);
    }

}
