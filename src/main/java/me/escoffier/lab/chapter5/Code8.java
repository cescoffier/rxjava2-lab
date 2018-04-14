package me.escoffier.lab.chapter5;

import io.reactivex.Observable;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Code8 {

    private static final Path DIRECTORY = new File("src/main/resources/super").toPath();

    public static void main(String[] args) {
        getFileNames().subscribe(System.out::println, Throwable::printStackTrace);
    }

    private static Observable<String> getFileNames() {
        return Observable.create(emitter -> {
            Files.walkFileTree(DIRECTORY,
                new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes attr) {
                        // ...
                        return FileVisitResult.CONTINUE;
                    }
                });
            // ...
        });
    }
}
