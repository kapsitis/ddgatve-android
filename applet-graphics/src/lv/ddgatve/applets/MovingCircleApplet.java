package lv.ddgatve.applets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JApplet;

public class MovingCircleApplet extends JApplet implements Runnable {

	public boolean isRunning = false;
	final static int WIDTH = 150;
	final static int HEIGHT = 150;
	final static int RADIUS = 16;
	
	int currentX = WIDTH/2;
	int currentY = HEIGHT/2;

	public void start() {
		isRunning = true;
		new Thread(this).start();
	}

	public void stop() {
		isRunning = false;
	}

	public void paint(Graphics g) {
		// Create Graphics2D object, cast g as a Graphics2D
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 150, 150);

		g2d.setColor(Color.RED);
		g2d.fillOval((currentX - RADIUS), (currentY - RADIUS), RADIUS * 2,
				RADIUS * 2);
	}

	public void run() {

		while (isRunning) {
			repaint();
			try {
				Thread.sleep(100);
				currentX+=1;
				currentY+=2;			
				if (currentX > WIDTH) {
					currentX -= WIDTH;					
				}
				if (currentY > HEIGHT) {
					currentY -= HEIGHT;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}