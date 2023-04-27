package game;

import config.Config;
import javafx.scene.image.Image;

public class Enemy extends Entity {

	
	
	public Enemy(float posX, float posY, int hP) {
		super(new Image("/Assets/alien.png", 30, 30, false, false), posX, posY, hP);
		//setPosX((Config.SCREEN_WIDTH-30)/2);
		//setPosY((Config.SCREEN_HEIGHT-30)/2);
		setAlive(true);
		setHP(hP);
		setWidth(Config.ENEMY_WIDTH);
		setHeight(Config.ENEMY_HEIGHT);
		setHitDamage(10);
	}

}
