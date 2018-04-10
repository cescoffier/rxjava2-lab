package me.escoffier.lab.chapter5;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Code10 {

	static void forcedConsuming(Observer<String> subscriber) {
		Observable.create(emitter -> {
			DirectoryStream<Path> stream;
			try {
				stream = Files.newDirectoryStream(new File(".").toPath());
			} catch (IOException e) {
				emitter.onError(e);
				return;
			}
			for(Path path : stream)
				emitter.onNext(path);
			stream.close();
			emitter.onComplete();
		}).map(path -> path.toString()).subscribe(subscriber);
	}

    public static void main(String[] args) throws InterruptedException {
    	forcedConsuming(new Observer<String>() {

			@Override
			public void onSubscribe(Disposable d) {
			}

			@Override
			public void onNext(String t) {
				System.out.println("File: "+t);				
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
}
