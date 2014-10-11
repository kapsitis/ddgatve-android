package lv.hello;

public class HelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Sveicināta, pasaule!");
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		System.out.println("a + b = " + (a + b));

	}

}
