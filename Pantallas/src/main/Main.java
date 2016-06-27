package main;

import juego.GameSetup;

public class Main {

	public static void main(String[] args) {
		GameSetup game = new GameSetup("Road Fighter", 1024, 720);
		game.start();
	}

}
