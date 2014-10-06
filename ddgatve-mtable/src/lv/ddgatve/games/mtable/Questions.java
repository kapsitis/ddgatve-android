package lv.ddgatve.games.mtable;

import java.util.Random;

public class Questions {

	private int max;
	private int first;
	private int second;
	Random r = new Random();
	private int type; // 1 - addition; 2 - subtraction; 3 - multiplication; 4-
						// division

	public Questions(int max) {
		this.max = max;
		next();
	}

	public void next() {
		int temp = r.nextInt(10);
		if (temp < 1 || temp > 4) {
			type = 3;
		} else {
			type = temp;
		}
		if (type == 3) {
			first = r.nextInt(19) + 2;
			int maxSecond = (int) Math.floor((double) max / first);
			second = r.nextInt(maxSecond - 1) + 2;
		} else if (type == 1) {
			first = r.nextInt(max + 1);
			int maxSecond = max - first;
			second = r.nextInt(maxSecond + 1);
		} else if (type == 2) {
			first = r.nextInt(max + 1);
			int maxSecond = first;
			second = r.nextInt(maxSecond + 1);
		} else if (type == 4) {
			second = r.nextInt(19) + 2;
			int maxSecond = (int) Math.floor((double) max / second);
			int answer = r.nextInt(maxSecond - 1) + 2;
			first = answer * second;
		}
	}

	public String getQuestion() {
		String[] operators = new String[] { "*", "+", "-", "*", "/" };
		if (type >= 1 && type <= 4) {
			return "" + first + operators[type] + second;
		} else {
			return "0*0";
		}
	}

	public int getType() {
		return type;
	}

	public int getAnswer() {
		if (type == 1) {
			return first + second;
		} else if (type == 2) {
			return first - second;
		} else if (type == 3) {
			return first * second;
		} else if (type == 4) {
			return first / second;
		} else {
			return 0;
		}
	}
	
	

	// public static void main(String[] args) {
	// Questions q = new Questions(100);
	// for (int i = 0; i < 100; i++) {
	// System.out.println(q.nextQuestion() + " = " + q.getAnswer());
	// }
	// }

}
