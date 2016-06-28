package enemies;

import graphics.LoadImage;

import java.awt.Graphics;

import motor.Motor;

public class OtroJugador { //Hice copypaste de EnemyAmarillo, configurar despues como va
	private int x;
	private int y;
	private Motor motor;
	
	public OtroJugador(Motor motor, int x, int y){
		this.motor = motor;
		this.x = x;
		this.y = y;
		
	}
	
//	public void tick(){
//		y -= 1;
//	}
	
	public void render(Graphics g){
		g.drawImage(LoadImage.enemyAmarillo, x, y-motor.getOffset(), 40, 50, null);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
