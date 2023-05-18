package game;

import config.Config;
import javafx.scene.image.Image;

public class Enemy extends Entity {
	public Enemy(float posX, float posY, int hP) {
		super(new Image("/assets/enemy/0.png", 30, 30, false, false), posX, posY, hP);
		//setPosX((Config.SCREEN_WIDTH-30)/2);
		//setPosY((Config.SCREEN_HEIGHT-30)/2);
		setAlive(true);
		setHP(hP);
		setWidth(Config.ENEMY_WIDTH);
		setHeight(Config.ENEMY_HEIGHT);
		setHitDamage(10);
	}

	public void draw() {
		Image image = new Image("assets/enemy/" + getEntityNumString() + ".png", 30, 30, false, false);
		this.setImage(image);
	}
	
	public int getEntityNumMax() {
		return 4;
	}

}
