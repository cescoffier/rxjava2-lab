package me.escoffier.lab.chapter5;

public class Code1 {

	static int blockingOperation() {
		System.out.println("Operation starting");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Operation done");
		return 42;
	}
	
	

    public static void main(String[] args) {
		System.out.println("Before operation");
    	int result = blockingOperation();
		System.out.println("After operation: "+result);
    }
}
