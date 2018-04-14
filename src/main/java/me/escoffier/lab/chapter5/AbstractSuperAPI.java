package me.escoffier.lab.chapter5;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.file.FileSystem;
import me.escoffier.superheroes.Character;

/**
 * A bad implementation of the {@code SuperAPI}
 */
public class AbstractSuperAPI implements SuperAPI {

    protected Flowable<Character> load() {
        Vertx vertx = Vertx.vertx();
        FileSystem fileSystem = vertx.fileSystem();
        return fileSystem.rxReadFile("src/main/resources/characters.json")
            .map(buffer -> buffer.toString())
            .map(content -> new JsonArray(content))
            .flatMapPublisher(array -> Flowable.fromIterable(array))
            .cast(JsonObject.class)
            .map(json -> json.mapTo(Character.class));
    }

    @Override
    public Single<Character> hero() {
        return Single.error(new UnsupportedOperationException());
    }

    @Override
    public Single<Character> villain() {
        return Single.error(new UnsupportedOperationException());
    }

    @Override
    public Flowable<Character> heroes() {
        return Flowable.empty();
    }

    @Override
    public Flowable<Character> villains() {
        return Flowable.empty();
    }

    @Override
    public Maybe<Character> findByName(String name) {
        return Maybe.empty();

    }

    @Override
    public Single<Character> findByNameOrError(String name) {
        return Single.error(new Exception("Not found"));
    }
}
