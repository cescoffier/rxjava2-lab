package me.escoffier.lab.chapter2;


import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static me.escoffier.lab.chapter2.HotStream.nap;

public class Code9 {

    public static void main(String... args) {
        Observable<Integer> stream = HotStream.create();

        Disposable s1 = stream
            .subscribe(
                i -> System.out.println("[A] Received: " + i),
                err -> System.out.println("[A] BOOM"),
                () -> System.out.println("[A] Completion")
            );

        // Wait before starting the next subscriber
        nap();

        Disposable s2 = stream
            .subscribe(
                i -> System.out.println("[B] Received: " + i),
                err -> System.out.println("[B] BOOM"),
                () -> System.out.println("[B] Completion")
            );

        nap(5);

        // Cancel the subscription for A
        s1.dispose();

        nap(3);

        // Cancel the subscription for B
        s2.dispose();
    }
}
