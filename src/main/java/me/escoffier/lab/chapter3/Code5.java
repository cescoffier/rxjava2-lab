package me.escoffier.lab.chapter3;


import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.client;

public class Code5 {


    public static void main(String[] args) {
        SuperHeroesService.run();
        String name1 = "Yoda";
        String name2 = "clement";

        client().get("/heroes").rxSend()
            .map(HttpResponse::bodyAsJsonObject)
            // Use the filter operator and contains to check if the is a hero named `name1`

            // Don't forget to subscribe
         ;


        client().get("/heroes").rxSend()
            .map(HttpResponse::bodyAsJsonObject)
            // Use the filter operator and contains to check if the is a hero named `name2`

            // Don't forget to subscribe
        ;
    }

    private static boolean contains(String name, JsonObject json) {
        return json.stream().anyMatch(e -> e.getValue().toString().equalsIgnoreCase(name));
    }
}
