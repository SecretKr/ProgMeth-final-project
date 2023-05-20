package weapon.rock;

import config.Config;
import game.Asset;

public class RockLevelOne extends BaseRock{

	public RockLevelOne(float posX, float posY) {
		super(Asset.rockImg.get(1), posX, posY, 1);
		setDamage(Config.Rock.ROCK_ONE_DAMAGE);
		setSpeed(Config.Rock.ROCK_ONE_SPEED);
		setWidth(Config.Rock.ROCK_ONE_WIDTH);
		setHeight(Config.Rock.ROCK_ONE_HEIGHT);
		setDurability(Config.Rock.ROCK_ONE_DURABILITY);
	}
	
}
