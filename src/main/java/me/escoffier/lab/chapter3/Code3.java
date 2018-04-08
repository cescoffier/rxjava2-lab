package me.escoffier.lab.chapter3;


import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.client;

public class Code3 {

    public static void main(String[] args) {
        SuperHeroesService.run();

        client().get("/heroes").rxSend()
            .map(HttpResponse::bodyAsJsonObject)
            .map(JsonObject::size)
            .subscribe(length -> System.out.println("Number of heroes: " + length));
    }
}
