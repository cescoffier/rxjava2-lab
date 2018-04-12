package me.escoffier.lab.chapter5;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import io.reactivex.Observable;

public class Code6 {

	static Observable<String> operationWithCleanup() {
		DirectoryStream<Path> stream;
		try {
			stream = Files.newDirectoryStream(new File(".").toPath());
		} catch (IOException e) {
			return Observable.error(e);
		}
		return Observable.fromIterable(stream)
				.map(path -> path.toString());
	}
	
	

    public static void main(String[] args) throws InterruptedException {
    	Observable<String> files = operationWithCleanup();
    	files.subscribe(value -> System.out.println("File: "+value),
    			x -> x.printStackTrace());
    }
}
