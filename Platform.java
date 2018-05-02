package spel;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform extends Rectangle {
	
	public static ArrayList<Platform> platforms = new ArrayList<Platform>();
	public static double PLATFORM_SPEED = 5;
	public Platform(int width, int height) {
		
		platforms.add(this);
		this.setFill(Color.BLACK);
		this.setWidth(width);
		this.setHeight(height);
	}
}
