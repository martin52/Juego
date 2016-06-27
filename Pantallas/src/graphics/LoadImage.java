package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImage {

	public static BufferedImage motors, playerMotor, enemyAmarillo, enemyAceite, fuelCar,
	fullImage, road, grass, footPath, llegadaTile;//, subImage1, subImage2;

	public static void init() {
		fullImage = imageLoader("/grid.jpg");
		playerMotor = imageLoader("/motor.gif");
		enemyAmarillo = imageLoader("/enemyAmarillo.gif");
		enemyAceite = imageLoader("/aceite.gif");
		fuelCar = imageLoader("/FuelCar.gif");
		llegadaTile = imageLoader("/llegada.jpg");
		crop();
	}

	public static BufferedImage imageLoader(String path) {
		try {
			return ImageIO.read(LoadImage.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		return null;
	}

	public static void crop() {
		//subImage1 = fullImage.getSubimage(100, 200, 200, 200);
		//subImage2 = fullImage.getSubimage(100, 200, 200, 200);
		grass = fullImage.getSubimage(0, 0, 130, 119);
		road = fullImage.getSubimage(130, 0, 130, 119);
		footPath = fullImage.getSubimage(260, 0, 130, 119);
		
	}
}
