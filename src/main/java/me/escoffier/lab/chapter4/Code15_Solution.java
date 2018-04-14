package me.escoffier.lab.chapter4;


import io.reactivex.Single;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.codec.BodyCodec;
import me.escoffier.superheroes.Character;

import static me.escoffier.superheroes.Helpers.client;

public class Code15_Solution {

    public static void main(String[] args) {
        client()
            .get("/heroes/random")
            .as(BodyCodec.json(Character.class))
            .rxSend()
            .map(HttpResponse::body)
            .map(Character::getName)
            .onErrorResumeNext(Single.just("Clement, even if I'm not a superhero"))
            .subscribe(
                name -> System.out.println("Retrieved: " + name),
                err -> System.err.println("Oh no... something bad happened: " + err)
            );

    }

}
