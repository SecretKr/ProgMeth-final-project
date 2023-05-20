package item;

import config.Config;
import game.Main;
import javafx.scene.image.Image;

public class Bomb extends Item{

	public Bomb(float posX,float posY) {
		super(new Image("assets/item/bomb.png", 30, 30, false, false) , posX, posY);
		setWidth(Config.ITEM_WIDTH);
		setHeight(Config.ITEM_HEIGHT);
	}

	public void useItem() {
		Main.clearEnemy();
	}
	
}
