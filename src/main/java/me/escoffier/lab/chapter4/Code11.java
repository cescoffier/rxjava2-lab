package me.escoffier.lab.chapter4;


import io.reactivex.Observable;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import me.escoffier.superheroes.SuperHeroesService;

import static me.escoffier.superheroes.Helpers.client;

public class Code11 {

    public static void main(String[] args) {
        SuperHeroesService.run();


        Single<JsonObject> request1 = client()
            .get("/heroes")
            .rxSend()
            .map(HttpResponse::bodyAsJsonObject);



        request1
            // Transform the response to retrieve a stream of ids.
            .flatMapObservable(j -> Observable.fromIterable(j.fieldNames()))
            // Take the first one, as a Single
            .firstOrError()

            // Second request
            .flatMap(Code11::getHero)

            // Print the result
            .subscribe(json -> System.out.println(json.encodePrettily()));
            
    }

    private static Single<JsonObject> getHero(String s) {
        return client().get("/heroes/" + s).rxSend().map(HttpResponse::bodyAsJsonObject);
    }

}
