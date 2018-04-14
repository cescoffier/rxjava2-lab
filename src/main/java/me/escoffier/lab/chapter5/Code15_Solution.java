package me.escoffier.lab.chapter5;

import io.reactivex.Single;

public class Code15_Solution {

	static Single<Integer> blockingOperation() {
		return Single.create(emitter -> {
			new Thread(() -> {
				System.out.println("Operation starting");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					emitter.onError(e);
					return;
				}
				emitter.onSuccess(42);
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
