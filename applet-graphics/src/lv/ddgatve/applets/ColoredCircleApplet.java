package lv.ddgatve.applets;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JApplet;

public class ColoredCircleApplet extends JApplet {
	final static int WIDTH = 150;
	final static int HEIGHT = 150;
	final static int RADIUS = 16;

	public void paint(Graphics g) {

		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.red);

		g.fillOval((WIDTH / 2 - RADIUS), (HEIGHT / 2 - RADIUS), RADIUS * 2,
				RADIUS * 2);
	}

}
