package me.escoffier.lab.chapter3;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import me.escoffier.lab.chapter2.HotStream;

public class Code10 {

    public static void main(String[] args) {
        // Create an observable emitting all numbers between 1 and 999_999_999
        Flowable.range(1, 999_999_999)
            .map(Item::new)
            // Emissions are made on the caller thread (main)
            // The next processing stages and the terminal subscriber
            // is now called on a separate thread (io thread).
            .observeOn(Schedulers.io())
            .subscribe(
                item -> {
                    nap();
                    System.out.println("Received : " + item.i);
                }
            );

        // Wait for 20 seconds. Without this the process will terminate immediately.
        HotStream.nap(20);
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
