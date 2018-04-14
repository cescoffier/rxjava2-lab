package me.escoffier.lab.chapter5;

import io.reactivex.Maybe;
import io.reactivex.Single;
import me.escoffier.superheroes.Character;

public class Code6_Solution extends AbstractSuperAPI {

    public static void main(String[] args) {
        new Code6_Solution().findByName("SuperGirl")
            .subscribe(
                c -> System.out.println(c.getName() + " is a super " + (c.isVillain() ? "villain" : "hero")),
                Throwable::printStackTrace,
                () -> System.out.println("Nope"));

        new Code6_Solution().findByName("Clement")
            .subscribe(
                c -> System.out.println(c.getName() + " is a super " + (c.isVillain() ? "villain" : "hero")),
                Throwable::printStackTrace,
                () -> System.out.println("No, Clement is not a " + "super hero (and not a super villain either despite the rumor)"));

        new Code6_Solution().findByNameOrError("Yoda")
            .subscribe(
                c -> System.out.println(c.getName() + " is a super " + (c.isVillain() ? "villain" : "hero")),
                Throwable::printStackTrace);

        new Code6_Solution().findByNameOrError("Clement")
            .subscribe(
                c -> System.out.println(c.getName() + " is a super " + (c.isVillain() ? "villain" : "hero")),
                t -> System.out.println("The lookup as failed, as expected, Clement is neither a super hero or super villain"));
    }

    @Override
    public Maybe<Character> findByName(String name) {
        return load()
            .filter(c -> c.getName().equalsIgnoreCase(name))
            .firstElement();
    }

    @Override
    public Single<Character> findByNameOrError(String name) {
        return load()
            .filter(c -> c.getName().equalsIgnoreCase(name))
            .firstOrError();
    }
}
