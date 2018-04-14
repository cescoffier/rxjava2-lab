package me.escoffier.lab.chapter5;

import io.reactivex.Maybe;
import io.reactivex.Single;
import me.escoffier.superheroes.Character;

public class Code6 extends AbstractSuperAPI {

    public static void main(String[] args) {
        new Code6().findByName("SuperGirl")
            .subscribe(
                c -> System.out.println(c.getName() + " is a super " + (c.isVillain() ? "villain" : "hero")),
                Throwable::printStackTrace,
                () -> System.out.println("Nope"));

        new Code6().findByName("Clement")
            .subscribe(
                c -> System.out.println(c.getName() + " is a super " + (c.isVillain() ? "villain" : "hero")),
                Throwable::printStackTrace,
                () -> System.out.println("No, Clement is not a " + "super hero (and not a super villain either despite the rumor)"));

        new Code6().findByNameOrError("Yoda")
            .subscribe(
                c -> System.out.println(c.getName() + " is a super " + (c.isVillain() ? "villain" : "hero")),
                Throwable::printStackTrace);

        new Code6().findByNameOrError("Clement")
            .subscribe(
                c -> System.out.println(c.getName() + " is a super " + (c.isVillain() ? "villain" : "hero")),
                t -> System.out.println("The lookup as failed, as expected, Clement is neither a super hero or super villain"));
    }

    @Override
    public Maybe<Character> findByName(String name) {
        // To implement
        return super.findByName(name);
    }

    @Override
    public Single<Character> findByNameOrError(String name) {
        // To implement
        return super.findByNameOrError(name);
    }
}
