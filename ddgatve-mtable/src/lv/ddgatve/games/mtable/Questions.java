package lv.ddgatve.games.mtable;

import java.util.Random;

public class Questions {
	
	private int max;
	private int first;
	private int second;
	Random r = new Random();
	
	public Questions(int max) {
		this.max = max;
		next();
	}

	public void next() {
		first = r.nextInt(19) + 2;
		int maxSecond = (int) Math.floor((double)max/first);
		second = r.nextInt(maxSecond-1) + 2;
	}
	
	public String getQuestion() {
		return "" + first + "\u00D7" + second;
	}
	
	public int getAnswer() {
		return first*second;
	}
	
//	public static void main(String[] args) {
//		Questions q = new Questions(100);
//		for (int i = 0; i < 100; i++) {
//			System.out.println(q.nextQuestion() + " = " + q.getAnswer());
//		}
//	}

}

