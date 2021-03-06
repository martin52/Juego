package enemies;

import graphics.LoadImage;

import java.awt.Graphics;

import motor.Motor;

public class EnemyAceite {
	private int x;
	private int y;
	private Motor motor;
	
	public EnemyAceite(Motor motor, int x, int y){
		this.motor = motor;
		this.x = x;
		this.y = y;
		
	}
	
	public void tick(){
		//y -= 1;
	}
	
	public void render(Graphics g){
		g.drawImage(LoadImage.enemyAceite, x, y-motor.getOffset(), 30, 30, null);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
