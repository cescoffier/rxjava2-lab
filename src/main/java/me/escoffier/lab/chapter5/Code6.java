package me.escoffier.lab.chapter5;

import io.reactivex.Single;

public class Code6 {

	static Single<Integer> blockingOperation() {
		return Single.create(emitter -> {
			new Thread(() -> {
				System.out.println("Operation starting");
				// do the blocking call
				System.out.println("Operation done");
			}).start();
		});
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Before operation");
		Integer result = blockingOperation().blockingGet();
		System.out.println("After operation: " + result);
	}
}
