package spel;

import javafx.scene.Group;

public abstract class GameObject extends Group{
	
	private double speedY;

	public double getHeight(){
		return this.getBoundsInLocal().getHeight();
	}
	
	public double getWidth(){
		return this.getBoundsInLocal().getWidth();
	}
	
	public void setSpeedY(double speed){
		this.speedY = speed;
	}

	public double getSpeedY(){
		return this.speedY;
	}
}