package weapon.rock;

import config.Config;
import javafx.scene.image.Image;

public class RockLevelTwo extends BaseRock{

	public RockLevelTwo(float posX, float posY) {
		super(new Image("/Assets/alien.png", 10, 10, false, false), posX, posY, 2);
		setDamage(Config.Rock.ROCK_TWO_DAMAGE);
		setSpeed(Config.Rock.ROCK_TWO_SPEED);
		setWidth(Config.Rock.ROCK_TWO_WIDTH);
		setHeight(Config.Rock.ROCK_TWO_HEIGHT);
	}
	
}
