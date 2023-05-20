package weapon.homing;

import config.Config;
import game.Asset;
import javafx.scene.image.Image;

public class HomingLevelThree extends BaseHoming{
	public HomingLevelThree(float posX, float posY) {
		super(Asset.homingImg.get(3), posX, posY, 1);
		setDamage(Config.Homing.HOMING_THREE_DAMAGE);
		setSpeed(Config.Homing.HOMING_THREE_SPEED);
		setWidth(Config.Homing.HOMING_THREE_WIDTH);
		setHeight(Config.Homing.HOMING_THREE_HEIGHT);
	}
}