package weapon.homing;

import config.Config;
import javafx.scene.image.Image;

public class HomingLevelTwo extends BaseHoming{
	public HomingLevelTwo(float posX, float posY) {
		super(new Image("assets/enemy/0.png", 10, 10, false, false), posX, posY, 1);
		setDamage(Config.Homing.HOMING_TWO_DAMAGE);
		setSpeed(Config.Homing.HOMING_TWO_SPEED);
		setWidth(Config.Homing.HOMING_TWO_WIDTH);
		setHeight(Config.Homing.HOMING_TWO_HEIGHT);
	}
}