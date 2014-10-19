package lv.ddgatve.games.game15;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game15Frame {

	int[][] slots;
	int[][] orderedSlots;

	int rows;
	int cols;

	private static Game15Frame instance;

	public static Game15Frame getInstance(int rows, int cols) {
		if (instance == null) {
			instance = new Game15Frame(rows, cols);
		}
		return instance;
	}

	private Game15Frame(int rows, int cols) {

		orderedSlots = new int[rows][cols];
		this.rows = rows;
		this.cols = cols;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				orderedSlots[i][j] = i * cols + j + 1;
			}
		}
		orderedSlots[rows - 1][cols - 1] = 0;
		evenScramble();
	}

	public int getSlot(int row, int col) {
		return slots[row][col];
	}

	public int getSlotByNum(int num) {
		return getSlot(num / cols, num % cols);
	}

	public void scramble() {
		slots = new int[rows][cols];
		Random r = new Random();
		slots[rows - 1][cols - 1] = -1;
		for (int i = 1; i <= rows * cols - 1; i++) {
			boolean found = false;
			while (!found) {
				int row = r.nextInt(rows);
				int col = r.nextInt(cols);
				if (slots[row][col] == 0) {
					slots[row][col] = i;
					found = true;
				}
			}
		}
		slots[rows - 1][cols - 1] = 0;
	}

	public void evenScramble() {
		boolean isEven = false;
		int inversions0 = countInversions(orderedSlots);
		while (!isEven) {
			scramble();
			int inversions1 = countInversions(slots);
			if (!isFinished() && (inversions0 - inversions1) % 2 == 0) {
				isEven = true;
			}
		}
	}

	public List<Integer> find(int tile) {
		int tileRow = -1;
		int tileCol = -1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (slots[i][j] == tile) {
					tileRow = i;
					tileCol = j;
				}
			}
		}
		return Arrays.asList(tileRow, tileCol);
	}

	public void move(int position) {
		int rowActive = position / cols;
		int colActive = position % cols;
		List<Integer> empty = find(0);
		int rowEmpty = empty.get(0);
		int colEmpty = empty.get(1);
		int distance = Math.round(Math.abs(rowActive - rowEmpty)
				+ Math.abs(colActive - colEmpty));
		if (distance == 1) {
			slots[rowEmpty][colEmpty] = slots[rowActive][colActive];
			slots[rowActive][colActive] = 0;
		}
	}

	public int countInversions(int[][] arg) {
		int[] seq = new int[rows * cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				seq[i * cols + j] = arg[i][j];
			}
		}
		int inversions = 0;
		for (int m = 0; m < seq.length - 1; m++) {
			for (int n = 0; n < m; n++) {
				if (seq[n] > seq[m]) {
					inversions++;
				}
			}
		}
		return inversions;
	}

	public boolean isFinished() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (slots[i][j] != orderedSlots[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
