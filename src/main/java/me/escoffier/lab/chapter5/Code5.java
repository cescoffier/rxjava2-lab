package me.escoffier.lab.chapter5;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.core.file.FileSystem;
import me.escoffier.superheroes.Character;

public class Code5 {

	static Flowable<Character> getHeroesFromFile(Single<String> file) {
		Vertx vertx = Vertx.vertx();
		FileSystem fileSystem = vertx.fileSystem();
		return // ...
				fileSystem.rxReadFile("...")
                .map(Buffer::toJsonArray)
                .flatMapPublisher(Flowable::fromIterable)
                .cast(JsonObject.class)
                .map(j -> j.mapTo(Character.class))
                .filter(s -> ! s.isVillain());
	}
	
	

    public static void main(String[] args) {
    	Single<String> file = Single.just("src/main/resources/entities.json");
    	getHeroesFromFile(file).subscribe(System.out::println, Throwable::printStackTrace);
    }
}
