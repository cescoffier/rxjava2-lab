package me.escoffier.lab.chapter5;

import io.reactivex.Flowable;
import me.escoffier.superheroes.Character;

public class Code4 extends AbstractSuperAPI {

    public static void main(String[] args) {
        new Code4().heroes()
            .count()
            .subscribe(i -> System.out.println(i + " heroes loaded"), Throwable::printStackTrace);

        new Code4().villains()
            .count()
            .subscribe(i -> System.out.println(i + " villains loaded"), Throwable::printStackTrace);
    }

    @Override
    public Flowable<Character> heroes() {
        return load()
            // Select only heroes
        ;
    }

    @Override
    public Flowable<Character> villains() {
        return load()
            // Select only villains
        ;
    }
}
