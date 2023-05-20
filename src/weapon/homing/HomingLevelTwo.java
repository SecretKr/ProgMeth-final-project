package weapon.homing;

import config.Config;
import game.Asset;
import javafx.scene.image.Image;

public class HomingLevelTwo extends BaseHoming{
	public HomingLevelTwo(float posX, float posY) {
		super(Asset.homingImg.get(2), posX, posY, 1);
		setDamage(Config.Homing.HOMING_TWO_DAMAGE);
		setSpeed(Config.Homing.HOMING_TWO_SPEED);
		setWidth(Config.Homing.HOMING_TWO_WIDTH);
		setHeight(Config.Homing.HOMING_TWO_HEIGHT);
	}
}