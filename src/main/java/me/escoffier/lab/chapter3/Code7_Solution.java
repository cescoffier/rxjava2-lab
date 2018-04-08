package me.escoffier.lab.chapter3;

import io.vertx.reactivex.core.buffer.Buffer;

import static me.escoffier.superheroes.Helpers.fs;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class Code7_Solution {

    public static void main(String[] args) {
        fs()
            .rxWriteFile(
                "hello.txt", Buffer.buffer("hello")
            )
            .subscribe(
                () -> System.out.println("File written"),
                Throwable::printStackTrace
            );
    }
}
