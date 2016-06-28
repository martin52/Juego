package juego;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import manager.GameManager;
import display.Display;

public class GameSetup implements Runnable {

	private Thread thread;
	private Display display;
	private String title;
	private int height;
	private int width;
	private BufferStrategy buffer;
	private Graphics g;
	private GameManager manager;

	public GameSetup(String title, int width, int height) {
		this.height = height;
		this.width = width;
		this.title = title;
	}

	public void init() {
		display = new Display(title, width, height);
		manager = new GameManager();
		
		//Creo que acá tengo que guardar los datos del GameManager para enviarselos a los demas jugadores
		manager.init();
	}

	public synchronized void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();

		}
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		manager.tick();
	}

	public void render() {
		buffer = display.canvas.getBufferStrategy();
		if (buffer == null) {
			display.canvas.createBufferStrategy(3);
			return;
		}
		g = buffer.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		// draw
		//g.setColor(Color.RED);
		//g.fillRect(12, y, 40, 40);
		manager.render(g);
		// draw end
		buffer.show();
		g.dispose();
	}

	
	public void run() {
		init();
		int fps = 50;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long outLoopTime = System.nanoTime();

		while (true) {
			delta = delta + (System.nanoTime() - outLoopTime) / timePerTick;
			outLoopTime = System.nanoTime();
			if (delta >= 1) {
				tick();
				render();
				delta--;
			}
		}

	}
}
