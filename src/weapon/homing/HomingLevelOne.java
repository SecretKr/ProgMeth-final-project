package weapon.homing;

import config.Config;
import game.Asset;

public class HomingLevelOne extends BaseHoming{
	public HomingLevelOne(float posX, float posY) {
		super(Asset.homingImg.get(1), posX, posY, 1);
		setDamage(Config.Homing.HOMING_ONE_DAMAGE);
		setSpeed(Config.Homing.HOMING_ONE_SPEED);
		setWidth(Config.Homing.HOMING_ONE_WIDTH);
		setHeight(Config.Homing.HOMING_ONE_HEIGHT);
		setDurability(Config.Homing.HOMING_ONE_DURABILITY);
	}
}
