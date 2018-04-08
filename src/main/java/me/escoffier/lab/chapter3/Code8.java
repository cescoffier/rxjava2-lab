package me.escoffier.lab.chapter3;

import io.reactivex.Observable;

public class Code8 {

    public static void main(String[] args) {
        // Create an observable emitting all numbers between 1 and 10000
        Observable.range(1, 10000)
            .map(Item::new)
            .subscribe(
                item -> {
                    nap();
                    System.out.println("Received : " + item.i);
                }
            );
    }


    private static class Item {
        private final int i;

        Item(int number) {
            System.out.println("Constructing item using " + number);
            this.i = number;
        }
    }

    private static void nap() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            // Ignore me.
        }
    }

}
