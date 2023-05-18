package weapon.homing;

import config.Config;
import javafx.scene.image.Image;

public class HomingLevelOne extends BaseHoming{
	public HomingLevelOne(float posX, float posY) {
		super(new Image("/assets/enemy/0.png", 10, 10, false, false), posX, posY, 1);
		setDamage(Config.Homing.HOMING_ONE_DAMAGE);
		setSpeed(Config.Homing.HOMING_ONE_SPEED);
		setWidth(Config.Homing.HOMING_ONE_WIDTH);
		setHeight(Config.Homing.HOMING_ONE_HEIGHT);
	}
}
