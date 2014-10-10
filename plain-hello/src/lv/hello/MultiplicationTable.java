package lv.hello;

import java.util.Random;

public class MultiplicationTable {

	public static String[] intensities = new String[] { "00", "33", "66", "99", "CC", "FF" };

	public static String randomColor() {
		Random a = new Random();
		return "#" + intensities[a.nextInt(6)] + intensities[a.nextInt(6)]
				+ intensities[a.nextInt(6)];
	}

	public static void main(String[] args) {
		int nRows = Integer.parseInt(args[0]);
		int nCols = Integer.parseInt(args[1]);
		String[] colArray = new String[10];
		for (int c = 0; c < 10; c++) {
			colArray[c] = randomColor();
		}
		System.out.println("<table border='1'>\n");
		for (int i = 1; i <= nRows; i++) {
			System.out.println("<tr>");
			for (int j = 1; j <= nCols; j++) {
				int result = i * j;
				int colorNum = result * 10 / (nRows * nCols + 1);
				System.out.println("<td bgcolor='" + colArray[colorNum] + "'>"
						+ result + "</td>");
			}
			System.out.println("</tr>\n");
		}
		System.out.println("</table>");

	}
}
