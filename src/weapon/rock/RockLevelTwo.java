package weapon.rock;

import config.Config;
import game.Asset;
import javafx.scene.image.Image;

public class RockLevelTwo extends BaseRock{

	public RockLevelTwo(float posX, float posY) {
		super(Asset.rockImg.get(2), posX, posY, 2);
		setDamage(Config.Rock.ROCK_TWO_DAMAGE);
		setSpeed(Config.Rock.ROCK_TWO_SPEED);
		setWidth(Config.Rock.ROCK_TWO_WIDTH);
		setHeight(Config.Rock.ROCK_TWO_HEIGHT);
	}
	
}
