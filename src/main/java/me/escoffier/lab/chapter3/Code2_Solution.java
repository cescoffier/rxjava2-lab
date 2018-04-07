package me.escoffier.lab.chapter3;


import io.reactivex.Single;

public class Code2_Solution {


    public static void main(String[] args) {
        Single.just("Superman")
            .subscribe(
                (name, err) -> {
                    if (err == null) {
                        System.out.println("Hello " + name);
                    } else {
                        err.printStackTrace();
                    }
                }
            );
    }
}
