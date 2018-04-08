package me.escoffier.lab.chapter3;


import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.client;

public class Code5_Solution {


    public static void main(String[] args) {
        SuperHeroesService.run();
        String name1 = "Yoda";
        String name2 = "clement";

        client().get("/heroes").rxSend()
            .map(HttpResponse::bodyAsJsonObject)
            .filter(json -> contains(name1, json))
            .subscribe(
                x -> System.out.println("Yes, " + name1 + " is a super hero"),
                Throwable::printStackTrace,
                () -> System.out.println("No, " + name1 + " is not a super hero")
            );


        client().get("/heroes").rxSend()
            .map(HttpResponse::bodyAsJsonObject)
            .filter(json -> contains(name2, json))
            .subscribe(
                x -> System.out.println("Yes, " + name2 + " is a super hero"),
                Throwable::printStackTrace,
                () -> System.out.println("No, " + name2 + " is not a super hero")
            );
    }

    private static boolean contains(String name, JsonObject json) {
        return json.stream().anyMatch(e -> e.getValue().toString().equalsIgnoreCase(name));
    }
}
