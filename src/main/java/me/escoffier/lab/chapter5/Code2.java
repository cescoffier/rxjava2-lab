package me.escoffier.lab.chapter5;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

public class Code2 {

	static Single<Integer> blockingOperation() {
		System.out.println("Operation starting");
		return Single.just(42)
				.delay(1, TimeUnit.SECONDS)
				.doAfterTerminate(() -> System.out.println("Operation done"));
	}
	
	

    public static void main(String[] args) throws InterruptedException {
		System.out.println("Before operation");
    	blockingOperation()
    	.subscribe(value -> System.out.println("After operation: "+value));
//		System.out.println("After operation");
//		Thread.sleep(2000);
    }
}
