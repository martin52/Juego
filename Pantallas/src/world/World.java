package world;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import display.Display;

import motor.Motor;
import tile.Tile;

public class World {
	
	private int[][] tile;
	private int width;
	private int height;
	private Motor motor;
	public World(Motor motor){
		loadWorld("res/world.txt");
		this.motor = motor;
	}
	
	/*
	public void loadWorld(String path){
		File f = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			f = new File(path);
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String datos[] = br.readLine().split(" ");
			int width = Integer.parseInt(datos[0]);
			int height = Integer.parseInt(datos[1]);
			
			tile = new int[width][height];
			for (int x = 0; x < width; x++) {
				datos = br.readLine().split(" ");
				for (int y = 0; y < height; y++) {
					tile[x][y] = Integer.parseInt(datos[y]);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
	*/ //CODIGO NUESTRO
	
	
	//CODIGO INDIO
	
	public String loadFile(String path){
		StringBuilder sr = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null){
				sr.append(line + "\n");
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sr.toString();
	}
	
	private int parseInt(String number){
		return Integer.parseInt(number);
	}
	
	private void loadWorld(String path){
		String file = loadFile(path);
		String[] space = file.split("\\s+");
		width = parseInt(space[0]);
		height = parseInt(space[1]);
		tile = new int[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tile[x][y] = parseInt( space[2 + (x+y*width)] );
			}
		}
		
	}
	
	//HASTA ACA
	public void render(Graphics g){
		
		int start = Math.max(0, (motor.getOffset())/Tile.tileHeight);
		int end = Math.min(height, (motor.getOffset() + Display.height +20) / Tile.tileHeight);
		for (int i = 0; i < width; i++) {
			for (int j = start; j < end; j++) {
				Tile t = Tile.tiles[tile[i][j]];
				
				t.render(g, i*Tile.tileWidth, (j*Tile.tileHeight) - motor.getOffset());
			}
		}
	}
}
