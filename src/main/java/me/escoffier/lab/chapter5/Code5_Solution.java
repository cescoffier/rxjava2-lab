package me.escoffier.lab.chapter5;

import io.reactivex.Observable;
import io.reactivex.Single;
import me.escoffier.superheroes.Character;

import java.util.Collections;

public class Code5_Solution extends AbstractSuperAPI {

    public static void main(String[] args) {
        new Code5_Solution().hero()
            .subscribe(System.out::println, Throwable::printStackTrace);

        new Code5_Solution().villain()
            .subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Override
    public Single<Character> hero() {
        return load()
            .filter(character -> !character.isVillain())
            .toList()
            .map(list -> {
                Collections.shuffle(list);
                return list;
            })
            .flatMapObservable(list -> Observable.fromIterable(list))
            .firstOrError();
    }

    @Override
    public Single<Character> villain() {
        return load()
            .filter(character -> character.isVillain())
            .toList()
            .map(list -> {
                if (list.isEmpty()) {
                    throw new RuntimeException("No villains");
                }
                Collections.shuffle(list);
                return list.get(0);
            });
    }

}
