package me.escoffier.lab.chapter5;

import io.reactivex.Single;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.file.FileSystem;

public class Code1 {

	static Single<String> getHeroesFile() {
		Vertx vertx = Vertx.vertx();
		FileSystem fileSystem = vertx.fileSystem();
		return fileSystem.rxReadFile("src/main/resources/entities.json")
				.map(buffer -> buffer.toString());
	}

    public static void main(String[] args) {
    	getHeroesFile().subscribe(System.out::println, Throwable::printStackTrace);
    }
}
