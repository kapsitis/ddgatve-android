package lv.ddgatve.applets;

import javax.swing.JApplet;
import java.awt.*;

// assume that the drawing area is 150 by 150
public class PlainCircleApplet extends JApplet {
	final int radius = 25;

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, 150, 150);
		g.setColor(Color.black);
		
		g.drawOval((150 / 2 - radius), (150 / 2 - radius), radius * 2,
				radius * 2);
	}
}
