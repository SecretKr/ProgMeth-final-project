package weapon.homing;

import game.Asset;

public class HomingLevelZero extends BaseHoming{
	public HomingLevelZero(float posX, float posY) {
		super(Asset.homingImg.get(0), posX, posY, 0);
	}
}
