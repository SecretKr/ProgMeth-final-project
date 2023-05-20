package weapon.rock;

import config.Config;
import game.Asset;
import javafx.scene.image.Image;

public class RockLevelOne extends BaseRock{

	public RockLevelOne(float posX, float posY) {
		super(Asset.rockImg.get(1), posX, posY, 1);
		setDamage(Config.Rock.ROCK_ONE_DAMAGE);
		setSpeed(Config.Rock.ROCK_ONE_SPEED);
		setWidth(Config.Rock.ROCK_ONE_WIDTH);
		setHeight(Config.Rock.ROCK_ONE_HEIGHT);
	}
	
}
