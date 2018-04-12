package me.escoffier.lab.chapter5;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import io.reactivex.Observable;

public class Code3_Solution {

	static Observable<File> getAllFiles() {
		return Observable.create(emitter -> {
			Files.walkFileTree(new File(".").toPath(), new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path path, BasicFileAttributes attr) throws IOException {
					emitter.onNext(path.toFile());
					return FileVisitResult.CONTINUE;
				}
			});
			emitter.onComplete();
		});
	}

    public static void main(String[] args) throws InterruptedException {
    	getAllFiles().subscribe(System.out::println, Throwable::printStackTrace);
    }
}
