package me.escoffier.lab.chapter5;

import io.reactivex.Single;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.file.FileSystem;

public class Code1 {

	public static void main(String[] args) {
		getFile().subscribe(System.out::println, Throwable::printStackTrace);
	}

	static Single<String> getFile() {
		Vertx vertx = Vertx.vertx();
		FileSystem fileSystem = vertx.fileSystem();
		return fileSystem.rxReadFile("src/main/resources/characters.json")
				.map(buffer -> buffer.toString());
	}
}
