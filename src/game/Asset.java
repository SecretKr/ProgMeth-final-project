package game;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Asset {
	public static ArrayList<Image> enemy = new ArrayList<Image>();
	public static int enemyFrame = 4;
	
	public static ArrayList<Image> player = new ArrayList<Image>();
	public static ArrayList<Image> playerLeft = new ArrayList<Image>();
	public static ArrayList<Image> playerRight = new ArrayList<Image>();
	public static ArrayList<Image> playerUp = new ArrayList<Image>();
	public static ArrayList<Image> playerDown = new ArrayList<Image>();
	public static int playerFrame = 6;

	public static AudioClip enemyHit;
	public static AudioClip nextWave;
	public static AudioClip bomb;
	public static AudioClip music;
	public static AudioClip gameOver;
	
	public static ArrayList<Image> rockImg = new ArrayList<Image>();
	public static ArrayList<Image> homingImg = new ArrayList<Image>();
	public static int weaponLevel = 4;
	
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
		
		enemyHit = new AudioClip("file:src/assets/sound/enemyHit.wav");
		nextWave = new AudioClip("file:src/assets/sound/nextWave.wav");
		bomb = new AudioClip("file:src/assets/sound/bomb.wav");
		music = new AudioClip("file:src/assets/sound/music.wav");
		gameOver = new AudioClip("file:src/assets/sound/gameOver.wav");
		
		for(int i = 0;i < weaponLevel;i++) {
			rockImg.add(new Image("assets/weapon/rock" + Integer.toString(i) + ".png", 10, 10, false, false));
			homingImg.add(new Image("assets/weapon/ghost" + Integer.toString(i) + ".png", 10, 10, false, false));
		}
	}
}
