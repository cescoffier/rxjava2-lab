package me.escoffier.lab.chapter5;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Code12 {
    private static final Path DIRECTORY = new File("src/main/resources/super/heroes").toPath();


    public static void main(String[] args) {
        getNamesWithForcedConsumption(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String t) {
                System.out.println("File: " + t);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
            }

        });
    }

    private static void getNamesWithForcedConsumption(Observer<String> subscriber) {
        Observable.<Path>create(emitter -> {
            DirectoryStream<Path> stream;
            try {
                stream = Files.newDirectoryStream(DIRECTORY);
            } catch (IOException e) {
                emitter.onError(e);
                return;
            }
            for (Path path : stream)
                emitter.onNext(path);
            stream.close();
            emitter.onComplete();
        })
            .map(path -> path.toFile().getName())
            .subscribe(subscriber);
    }
}
