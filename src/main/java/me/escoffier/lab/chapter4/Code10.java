package me.escoffier.lab.chapter4;


import io.reactivex.Flowable;
import io.reactivex.Single;

public class Code9 {

    public static void main(String[] args) {
        String text = "Super heroes and super villains";
        Single.just(text)
            .flatMapPublisher(s -> Flowable.fromArray(s.split(" ")))
            .subscribe(System.out::println);
    }

}
