package spel;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Missile extends Group {
	
	public static ArrayList<Missile> missiles = new ArrayList<Missile>();
	public static double MISSILE_SPEED = 7;
	public Missile() {
				
		Rectangle body = new Rectangle(40,10);
		body.setFill(Color.GRAY);
		body.setTranslateX(15);
		body.setTranslateY(5);
		Polygon top = new Polygon(
				20,0,
				20,20,
				0,10
				);
		top.setFill(Color.RED);
		
		this.getChildren().addAll(body,top);
		
		missiles.add(this);
		
	}
}
