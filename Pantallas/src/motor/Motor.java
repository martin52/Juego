package motor;

import graphics.LoadImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import tile.Tile;
import display.Display;

public class Motor implements KeyListener {
	private int x, y;
	private int offset;

	private double speed;

	// direcciones
	private boolean left, right, up, down;

	private double fuel;
	private int gear;
	private int score;

	public Motor() {
		this.x = Display.width / 4;
		this.y = Tile.tileHeight * 120;
		offset = 0;

		speed = 0f;
		fuel = 100;
		gear = 0;
		score = 0;

	}

	public void init() {
		Display.frame.addKeyListener(this);

	}

	public void tick() {
		if (y > 700){
			this.fuel -= 0.05;
			//speed -= 0.5f;
		}
		System.out.println(y);
		if (fuel > 0) {

			offset = y - (Display.height - 100);
			if (right)
				if (x <= 349 && speed > 0)
					x += 5;
			if (left)
				if (x >= 121 && speed > 0)
					x -= 5;
			if (up) {
				if (y > 700) {
					speed += 3f;
					if (speed >= 20) {
						speed = 20;
					}
				}
			} else {
				speed -= 0.5f;
				if (speed <= 0)
					speed = 0;
			}
			if (y > 700)
				y -= speed;
			if (down) {
				// y += 1;
				// speed -= 0.015f;
				// if (speed <= 0)
				// speed = 0;

			}
		}
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getOffset() {
		return offset;
	}

	public void drawBoard(Graphics g) {
		int speed1 = (int) speed;
		switch (speed1) {
		case 0:
			gear = 0;
			break;
		case 2:
			gear = 1;
			break;
		case 4:
			gear = 2;
			break;
		case 6:
			gear = 3;
			break;
		}
		//g.setColor(Color.black);
		//g.fillRect(750, 10, 400, 80);

		// draw stats
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 30));
		g.drawString("Score: " + score, 700, 40);
		g.drawString("Speed : " + Double.toString(speed * 20) + " km/h", 700,
				80);
		g.drawString("FUEL: " + (int) fuel, 700, 120);
		

	}

	public void finDePartida(Graphics g){
		g.setColor(Color.blue);
		g.setFont(new Font("arial", Font.BOLD, 33));
		g.drawString("Tu puntaje es: " + this.score, Display.width / 6, Display.height / 2);
	}
	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("arial", Font.BOLD, 33));
		g.drawString("Game Over ", Display.width / 6, Display.height / 2);

	}

	public void render(Graphics g) {
		if (fuel > 0) {
			g.setColor(Color.red);
			g.drawImage(LoadImage.playerMotor, x, y - offset, 40, 50, null);
			if(y < 700){
				finDePartida(g);
			}
		} else {
			gameOver(g);
		}
		// draw Board
		drawBoard(g);

	}

	public void keyPressed(KeyEvent e) {
		int source = e.getKeyCode();
		if (source == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (source == KeyEvent.VK_LEFT) {
			left = true;
		}
		if (source == KeyEvent.VK_UP) {
			up = true;
		}
		if (source == KeyEvent.VK_DOWN) {
			down = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int source = e.getKeyCode();
		if (source == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if (source == KeyEvent.VK_LEFT) {
			left = false;
		}
		if (source == KeyEvent.VK_UP) {
			up = false;
		}
		if (source == KeyEvent.VK_DOWN) {
			down = false;
		}
	}

	public void keyTyped(KeyEvent arg0) {

	}

	public void setScore(int score) {
		this.score = score;
	}
}
