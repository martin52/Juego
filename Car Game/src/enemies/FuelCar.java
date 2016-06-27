package enemies;

import graphics.LoadImage;

import java.awt.Graphics;

import motor.Motor;

public class FuelCar {
	private int x;
	private int y;
	private Motor motor;
	
	public FuelCar(Motor motor, int x, int y){
		this.motor = motor;
		this.x = x;
		this.y = y;
		
	}
	
	public void tick(){
		y -= 1;
	}
	
	public void render(Graphics g){
		g.drawImage(LoadImage.fuelCar, x, y-motor.getOffset(), 30, 40, null);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
}
