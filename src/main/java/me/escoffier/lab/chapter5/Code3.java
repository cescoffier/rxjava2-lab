package me.escoffier.lab.chapter5;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.file.FileSystem;
import me.escoffier.superheroes.Character;

public class Code3 {

    public static void main(String[] args) {
        load()
            .subscribe(System.out::println, Throwable::printStackTrace);
    }

    static Flowable<Character> load() {
        Vertx vertx = Vertx.vertx();
        FileSystem fileSystem = vertx.fileSystem();
        return fileSystem.rxReadFile("src/main/resources/characters.json")
            .map(buffer -> buffer.toString())
            .map(content -> new JsonArray(content))
            .flatMapPublisher(array -> Flowable.fromIterable(array))
            .cast(JsonObject.class)
            .map(json -> json.mapTo(Character.class));
    }
}
