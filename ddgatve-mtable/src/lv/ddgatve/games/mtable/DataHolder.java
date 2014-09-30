package lv.ddgatve.games.mtable;

import java.util.Date;

public class DataHolder {

	private static DataHolder instance;

	private Questions q;

	private int totalCorrect;
	
	private long startMilliseconds;

	private DataHolder() {
		q = new Questions(150);
		totalCorrect = 0;
		startMilliseconds = (new Date()).getTime();
	}
	
	public static DataHolder getInstance() {
		if (instance == null) {
			instance = new DataHolder();
		}
		return instance;
	}

	public String getQuestion() {
		return q.getQuestion();
	}
	
	public void nextQuestion() {
		q.next();
	}
	
	public int getAnswer() {
		return q.getAnswer();
	}
	
	public int getTotalCorrect() {
		return totalCorrect;
	}

	public void incrementTotalCorrect() {
		totalCorrect++;
	}
	
	public String timeDifference() {
		long current = (new Date()).getTime();
		int difference = (int) Math.round(1.0*(current - startMilliseconds)/1000);
		int minutes = difference / 60; 
		int seconds = difference - 60*minutes;
		return minutes + " minūtes, " + seconds + " sekundes";  
	}
	
	public void reset() {
		totalCorrect = 0;
		q = new Questions(150);
		startMilliseconds = (new Date()).getTime();
	}
}
