package me.escoffier.lab.chapter5;

import io.reactivex.Single;
import me.escoffier.superheroes.Character;

import java.util.Arrays;

public class Code19_Solution {
    
    public static void main(String[] args) {
        System.out.println("Before operation");
        Character result = getBlockingSuperVillain().blockingGet();
        System.out.println("After operation: " + result);
    }

    private static Single<Character> getBlockingSuperVillain() {
        return Single.create(emitter ->
            new Thread(() -> {
                System.out.println("Operation starting");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    emitter.onError(e);
                    return;
                }
                emitter.onSuccess(new Character("Frog-Man",
                    Arrays.asList("super strength", "leaping", "mega agility", "French"),
                    false));
            }).start()
        );
    }

}
