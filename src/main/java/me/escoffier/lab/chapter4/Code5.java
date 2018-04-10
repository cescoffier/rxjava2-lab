package me.escoffier.lab.chapter4;


import io.reactivex.Flowable;

import java.util.HashSet;

import static me.escoffier.superheroes.Helpers.heroes;

public class Code5 {

    public static void main(String[] args) {
        Flowable.range(0, 10)
            .scan(0, (last_result, item) -> last_result + item)
            .subscribe(i -> System.out.println("[Scan] Got " + i));

        Flowable.range(0, 10)
            .reduce(0, (last_result, item) -> last_result + item)
            .subscribe(i -> System.out.println("[Reduce] Got " + i));
    }

}
