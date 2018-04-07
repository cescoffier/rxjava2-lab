package me.escoffier.lab.chapter2;


import io.reactivex.Observable;

import static me.escoffier.lab.chapter2.HotStream.nap;

public class Code8 {

    public static void main(String... args) {
        Observable<Integer> stream = HotStream.create();

        stream
            .subscribe(
                i -> System.out.println("[A] Received: " + i),
                err -> System.out.println("[A] BOOM"),
                () -> System.out.println("[A] Completion")
            );

        nap();

        stream
            .subscribe(
                i -> System.out.println("[B] Received: " + i),
                err -> System.out.println("[B] BOOM"),
                () -> System.out.println("[B] Completion")
            );

    }
}
