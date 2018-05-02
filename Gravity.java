package spel;

import java.util.ArrayList;

public class Gravity {
	
	private static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	public static final double gravity = 0.982;
	
	public static void doGravity() {
		
		for (GameObject go : objects) {

			go.setSpeedY(go.getSpeedY() + gravity);

			if (go.getSpeedY() < 0) {
				go.setTranslateY(go.getTranslateY() + go.getSpeedY());
				return;
			}

			for (Platform p : Platform.platforms) {

				if (intersects(p, go)) {
					go.setSpeedY(0);
					go.setTranslateY(p.getTranslateY() - go.getHeight());
					break;
				}

			}

			if (go.getSpeedY() != 0) {
				go.setTranslateY(go.getTranslateY() + go.getSpeedY());
			} 
		}
		
	}
	
	private static boolean intersects(Platform p, GameObject go) {

		double goY = go.getTranslateY() + go.getSpeedY();
		double goX = go.getTranslateX();

		double pY = p.getTranslateY();
		double pX = p.getTranslateX();

		double pWidth = p.getWidth();
		double goWidth = go.getWidth();
		double goHeight = go.getHeight();

		if ((goX > pX && goX < pWidth + pX) || (goX + goWidth > pX && goX + goWidth < pWidth + pX)) {

			if (pY >= goY + goHeight - go.getSpeedY() && pY <= goY + goHeight) {
				return true;
			}
		}
		return false;
	}

	public static void giveGravity(GameObject go) {
		objects.add(go);
	}


}
