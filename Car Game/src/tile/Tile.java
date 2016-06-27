package tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static Tile[] tiles = new Tile[24];
	
	public static Tile roadTile = new RoadTile(0);
	
	public static Tile grassTile = new GrassTile(1);
	public static Tile footPathTile = new FootPathTile(2);
	public static Tile llegada = new LlegadaTile(3);
			
	public BufferedImage texture;
	
	public static final int tileWidth = 64, tileHeight = 64;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		tiles[id] = this;
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, null);
	}
}
