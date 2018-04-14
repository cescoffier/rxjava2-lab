package me.escoffier.lab.chapter7;

import io.reactivex.Single;

import java.io.IOException;

public class Code03 {

    public static void main(String[] args) {
        Single.error(new IOException("Source closed"))
                .test()
                .assertNotComplete()
                .assertError(IOException.class)
                .assertError(t -> t.getMessage().equals("Source closed"));
    }
}
