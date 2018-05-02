package spel;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import space.Space;

public class Player extends GameObject{
	
	public static double PLAYER_DISTANCE = 0;
	public static double PLAYER_SPEED = 10;
	
	public Player() {
		
		Gravity.giveGravity(this);
	
		Rectangle body = new Rectangle(10,75);
		body.setFill(Color.BLACK);
		body.setTranslateX(10);
		
		Circle head = new Circle(15,15,15);
		head.setFill(Color.BLACK);
		
		this.getChildren().addAll(body,head);
		
		
	}
	
	public void moveHorizontally(double direction) {
		this.setTranslateX(this.getTranslateX()+direction);
		PLAYER_DISTANCE += direction;
		if(this.getTranslateX() <= -100) {
			this.setTranslateX(Space.WIDTH);
		}
		else if(this.getTranslateX() >= Space.WIDTH) {
			this.setTranslateX(-100);
		}
	}	
	
	public double getYPos(){
		return this.getTranslateY();
	}
	
	public void jump() {
		if(this.getSpeedY() == 0){
			this.setSpeedY(-25);
		}
	}
}
