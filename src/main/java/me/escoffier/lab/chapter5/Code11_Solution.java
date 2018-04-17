package me.escoffier.lab.chapter5;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import io.reactivex.Observable;

public class Code11_Solution {

	private static final Path DIRECTORY = new File("src/main/resources/super/heroes").toPath();

	public static void main(String[] args) {
		Observable<String> files = getHeroesNames();
		files.subscribe(value -> System.out.println("Subscriber 1: " + value),
			Throwable::printStackTrace);
		files.subscribe(value -> System.out.println("Subscriber 2: " + value),
			Throwable::printStackTrace);
	}
	
	private static Observable<String> getHeroesNames() {
		return Observable.<Path>create(emitter -> {
			DirectoryStream<Path> stream;
			try {
				stream = Files.newDirectoryStream(DIRECTORY);
			} catch (IOException e) {
				emitter.onError(e);
				return;
			}
			for(Path path : stream) {
        emitter.onNext(path);
      }
			stream.close();
			emitter.onComplete();
		}).map(path -> path.toFile().getName());
	}

}
