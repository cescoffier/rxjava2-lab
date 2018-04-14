package me.escoffier.lab.chapter5;

import io.reactivex.Flowable;
import me.escoffier.superheroes.Character;

public class Code4_Solution extends AbstractSuperAPI {

    public static void main(String[] args) {
        new Code4_Solution().heroes()
            .count()
            .subscribe(i -> System.out.println(i + "heroes loaded"), Throwable::printStackTrace);

        new Code4_Solution().villains()
            .count()
            .subscribe(i -> System.out.println(i + "villains loaded"), Throwable::printStackTrace);
    }

    @Override
    public Flowable<Character> heroes() {
        return load()
            .filter(character -> !character.isVillain());
    }

    @Override
    public Flowable<Character> villains() {
        return load()
            .filter(character -> character.isVillain());
    }
}
