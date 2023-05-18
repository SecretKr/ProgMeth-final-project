package game;

import java.util.ArrayList;

import javafx.scene.image.Image;

class Asset {
	public static ArrayList<Image> enemy = new ArrayList<Image>();
	public static int enemyFrame = 4;
	
	public static ArrayList<Image> player = new ArrayList<Image>();
	public static ArrayList<Image> playerLeft = new ArrayList<Image>();
	public static ArrayList<Image> playerRight = new ArrayList<Image>();
	public static ArrayList<Image> playerUp = new ArrayList<Image>();
	public static ArrayList<Image> playerDown = new ArrayList<Image>();
	public static int playerFrame = 6;
	
	public static void getAssets() {
		// enemy
		for(int i = 0;i < enemyFrame;i++) {
			enemy.add(new Image("assets/enemy/" + Integer.toString(i) + ".png", 30, 30, false, false));
		}
		// player
		for(int i = 0;i < playerFrame;i++) {
			player.add(new Image("assets/player/" + Integer.toString(i) + ".png", 50, 50, false, false));
			playerLeft.add(new Image("assets/player/left" + Integer.toString(i) + ".png", 50, 50, false, false));
			playerRight.add(new Image("assets/player/right" + Integer.toString(i) + ".png", 50, 50, false, false));
			playerUp.add(new Image("assets/player/up" + Integer.toString(i) + ".png", 50, 50, false, false));
			playerDown.add(new Image("assets/player/down" + Integer.toString(i) + ".png", 50, 50, false, false));
		}
	}
}
