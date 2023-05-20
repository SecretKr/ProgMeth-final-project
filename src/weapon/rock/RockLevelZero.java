package weapon.rock;

import config.Config;
import game.Asset;
import javafx.scene.image.Image;

public class RockLevelZero extends BaseRock{

	public RockLevelZero(float posX, float posY) {
		super(Asset.rockImg.get(0), posX, posY, 0);
	}
	
}
