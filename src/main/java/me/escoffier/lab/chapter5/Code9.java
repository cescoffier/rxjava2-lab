package me.escoffier.lab.chapter5;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import io.reactivex.Observable;

public class Code9 {

	private static final Path DIRECTORY = new File("src/main/resources/super/heroes").toPath();

	public static void main(String[] args) {
		getHeroesNames().subscribe(System.out::println, Throwable::printStackTrace);
	}

	private static Observable<String> getHeroesNames() {
		DirectoryStream<Path> stream;
		try {
			stream = Files.newDirectoryStream(DIRECTORY);
		} catch (IOException e) {
			return Observable.error(e);
		}
		return Observable.fromIterable(stream)
			.map(path -> path.toFile().getName())
            // ...
        ;
	}
}
