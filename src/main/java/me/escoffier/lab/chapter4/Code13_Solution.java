package me.escoffier.lab.chapter4;


import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.codec.BodyCodec;
import me.escoffier.superheroes.SuperHeroesService;
import me.escoffier.superheroes.SuperStuff;

import static me.escoffier.superheroes.Helpers.client;

public class Code13 {

    public static void main(String[] args) {
        SuperHeroesService.run();

        Single<SuperStuff> random_heroes = client()
            .get("/heroes/random")
            .as(BodyCodec.json(SuperStuff.class))
            .rxSend()
            .map(HttpResponse::body);

        Single<SuperStuff> random_villains = client()
            .get("/villains/random")
            .as(BodyCodec.json(SuperStuff.class))
            .rxSend()
            .map(HttpResponse::body);

        random_heroes.zipWith(random_villains, (h, v) -> fight(h, v))
            .subscribe(j -> System.out.println(j.encodePrettily()));

    }

    private static JsonObject fight(SuperStuff h, SuperStuff v) {
        String winner = h.getName();
        if (v.getSuperpowers().size() > h.getSuperpowers().size()) {
            winner = v.getName();
        } else if (v.getSuperpowers().size() == h.getSuperpowers().size()) {
            winner = "none";
        }
        return new JsonObject()
            .put("hero", h.getName())
            .put("villain", v.getName())
            .put("winner", winner);
    }
}
