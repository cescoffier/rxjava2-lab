package me.escoffier.superheroes;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.core.file.FileSystem;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;

import java.util.Map;
import java.util.stream.Collectors;

public class Helpers {

    public static FileSystem fs() {
        return Vertx.vertx().fileSystem();
    }

    public static WebClient client() {
        Vertx vertx = Vertx.vertx();
        return WebClient.create(vertx,
            new WebClientOptions().setDefaultPort(8080).setDefaultHost("localhost")
        );
    }

    public static Observable<String> villains_names() {
        return client()
            .get("/villains")
            .rxSend()
            .map(HttpResponse::bodyAsJsonObject)
            .map(json -> json.stream().map(Map.Entry::getValue).collect(Collectors.toList()))
            .flatMapObservable(Observable::fromIterable)
            .cast(String.class);
    }

    public static Observable<String> heroes_names() {
        return client()
            .get("/heroes")
            .rxSend()
            .map(HttpResponse::bodyAsJsonObject)
            .map(json -> json.stream().map(Map.Entry::getValue).collect(Collectors.toList()))
            .flatMapObservable(Observable::fromIterable)
            .cast(String.class);
    }

    public static Flowable<SuperStuff> heroes() {
        return fs().rxReadFile("src/main/resources/entities.json")
            .map(Buffer::toJsonArray)
            .flatMapPublisher(Flowable::fromIterable)
            .cast(JsonObject.class)
            .map(j -> j.mapTo(SuperStuff.class))
            .filter(s -> ! s.isVillain());
    }

    public static Flowable<SuperStuff> villains() {
        return fs().rxReadFile("src/main/resources/entities.json")
            .map(Buffer::toJsonArray)
            .flatMapPublisher(Flowable::fromIterable)
            .cast(JsonObject.class)
            .map(j -> j.mapTo(SuperStuff.class))
            .filter(SuperStuff::isVillain);
    }


}
