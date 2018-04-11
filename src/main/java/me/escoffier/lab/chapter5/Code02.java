package me.escoffier.lab.chapter5;

import io.reactivex.Flowable;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.core.file.FileSystem;
import me.escoffier.superheroes.SuperStuff;

public class Code02 {

	static Flowable<SuperStuff> getHeroes() {
		Vertx vertx = Vertx.vertx();
		FileSystem fileSystem = vertx.fileSystem();
        return fileSystem.rxReadFile("src/main/resources/entities.json")
                .map(Buffer::toJsonArray)
                .flatMapPublisher(Flowable::fromIterable)
                .cast(JsonObject.class)
                .map(j -> j.mapTo(SuperStuff.class))
                .filter(s -> ! s.isVillain());
	}
	
	

    public static void main(String[] args) {
    	getHeroes().subscribe(System.out::println, Throwable::printStackTrace);
    }
}
