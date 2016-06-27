package manager;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import motor.Motor;
import world.World;
import display.Display;
import enemies.EnemyAceite;
import enemies.EnemyAmarillo;
import enemies.FuelCar;
import graphics.LoadImage;

public class GameManager {
	private Motor motor;
	private World world;
	private long time = System.nanoTime();
	private long delay;
	private long delayAceite;
	private double fuel;
	private int score;

	private ArrayList<EnemyAmarillo> eMotor;
	private ArrayList<EnemyAceite> eAceite;
	private ArrayList<FuelCar> aFuelCar;

	public GameManager() {
		motor = new Motor();
		world = new World(motor);
		eMotor = new ArrayList<EnemyAmarillo>();
		eAceite = new ArrayList<EnemyAceite>();
		aFuelCar = new ArrayList<FuelCar>();
		delay = 2000;
		delayAceite = 2001;
		fuel = 100;
		score = 0;
	}

	public void init() {
		// Timer timer = new Timer();
		// int segundos=0;
		// world.init();
		// motor = new Motor();

		// world = new World(motor);
		LoadImage.init();
		motor.init();
	}

	public void tick() {
		// motor
		motor.tick();

		// ///////////////////////ENEMY AMARILLO
		Random rand = new Random();
		int randX = rand.nextInt(300);
		int randY = rand.nextInt(Display.height);

		while (randX < 150) {
			randX = rand.nextInt(300);
		}
		for (int i = 0; i < eMotor.size(); i++) {
			// player
			int px = motor.getX();
			int py = motor.getY();

			// enemyAmarillo
			int ex = eMotor.get(i).getX();
			int ey = eMotor.get(i).getY();

			// if(r1.x < r2.x +width && r1.x + width > r2r2.x && r1.y < r2.y +
			// width && r1.y + width > r2.y)

			if (px < ex + 40 && px + 40 > ex && py < ey + 40 && py + 40 > ey) {
				// collided
				eMotor.remove(i);
				i--;
				fuel -= 10;
				score -= 50;
				if(score < 0)
					score = 0;
				motor.setSpeed(0);
				motor.setFuel(fuel);
				motor.setScore(score);
			}
		}
		long elapsed = (System.nanoTime() - time) / 1000000;
		if (elapsed > delay) {
			if (motor.getSpeed() >= 3) {
				eMotor.add(new EnemyAmarillo(motor, randX, (-randY)
						+ motor.getOffset()));
			}
			time = System.nanoTime();
		}

		for (int i = 0; i < eMotor.size(); i++) {
			eMotor.get(i).tick();
		}

		// ////////////////////////////ACEITE
		int randAX = rand.nextInt(300);
		int randAY = rand.nextInt(Display.height);

		while (randAX < 150) {
			randAX = rand.nextInt(300);
		}

		for (int i = 0; i < eAceite.size(); i++) {
			// player
			int px = motor.getX();
			int py = motor.getY();
			// enemyAceite
			int aceiteX = eAceite.get(i).getX();
			int aceiteY = eAceite.get(i).getY();
			if (px < aceiteX + 40 && px + 40 > aceiteX && py < aceiteY + 40
					&& py + 40 > aceiteY) {
				// collided
				eAceite.remove(i);
				i--;
				fuel -= 10;
				score -= 20;
				if(score < 0)
					score = 0;
				motor.setSpeed(0);
				motor.setFuel(fuel);
				motor.setScore(score);
			}
		}

		if (elapsed > delayAceite) {
			if (motor.getSpeed() >= 3) {
				eAceite.add(new EnemyAceite(motor, randAX, (-randAY)
						+ motor.getOffset()));
			}
			time = System.nanoTime();
		}
		for (int i = 0; i < eAceite.size(); i++) {
			eAceite.get(i).tick();
		}

		// /////////////////////FUEL CAR
		int randFuelCarX = rand.nextInt(300);
		int randFuelCarY = rand.nextInt(Display.height);

		while (randFuelCarX < 150) {
			randFuelCarX = rand.nextInt(300);
		}
		
		for (int i = 0; i < aFuelCar.size(); i++) {
			// player
			int px = motor.getX();
			int py = motor.getY();
			// fuelCar
			int fuelCarX = aFuelCar.get(i).getX();
			int fuelCarY = aFuelCar.get(i).getY();
			if (px < fuelCarX + 40 && px + 40 > fuelCarX && py < fuelCarY + 40
					&& py + 40 > fuelCarY) {
				// collided
				aFuelCar.remove(i);
				i--;
				fuel += 10;
				score += 100;
				motor.setScore(score);
				motor.setFuel(fuel);

			}
		}
		
		if (elapsed > delay) {
			if (motor.getSpeed() >= 3) {
				aFuelCar.add(new FuelCar(motor, randFuelCarX, (-randFuelCarY)
						+ motor.getOffset()));
			}
			time = System.nanoTime();
		}
		for (int i = 0; i < aFuelCar.size(); i++) {
			aFuelCar.get(i).tick();
		}

	}

	public void render(Graphics g) {
		// g.drawImage(LoadImage.fullImage, 0, 0, 800, 600, null);
		// g.drawImage(LoadImage.subImage1, 150, 150, null);
		// g.drawImage(LoadImage.subImage2, 300, 300, null);
		world.render(g);
		motor.render(g);
		for (int i = 0; i < eAceite.size(); i++) {
			eAceite.get(i).render(g);
		}
		for (int i = 0; i < eMotor.size(); i++) {
			eMotor.get(i).render(g);
		}
		for (int i = 0; i < aFuelCar.size(); i++) {
			aFuelCar.get(i).render(g);
		}

	}
}
