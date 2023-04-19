package game;

import config.Config;
import javafx.scene.image.Image;

public class Player extends Entity {
	public Player() {
		super(new Image("/Assets/mario.png", 50, 50, false, false));
		setPosX((Config.SCREEN_WIDTH-Config.PLAYER_WIDTH)/2);
		setPosY((Config.SCREEN_HEIGHT-Config.PLAYER_HEIGHT)/2);
		setAlive(true);
	}
}
