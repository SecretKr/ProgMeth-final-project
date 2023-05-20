package weapon.homing;

import config.Config;
import game.Asset;
import javafx.scene.image.Image;

public class HomingLevelZero extends BaseHoming{
	public HomingLevelZero(float posX, float posY) {
		super(Asset.homingImg.get(0), posX, posY, 0);
	}
}
