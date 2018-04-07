package me.escoffier.lab.chapter2;


import io.reactivex.Observable;

import java.util.Scanner;

public class Code6 {

    public static void main(String... args) {
        Observable<String> stream = Observable.create(subscriber -> {
            boolean done = false;
            Scanner scan = new Scanner(System.in);
            while(! done) {
                String input = scan.next();
                if (input.contains("done")) {
                    done = true;
                    subscriber.onComplete();
                } else if (input.contains("error")) {
                    subscriber.onError(new Exception(input));
                } else {
                    subscriber.onNext(input);
                }
            }
        });

        stream
            .subscribe(
                i -> System.out.println("Received: " + i),
                err -> System.out.println("BOOM"),
                () -> System.out.println("Completion")
            );

    }
}
