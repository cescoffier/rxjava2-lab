package me.escoffier.lab.chapter5;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import io.reactivex.Observable;

public class Code8_Solution {

	static Observable<String> operationWithCleanup() {
		return Observable.create(emitter -> {
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
		}).map(path -> path.toString());
	}
	
	

    public static void main(String[] args) throws InterruptedException {
    	Observable<String> files = operationWithCleanup();
    	files.subscribe(value -> System.out.println("File: "+value),
    			x -> x.printStackTrace());
    	files.subscribe(value -> System.out.println("File: "+value),
    			x -> x.printStackTrace());
    }
}
