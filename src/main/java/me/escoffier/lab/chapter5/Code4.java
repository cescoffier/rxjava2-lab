package me.escoffier.lab.chapter5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

public class Code4 {

	static Single<Integer> blockingOperation() {
		System.out.println("Operation starting");
		return Single.just(42)
				.delay(1, TimeUnit.SECONDS)
				.doAfterTerminate(() -> System.out.println("Operation done"));
	}
	
	

    public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("Before operation");
    	Integer result = blockingOperation()
    	.toFuture().get();
		System.out.println("After operation: "+result);
    }
}
