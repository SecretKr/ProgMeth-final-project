package item;

import config.Config;
import javafx.scene.image.Image;

public class Potion extends Item{

	public Potion(float posX,float posY) {
		super(new Image("assets/item/potion.png", 30, 30, false, false) , posX, posY);
		setWidth(Config.ITEM_WIDTH);
		setHeight(Config.ITEM_HEIGHT);
	}

	public void useItem() {
		
	}
	
}
