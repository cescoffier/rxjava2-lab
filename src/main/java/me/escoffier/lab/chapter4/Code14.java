package me.escoffier.lab.chapter4;


import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.codec.BodyCodec;
import me.escoffier.superheroes.Character;

import static me.escoffier.superheroes.Helpers.client;

public class Code14 {

    public static void main(String[] args) {
        client()
            .get("/heroes/random")
            .as(BodyCodec.json(Character.class))
            .rxSend()
            .map(HttpResponse::body)
            .map(Character::getName)
            .subscribe(
                name -> System.out.println("Retrieved: " + name),
                err -> System.err.println("Oh no... something bad happened: " + err)
            );

    }

}
