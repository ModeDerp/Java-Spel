package spel;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {

	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 800;
	static Group root;
	Scene scene;
	public static final ArrayList<KeyCode> keys = new ArrayList<KeyCode>();

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage primaryStage) {

		root = new Group();
		scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		Player player = new Player();
		player.setTranslateX(WINDOW_WIDTH/2);
		player.setTranslateY(WINDOW_HEIGHT/2-100);
		root.getChildren().add(player);

		Platform startingPlatform = new Platform((WINDOW_WIDTH/(64/25)),WINDOW_WIDTH/192);
		startingPlatform.setTranslateX(WINDOW_WIDTH/2-100);
		startingPlatform.setTranslateY(WINDOW_HEIGHT/2);
		startingPlatform.setFill(Color.RED);
		root.getChildren().add(startingPlatform);
		
		Text counter = new Text();
		root.getChildren().add(counter);
		Text GameOver = new Text();
		GameOver.setTranslateX(WINDOW_WIDTH/2);
		GameOver.setTranslateY(WINDOW_HEIGHT/2);
		GameOver.setFill(Color.RED);
		GameOver.setStyle("-fx-font: 80 arial;");
		GameOver.setText("GAME OVER" );
		
		AnimationTimer at = new AnimationTimer() {

			long timer = 0;

			@Override
			public void handle(long now) {

				for (KeyCode key : keys) {
					switch (key) {

					case A:
						player.moveHorizontally(-Player.PLAYER_SPEED);
						break;
					case D:
						player.moveHorizontally(+Player.PLAYER_SPEED);
						break;
					case SPACE:
						player.jump();
						break;
					default:
						break;
					}
				}
				Gravity.doGravity();

				if(now-timer >= 10_000_000_00) {
					Platform platform = new Platform(200,10);
					platform.setTranslateX(WINDOW_WIDTH+Math.random()*WINDOW_WIDTH/(32/5));
					platform.setTranslateY(Math.random()*(WINDOW_HEIGHT/(96/25))+400);
					root.getChildren().add(platform);
					Missile missile = new Missile();
					missile.setTranslateX(WINDOW_WIDTH*(25/16));
					missile.setTranslateY(Math.random()*(WINDOW_HEIGHT/(96/25))+400);
					root.getChildren().add(missile);
					timer = now;
				}
				
				if(now-timer >= 10_000_000_00) {
					
					timer = now;
				}
				
				for (Platform array : Platform.platforms  ) {
					array.setTranslateX(array.getTranslateX()-Platform.PLATFORM_SPEED);
				}
				for(Missile array : Missile.missiles) {
					array.setTranslateX(array.getTranslateX()-Missile.MISSILE_SPEED);
				}
				for(int i = 0; i < Missile.missiles.size(); i++) {
					Missile m = Missile.missiles.get(i);
					if(player.getBoundsInParent().intersects(m.getBoundsInParent()) || player.getTranslateY() > WINDOW_HEIGHT) {
						root.getChildren().remove(player);
						Platform.PLATFORM_SPEED = 0;
						Player.PLAYER_SPEED = 0;
						if(!root.getChildren().contains(GameOver)) {
							root.getChildren().add(GameOver);
						}
						break;
					}
				}
				player.setTranslateX(player.getTranslateX()-Platform.PLATFORM_SPEED);
				
				String distance = String.valueOf(Player.PLAYER_DISTANCE);
				counter.setText(distance);
				counter.setTranslateX(50);
				counter.setTranslateY(50);
				counter.setFill(Color.BLACK);
				counter.setStyle("-fx-font: 24 arial;");
			}
		};
		at.start();

		primaryStage.setScene(scene);
		primaryStage.show();

		scene.setOnKeyPressed(event -> {

			KeyCode key = event.getCode();
			if (!keys.contains(key)) {
				keys.add(key);
			}
		});      
		scene.setOnKeyReleased(event -> {

			KeyCode key = event.getCode();
			if (keys.contains(key)) {
				keys.remove(key);
			}
		});
	}
}
