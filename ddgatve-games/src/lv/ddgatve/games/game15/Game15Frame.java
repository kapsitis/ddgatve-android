package lv.ddgatve.games.game15;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game15Frame {

	int[][] slots;

	int rows;
	int cols;

	private static Game15Frame instance;

	public static Game15Frame getInstance() {
		if (instance == null) {
			instance = new Game15Frame(4, 4);
		}
		return instance;
	}

	private Game15Frame(int rows, int cols) {
		slots = new int[rows][cols];
		this.rows = rows;
		this.cols = cols;
		scramble();
	}

	public int getSlot(int row, int col) {
		return slots[row][col];
	}

	public int getSlotByNum(int num) {
		return getSlot(num / cols, num % cols);
	}

	public void scramble() {
		Random r = new Random();

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

	// public void move(int tile) {
	// List<Integer> pos = find(tile);
	// int posX = pos.get(0);
	// int posY = pos.get(1);
	// List<Integer> empty = find(0);
	// int emptyX = empty.get(0);
	// int emptyY = empty.get(1);
	// int distance = Math.round(Math.abs(posX - emptyX)
	// + Math.abs(posY - emptyY));
	// if (distance == 1) {
	// slots[posX][posY] = 0;
	// slots[emptyX][emptyY] = tile;
	// }
	// }

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

	public boolean isFinished() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (slots[i][j] != i * cols + j + 1) {
					return false;
				}
			}
		}
		return true;
	}
}
