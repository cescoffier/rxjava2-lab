package me.escoffier.lab.chapter5;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

import io.reactivex.Single;

public class Code2 {

	static Single<String> getHeroesFile() {
		File file = new File("src/main/resources/entities.json");
		return Single.<ByteBuffer>create(emitter -> {
			AsynchronousFileChannel channel = AsynchronousFileChannel.open(file.toPath());
			ByteBuffer buffer = ByteBuffer.allocate((int)file.length());
			channel.read(buffer, 0, null, new CompletionHandler<Integer,Void>(){

				@Override
				public void completed(Integer result, Void attachment) {
					try {
						channel.close();
					} catch (IOException e) {
						emitter.onError(e);
						return;
					}
					emitter.onSuccess(buffer);
				}

				@Override
				public void failed(Throwable error, Void attachment) {
					try {
						channel.close();
					} catch (IOException e) {
						// ignore
					}
					emitter.onError(error);
				}
			});
		}).map(buffer -> new String(buffer.array(), StandardCharsets.UTF_8));
	}

    public static void main(String[] args) throws InterruptedException {
    	getHeroesFile().subscribe(System.out::println, Throwable::printStackTrace);
    	Thread.sleep(2000);
    }
}
