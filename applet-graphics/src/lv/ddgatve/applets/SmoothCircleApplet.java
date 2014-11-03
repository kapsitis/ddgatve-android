package lv.ddgatve.applets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JApplet;

public class SmoothCircleApplet extends JApplet {
	final static int WIDTH = 150;
	final static int HEIGHT = 150;
	final static int RADIUS = 16;

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.red);

		g.fillOval((WIDTH / 2 - RADIUS), (HEIGHT / 2 - RADIUS), RADIUS * 2,
				RADIUS * 2);
	}

}
