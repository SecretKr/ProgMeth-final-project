package weapon.homing;

import config.Config;
import javafx.scene.image.Image;

public class HomingLevelZero extends BaseHoming{
	public HomingLevelZero(float posX, float posY) {
		super(new Image("assets/enemy/0.png", 10, 10, false, false), posX, posY, 0);
	}
}
