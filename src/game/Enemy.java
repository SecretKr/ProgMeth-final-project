package game;

import config.Config;
import javafx.scene.image.Image;

public class Enemy extends Entity {

	
	
	public Enemy() {
		super(new Image("/Assets/alien.png", 30, 30, false, false));
		setPosX((Config.SCREEN_WIDTH-30)/2);
		setPosY((Config.SCREEN_HEIGHT-30)/2);
		setAlive(true);
		setHP(1);
		setWidth(Config.ENEMY_WIDTH);
		setHeight(Config.ENEMY_HEIGHT);
		setHitDamage(10);
	}

}
