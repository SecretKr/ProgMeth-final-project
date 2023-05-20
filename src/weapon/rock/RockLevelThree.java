package weapon.rock;

import config.Config;
import game.Asset;
import javafx.scene.image.Image;

public class RockLevelThree extends BaseRock{

	public RockLevelThree(float posX, float posY) {
		super(Asset.rockImg.get(0), posX, posY, 3);
		setDamage(Config.Rock.ROCK_THREE_DAMAGE);
		setSpeed(Config.Rock.ROCK_THREE_SPEED);
		setWidth(Config.Rock.ROCK_THREE_WIDTH);
		setHeight(Config.Rock.ROCK_THREE_HEIGHT);
	}
	
}
